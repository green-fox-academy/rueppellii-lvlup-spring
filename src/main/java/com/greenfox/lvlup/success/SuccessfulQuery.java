package com.greenfox.lvlup.success;

public class SuccessfulQuery {
  String message;

  public SuccessfulQuery(String message) {
    this.message = message;
  }

  public SuccessfulQuery() {
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
