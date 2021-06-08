package com.assignment.trafikmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopPoint {

  @JsonProperty("StopPointNumber")
  private String stopPointNumber;

  @JsonProperty("StopPointName")
  private String stopPointName;

  @JsonProperty("StopAreaNumber")
  private String stopAreaNumber;

}
