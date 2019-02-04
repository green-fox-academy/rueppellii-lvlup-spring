package com.greenfox.lvlup.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

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
  @ManyToMany(mappedBy = "holders")
  private Set<BadgeLevel> levels = new HashSet<>();

  public User(String name, String tokenAuth, String pic) {
    this.name = name;
    this.tokenAuth = tokenAuth;
    this.pic = pic;
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

  public Set<BadgeLevel> getLevels() {
    return levels;
  }

  public void setLevels(Set<BadgeLevel> levels) {
    this.levels = levels;
  }
}
