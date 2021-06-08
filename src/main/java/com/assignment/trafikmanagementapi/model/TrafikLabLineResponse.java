package com.assignment.trafikmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrafikLabLineResponse {

  @JsonProperty("StatusCode")
  private int statusCode;

  @JsonProperty("Message")
  private String message;

  @JsonProperty("ExecutionTime")
  private int executionTime;

  @JsonProperty("ResponseData")
  private LineModelResponseData lineModelResponseData;

}
