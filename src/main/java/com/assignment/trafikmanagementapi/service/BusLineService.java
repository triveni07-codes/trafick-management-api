package com.assignment.trafikmanagementapi.service;

import static java.util.stream.Collectors.groupingBy;
import com.assignment.trafikmanagementapi.client.TrafikLabClient;
import com.assignment.trafikmanagementapi.model.BusLine;
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

  public List<BusLine> getTop10BusLines() {
    ResponseData responseData = trafikLabClient.getAllBusLines().getResponseData();

    return null;
  }

}
