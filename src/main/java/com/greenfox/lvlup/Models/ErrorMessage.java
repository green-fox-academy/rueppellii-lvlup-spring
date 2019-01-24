package com.greenfox.lvlup.Models;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

  private String error;
  private HttpStatus status;

  public ErrorMessage() {
  }

  public ErrorMessage(String error, HttpStatus status) {
    this.error = error;
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}
