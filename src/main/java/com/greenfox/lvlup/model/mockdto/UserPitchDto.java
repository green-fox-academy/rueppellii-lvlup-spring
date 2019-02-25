package com.greenfox.lvlup.model.mockdto;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.Review;
import com.greenfox.lvlup.model.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class UserPitchDto {
  private long id;
  private Date created;
  private int oldLevel;
  private int pitchedLevel;
  private String pitchedMessage;
  private User user;
  private BadgeLevel level;
  private Badge badge;
  private List<Review> reviews;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public BadgeLevel getLevel() {
    return level;
  }

  public void setLevel(BadgeLevel level) {
    this.level = level;
  }

  public Badge getBadge() {
    return badge;
  }

  public void setBadge(Badge badge) {
    this.badge = badge;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }
}
