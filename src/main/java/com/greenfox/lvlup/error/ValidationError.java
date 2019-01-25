package com.greenfox.lvlup.error;

import org.springframework.util.MultiValueMap;

public class ValidationError {
  String error;

  public ValidationError() {
  }

  public ValidationError(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
