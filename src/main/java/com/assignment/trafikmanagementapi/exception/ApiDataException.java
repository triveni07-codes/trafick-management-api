package com.assignment.trafikmanagementapi.exception;

import org.springframework.http.HttpStatus;

public class ApiDataException extends RuntimeException {

  private static final long serialVersionUID = 5373868865249521216L;

  private HttpStatus status;

  public ApiDataException(Exception e) {
    super(e);
  }

  public ApiDataException(String errorMessage) {
    super(errorMessage);
  }

  public ApiDataException(String errorMessage, HttpStatus status) {
    super(errorMessage);
    this.status = status;
  }

  public ApiDataException(String errorMessage, Exception e) {
    super(errorMessage, e);
  }

  public HttpStatus getStatus() {
    return this.status;
  }
}
