package com.greenfox.lvlup.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  private String tokenAuth;
  private String pic;
  @ManyToMany(mappedBy = "holders")
  private Set<BadgeLevel> badgeLevels = new HashSet<>();
  @OneToMany (mappedBy = "user")
  private User user;

  public User() {
  }

  public User(String name, String tokenAuth, String pic) {
    this.name = name;
    this.tokenAuth = tokenAuth;
    this.pic = pic;
  }

  public User(String name) {
    this.name = name;
  }

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

  public Set<BadgeLevel> getBadgeLevels() {
    return badgeLevels;
  }

  public void setBadgeLevels(Set<BadgeLevel> badgeLevels) {
    this.badgeLevels = badgeLevels;
  }
}
