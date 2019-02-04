package com.greenfox.lvlup.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "badges")
public class Badge {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long badgeId;
  @JsonIgnore
  private String version;
  private String name;
  @JsonIgnore
  private String tag;
  @OneToMany
  @JoinColumn(name = "levelId")
  private List<BadgeLevel> levels;

  public Badge(String version, String name, String tag, List<BadgeLevel> levels) {
    this.version = version;
    this.name = name;
    this.tag = tag;
    this.levels = levels;
  }

  public Badge(String version, String name, String tag) {
    this.version = version;
    this.name = name;
    this.tag = tag;
  }

  public Badge() {
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

  public List<BadgeLevel> getLevels() {
    return levels;
  }

  public void setLevels(List<BadgeLevel> levels) {
    this.levels = levels;
  }
}
