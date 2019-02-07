package com.greenfox.lvlup.model.entity;

import com.greenfox.lvlup.model.entity.BadgeLevel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "archetypes")
public class Archetype {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "level_id")
  private List<BadgeLevel> badgeLevels;

  public Archetype() {
  }

  public Archetype(String name, List<BadgeLevel> badgeLevels) {
    this.name = name;
    this.badgeLevels = badgeLevels;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<BadgeLevel> getBadgeLevels() {
    return badgeLevels;
  }

  public void setBadgeLevels(List<BadgeLevel> badgeLevels) {
    this.badgeLevels = badgeLevels;
  }
}
