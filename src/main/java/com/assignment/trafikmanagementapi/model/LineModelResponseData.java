package com.assignment.trafikmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineModelResponseData {

  @JsonProperty("Version")
  private String version;

  @JsonProperty("Type")
  private String type;

  @JsonProperty("Result")
  private List<BusLine> busLines;
}
