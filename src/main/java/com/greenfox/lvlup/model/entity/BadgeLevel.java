package com.greenfox.lvlup.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "levels")
public class BadgeLevel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int levelId;
  private int level;
  private String description;
  @ManyToOne
  @JoinColumn(name = "badgeId")
  private Badge badge;
  @ManyToMany
  @JsonIgnore
  @JoinTable(name = "badgelevel_user",
      joinColumns = @JoinColumn(name = "badgelevel_id", referencedColumnName = "levelId"),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
  private Set<User> holders;

  public BadgeLevel() {
  }

  public BadgeLevel(int level, String description, Badge badge, User... holders) {
    this.level = level;
    this.description = description;
    this.badge = badge;
    this.holders = Stream.of(holders).collect(Collectors.toSet());
    this.holders.forEach(x -> x.getLevels().add(this));
  }

  public int getLevelId() {
    return levelId;
  }

  public void setLevelId(int levelId) {
    this.levelId = levelId;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Badge getBadge() {
    return badge;
  }

  public void setBadge(Badge badge) {
    this.badge = badge;
  }

  public Set<User> getHolders() {
    return holders;
  }

  public void setHolders(Set<User> holders) {
    this.holders = holders;
  }
}
