package com.greenfox.lvlup.model.dto.pitches;


import com.greenfox.lvlup.model.entity.Review;

import java.util.Date;
import java.util.Set;

public class PitchDto {
  private long id;
  private Date timestamp;
  private String userName;
  private String badgeName;
  private int oldLevel;
  private int pitchedLevel;
  private String pitchedMessage;
  private Set<ReviewDto> reviews;

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
