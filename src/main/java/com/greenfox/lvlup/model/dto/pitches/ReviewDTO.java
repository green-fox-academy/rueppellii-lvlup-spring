package com.greenfox.lvlup.model.dto.pitches;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReviewDTO {
  @NotNull(message = "ReviewDTO id is required")
  private Long id;
  @NotEmpty(message = "ReviewDTO pitcherName is required")
  private String pitcherName;
  @NotEmpty(message = "ReviewDTO badgeName is required")
  private String badgeName;
  @NotEmpty(message = "ReviewDTO message is required")
  private String message;
  @NotNull(message = "ReviewDTO pitchStatus is required")
  private boolean pitchStatus;

  public ReviewDTO() {
  }

  public ReviewDTO(@NotNull(message = "ReviewDTO id is required") Long id, @NotEmpty(message = "ReviewDTO pitcherName is required") String pitcherName, @NotEmpty(message = "ReviewDTO badgeName is required") String badgeName, @NotEmpty(message = "ReviewDTO message is required") String message, @NotEmpty(message = "ReviewDTO pitchStatus is required") boolean pitchStatus) {
    this.id = id;
    this.pitcherName = pitcherName;
    this.badgeName = badgeName;
    this.message = message;
    this.pitchStatus = pitchStatus;
  }

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
