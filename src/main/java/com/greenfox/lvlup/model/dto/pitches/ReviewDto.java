package com.greenfox.lvlup.model.dto.pitches;


public class ReviewDto {
  private String name;
  private String message;
  private boolean pitchStatus;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isPitchStatus() {
    return pitchStatus;
  }

  public void setPitchStatus(boolean pitchStatus) {
    this.pitchStatus = pitchStatus;
  }
}
