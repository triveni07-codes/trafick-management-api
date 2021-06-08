package com.assignment.trafikmanagementapi.client;

import com.assignment.trafikmanagementapi.model.TrafikLabLineResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TrafikLabClient {

  private final WebClient webClient;

  /*
  @Value("${trafik-lab.base-url}")
  private String baseUrl;
   */

  public TrafikLabClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("https://api.sl.se").
        defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  public TrafikLabLineResponse getAllBusLines() {
    return webClient.get()
        .uri("/api2/LineData.json?key=e66e881a3e43400d89f79440dbac7abc&model=jour&DefaultTransportModeCode=BUS")
        .retrieve()
        .bodyToMono(TrafikLabLineResponse.class)
        .block();
  }

}
