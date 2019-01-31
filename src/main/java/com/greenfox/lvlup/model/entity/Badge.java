package com.greenfox.lvlup.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="badges")
public class Badge {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long badgeId;
  private String version;
  private String name;
  private String tag;
  private ArrayList<String> levels;
  @ManyToMany
  @JoinColumn(name = "badge_id")
  private List<User> users;

  public Badge(String version, String name, String tag, ArrayList<String> levels, List<User> users) {
    this.version = version;
    this.name = name;
    this.tag = tag;
    this.levels = levels;
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

  public ArrayList<String> getLevels() {
    return levels;
  }

  public void setLevels(ArrayList<String> levels) {
    this.levels = levels;
  }
}
