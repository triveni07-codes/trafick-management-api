package com.assignment.trafikmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusLineDetailsDto {

  @JsonProperty("LineNumber")
  private String lineNumber;

  @JsonProperty("BusStops")
  private List<BusStop> listOfBusStops;


}
