package com.assignment.trafikmanagementapi.service;

import static java.util.stream.Collectors.groupingBy;

import com.assignment.trafikmanagementapi.client.TrafikLabClient;
import com.assignment.trafikmanagementapi.model.BusLine;
import com.assignment.trafikmanagementapi.model.BusLineDetailsDto;
import com.assignment.trafikmanagementapi.model.BusStop;
import com.assignment.trafikmanagementapi.model.LineModelResponseData;
import com.assignment.trafikmanagementapi.model.StopPoint;
import com.assignment.trafikmanagementapi.model.StopPointModelResponseData;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusLineService {

  private static final int MAX_BUS_LINES = 10;

  @Autowired
  private TrafikLabClient trafikLabClient;

  public List<BusLineDetailsDto> getTopBusLines() {

    List<BusLineDetailsDto> busLinesDetails = extractBusLinesWithBusStopNumbers();
    enrichBusLinesDetailsWithStopName(busLinesDetails);
    return busLinesDetails;

  }

  private void enrichBusLinesDetailsWithStopName(List<BusLineDetailsDto> busLinesDetails) {
    StopPointModelResponseData lineModelResponseData = trafikLabClient.getAllBusStops().getStopPointModelResponseData();

    busLinesDetails.forEach(busLineObj -> {
      busLineObj.getListOfBusStops().forEach(busStop -> {
        StopPoint stop = lineModelResponseData.getStopPoints().stream()
            .filter(stopPoint -> stopPoint.getStopPointNumber().equalsIgnoreCase(busStop.getNumber()))
            .collect(Collectors.toList()).get(0);

        busStop.setName(stop.getStopPointName());
      });
    });

  }

  private List<BusLineDetailsDto> extractBusLinesWithBusStopNumbers() {
    LineModelResponseData lineModelResponseData = trafikLabClient.getAllBusLines().getLineModelResponseData();

    final Map<String, List<BusLine>> groupedBusLines = lineModelResponseData.getBusLines().stream()
        .collect(groupingBy(BusLine::getLineNumber));

    Map<String, Integer> numberOfBusStopsPerLine = getNumberOfBusStopsPerLine(groupedBusLines);
    Map<String, Integer> topTenBusLines = sortByNumberOfBusStops(numberOfBusStopsPerLine);

    return mapTop10BusLinesWithBusStops(topTenBusLines, groupedBusLines);

  }

  private List<BusLineDetailsDto> mapTop10BusLinesWithBusStops(Map<String, Integer> topTenBusLines,
      Map<String, List<BusLine>> groupedBusLines) {

    List<BusLineDetailsDto> top10BusLinesDetails = new ArrayList<>();
    topTenBusLines.forEach((lineNumber, numberOfBusStops) -> {
      List<BusLine> busLines = groupedBusLines.get(lineNumber);
      BusLineDetailsDto busLineDetailsDto = buildBusLineDetails(busLines);
      busLineDetailsDto.setLineNumber(lineNumber);
      busLineDetailsDto.setNumberOfBusStops(numberOfBusStops);
      top10BusLinesDetails.add(busLineDetailsDto);
    });

    return top10BusLinesDetails;

  }

  private BusLineDetailsDto buildBusLineDetails(List<BusLine> busLines) {

    BusLineDetailsDto busLineDetailsDto = new BusLineDetailsDto();
    List<BusStop> busStops = new ArrayList<>();
    List<String> busStopNumbers = busLines.stream().map(BusLine::getJourneyPatternPointNumber)
        .collect(Collectors.toList());
    busStopNumbers.forEach(busStopNumber -> {
      BusStop busStop = new BusStop();
      busStop.setNumber(busStopNumber);
      busStops.add(busStop);
    });
    busLineDetailsDto.setListOfBusStops(busStops);
    return busLineDetailsDto;

  }

  private Map<String, Integer> sortByNumberOfBusStops(Map<String, Integer> numberOfBusStopsPerLine) {
    return numberOfBusStopsPerLine.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(MAX_BUS_LINES)
        .collect(Collectors.toMap(
            Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  private Map<String, Integer> getNumberOfBusStopsPerLine(Map<String, List<BusLine>> groupedBusLines) {
    Map<String, Integer> numberOfBusStopsPerLine = new HashMap<String, Integer>();
    groupedBusLines.entrySet().stream().forEach(entry -> {
      numberOfBusStopsPerLine.put(entry.getKey(), entry.getValue().size());
    });
    return numberOfBusStopsPerLine;
  }

}
