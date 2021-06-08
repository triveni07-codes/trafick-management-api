package com.assignment.trafikmanagementapi.service;

import static java.util.stream.Collectors.groupingBy;

import com.assignment.trafikmanagementapi.client.TrafikLabClient;
import com.assignment.trafikmanagementapi.model.BusLine;
import com.assignment.trafikmanagementapi.model.BusLineDetailsDto;
import com.assignment.trafikmanagementapi.model.ResponseData;
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

  @Autowired
  private TrafikLabClient trafikLabClient;

  public List<BusLineDetailsDto> getTop10BusLines() {

    Map<String, List<BusLine>> top10BusLinesWithBusStopNumbers = getTop10BusLinesWithBusStopNumbers();

    return new ArrayList<>();

  }

  private Map<String, List<BusLine>> getTop10BusLinesWithBusStopNumbers() {
    ResponseData responseData = trafikLabClient.getAllBusLines().getResponseData();
    final Map<String, List<BusLine>> groupedBusLines = responseData.getBusLines().stream()
        .collect(groupingBy(BusLine::getLineNumber));

    Map<String, Integer> numberOfBusStopsPerLine = getNumberOfBusStopsPerLine(groupedBusLines);
    Map<String, Integer> topTenBusLines = sortByNumberOfBusStops(numberOfBusStopsPerLine);

    Map<String, List<BusLine>> top10BusLinesWithBusStops = new HashMap<>();
    topTenBusLines.forEach((k, v) -> {
      top10BusLinesWithBusStops.put(k, groupedBusLines.get(k));
    });

    return top10BusLinesWithBusStops;
  }

  private Map<String, Integer> sortByNumberOfBusStops(Map<String, Integer> numberOfBusStopsPerLine) {
    return numberOfBusStopsPerLine.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(10)
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
