package com.greenfox.lvlup.model.dto.pitches;


public class ReviewDTO {
  private Long id;
  private String pitcherName;
  private String badgeName;
  private String message;
  private boolean pitchStatus;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPitcherName() {
    return pitcherName;
  }

  public void setPitcherName(String pitcherName) {
    this.pitcherName = pitcherName;
  }

  public String getBadgeName() {
    return badgeName;
  }

  public void setBadgeName(String badgeName) {
    this.badgeName = badgeName;
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
