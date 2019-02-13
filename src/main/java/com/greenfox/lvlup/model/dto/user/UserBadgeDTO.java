package com.greenfox.lvlup.model.dto.user;

public class UserBadgeDTO {
  private String name;
  private int level;

  public UserBadgeDTO(String name) {
    this.name = name;
    this.level = 1;
  }

  public UserBadgeDTO(String name, int level) {
    this.name = name;
    this.level = level;
  }

  public UserBadgeDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
}
