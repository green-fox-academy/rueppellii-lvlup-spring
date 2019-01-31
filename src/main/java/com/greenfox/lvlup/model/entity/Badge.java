package com.greenfox.lvlup.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="badges")
public class Badge {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  private long badgeId;
  @JsonIgnore
  private String version;

  private String name;
  @JsonIgnore
  private String tag;

  private int level;
  @ManyToMany(mappedBy = "badges")
  @JsonIgnore
  private List<User> users;

  public Badge(String version, String name, String tag, int level, List<User> users) {
    this.version = version;
    this.name = name;
    this.tag = tag;
    this.level = level;
    this.users = users;
  }

  public Badge() {
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public long getBadgeId() {
    return badgeId;
  }

  public void setBadgeId(long badgeId) {
    this.badgeId = badgeId;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public int getLevels() {
    return level;
  }

  public void setLevels(int level) {
    this.level = level;
  }
}
