package com.greenfox.lvlup.exception;

public class GeneralMessage {
  String message;

  public GeneralMessage(String message) {
    this.message = message;
  }

  public GeneralMessage() {
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
