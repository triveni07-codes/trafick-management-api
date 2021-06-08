package com.assignment.trafikmanagementapi.util;

import com.assignment.trafikmanagementapi.exception.ApiDataException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileReader {

  @Autowired
  private ObjectMapper objectMapper;

  public <T> T loadInputData(String fileName, Class<T> type) {
    String path = getClass().getClassLoader().getResource(fileName).getPath();
    File file = new File(path);
    try {
      objectMapper.registerModule(new JavaTimeModule());
      return objectMapper.readValue(file, type);
    } catch (IOException e) {
      throw new ApiDataException(e.getMessage());
    }
  }
}
