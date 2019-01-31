package com.greenfox.lvlup.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userId;
  @NotEmpty
  private String name;
  @NotEmpty
  private String tokenAuth;
  @NotEmpty
  private String pic;
  @ManyToMany
  @JoinTable(name = "user_badge",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
      inverseJoinColumns = @JoinColumn(name = "badge_id", referencedColumnName = "badgeId"))
  private List<Badge> badges;

  public User(String name, String tokenAuth, String pic, List<Badge> badges) {
    this.name = name;
    this.tokenAuth = tokenAuth;
    this.pic = pic;
    this.badges = badges;
  }

  public User() {
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
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

  public List<Badge> getBadges() {
    return badges;
  }

  public void setBadges(List<Badge> badges) {
    this.badges = badges;
  }
}
