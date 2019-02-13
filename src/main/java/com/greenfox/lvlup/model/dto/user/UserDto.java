package com.greenfox.lvlup.model.dto.user;

import java.util.Set;

public class UserDto {
  private long id;
   private String name;
   private String tokenAuth;
   private String pic;
   private Set<UserBadgeDTO> badges;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTokenAuth() {
    return tokenAuth;
  }

  public void setTokenAuth(String tokenAuth) {
    this.tokenAuth = tokenAuth;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public Set<UserBadgeDTO> getBadges() {
    return badges;
  }

  public void setBadges(Set<UserBadgeDTO> badges) {
    this.badges = badges;
  }
}
