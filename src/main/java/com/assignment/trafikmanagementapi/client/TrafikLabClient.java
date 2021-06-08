package com.assignment.trafikmanagementapi.client;

import com.assignment.trafikmanagementapi.model.TrafikLabLineResponse;
import com.assignment.trafikmanagementapi.model.TrafikLabStopPointResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Service
public class TrafikLabClient {

  private final String apiKey;
  private final String path;
  private final WebClient webClient;

  public TrafikLabClient(Builder webClientBuilder, @Value("${trafik-lab.base-url}") String baseUrl,
      @Value("${trafik-lab.api-key}") String apiKey,
      @Value("${trafik-lab.path}") String path) {
    this.apiKey = apiKey;
    this.path = path;
    this.webClient = webClientBuilder.baseUrl(baseUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  public TrafikLabLineResponse getAllBusLines() {
    return webClient.get()
        .uri(path + apiKey + "&model=jour&DefaultTransportModeCode=BUS")
        .retrieve()
        .bodyToMono(TrafikLabLineResponse.class)
        .block();
  }

  public TrafikLabStopPointResponse getAllBusStops() {
    return webClient.get()
        .uri(path + apiKey + "&model=stop&StopAreaTypeCode=BUSTERM")
        .retrieve()
        .bodyToMono(TrafikLabStopPointResponse.class)
        .block();
  }

}
