package com.greenfox.lvlup.model.dto.pitches;


public class ReviewDto {
  private String pitcherName;
  private String message;
  private boolean pitchStatus;

  public String getPitcherName() {
    return pitcherName;
  }

  public void setPitcherName(String pitcherName) {
    this.pitcherName = pitcherName;
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
