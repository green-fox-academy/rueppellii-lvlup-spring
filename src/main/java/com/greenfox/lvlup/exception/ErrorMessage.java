package com.greenfox.lvlup.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
  private String error;

  public ErrorMessage() {}

  public ErrorMessage(String error) {
    this.error = error;
  }
}
