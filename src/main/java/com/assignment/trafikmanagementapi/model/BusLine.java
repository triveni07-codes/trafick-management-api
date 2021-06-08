package com.assignment.trafikmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusLine {

  @JsonProperty("LineNumber")
  private String lineNumber;

  @JsonProperty("DirectionCode")
  private String directionCode;

  @JsonProperty("JourneyPatternPointNumber")
  private String journeyPatternPointNumber;

}
