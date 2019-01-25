package com.greenfox.lvlup.model;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

  private ErrorMessage errorMessage;
  private HttpStatus httpStatus;

  public CustomException() {}

  public CustomException(String error) {
    this.errorMessage = new ErrorMessage(error);
  }

  public CustomException(String error, HttpStatus httpStatus) {
    this.errorMessage = new ErrorMessage(error);
    this.httpStatus = httpStatus;
  }

  public ErrorMessage getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }
}
