package com.greenfox.lvlup.model.dto.pitches;


import com.greenfox.lvlup.model.entity.Review;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class PitchDto {
  private long id;
  private Date timestamp;
  private String userName;
  @NotEmpty(message = "PitchDto name is required.")
  private String badgeName;
  @NotNull(message = "Old badgeLevel is required.")
  private int oldLevel;
  @NotNull(message = "Pitched badgeLevel is required.")
  private int pitchedLevel;
  @NotEmpty(message = "PitchDto message is required.")
  private String pitchedMessage;
  private Set<ReviewDto> reviews;
  @NotEmpty(message = "Holders are required.")
  List<String> holders;


  public PitchDto(String badgeName, int oldLVL, int pitchedLVL, String pitchMessage, List<String> holders) {
    this.badgeName = badgeName;
    this.oldLevel = oldLVL;
    this.pitchedLevel = pitchedLVL;
    this.pitchedMessage = pitchMessage;
    this.holders = holders;
  }

  public PitchDto(String badgeName, int oldLVL, int pitchedLVL, String pitchMessage) {
    this.badgeName = badgeName;
    this.oldLevel = oldLVL;
    this.pitchedLevel = pitchedLVL;
    this.pitchedMessage = pitchMessage;
  }

  public PitchDto(String badgeName, int pitchedLVL, String pitchMessage, List<String> holders) {
    this.badgeName = badgeName;
    this.pitchedLevel = pitchedLVL;
    this.pitchedMessage = pitchMessage;
    this.holders = holders;
  }

  public PitchDto(int oldLVL, int pitchedLVL, String pitchMessage, List<String> holders) {
    this.oldLevel = oldLVL;
    this.pitchedLevel = pitchedLVL;
    this.pitchedMessage = pitchMessage;
    this.holders = holders;
  }

  public PitchDto() {
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getBadgeName() {
    return badgeName;
  }

  public void setBadgeName(String badgeName) {
    this.badgeName = badgeName;
  }

  public int getOldLevel() {
    return oldLevel;
  }

  public void setOldLevel(int oldLevel) {
    this.oldLevel = oldLevel;
  }

  public int getPitchedLevel() {
    return pitchedLevel;
  }

  public void setPitchedLevel(int pitchedLevel) {
    this.pitchedLevel = pitchedLevel;
  }

  public String getPitchedMessage() {
    return pitchedMessage;
  }

  public void setPitchedMessage(String pitchedMessage) {
    this.pitchedMessage = pitchedMessage;
  }

  public Set<ReviewDto> getReviews() {
    return reviews;
  }

  public void setReviews(Set<ReviewDto> reviews) {
    this.reviews = reviews;
  }
}
