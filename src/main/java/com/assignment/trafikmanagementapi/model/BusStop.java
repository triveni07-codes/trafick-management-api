package com.assignment.trafikmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusStop {

  @JsonProperty("Number")
  private String number;

  @JsonProperty("Name")
  private String name;


}
