package com.greenfox.lvlup.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "archetypes")
public class Archetype {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "level_id")
  private Set<BadgeLevel> badgeLevels;

  public Archetype() {
  }

  public Archetype(String name, Set<BadgeLevel> badgeLevels) {
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

  public Set<BadgeLevel> getBadgeLevels() {
    return badgeLevels;
  }

  public void setBadgeLevels(Set<BadgeLevel> badgeLevels) {
    this.badgeLevels = badgeLevels;
  }
}
