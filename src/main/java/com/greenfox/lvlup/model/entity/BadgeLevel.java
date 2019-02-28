package com.greenfox.lvlup.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "badgelevels")
@Getter
@Setter
public class BadgeLevel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int level;
  private String description;
  @ManyToOne
  private Badge badge;
  @ManyToMany
  @JoinTable(name = "badgelevel_user",
      joinColumns = @JoinColumn(name = "badgelevel_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
  private Set<User> holders;
  @OneToMany(mappedBy = "badgeLevel")
  private List<Pitch> pitches;
  @ManyToMany
  @JoinTable(name = "badgelevel_archetype",
      joinColumns = @JoinColumn(name = "badgelevel_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "archetype_id", referencedColumnName = "id"))
  private Set<Archetype> archetypes;

  public BadgeLevel() {
  }

  public BadgeLevel(int level, String description, Badge badge, User... holders) {
    this.level = level;
    this.description = description;
    this.badge = badge;
    this.holders = Stream.of(holders).collect(Collectors.toSet());
    this.holders.forEach(x -> x.getBadgeLevels().add(this));
  }

  public BadgeLevel(int level, String description, Badge badge) {
    this.level = level;
    this.description = description;
    this.badge = badge;
  }

  public BadgeLevel(int level, String description, Badge badge, Archetype... archetypes) {
    this.level = level;
    this.description = description;
    this.badge = badge;
    this.archetypes = Stream.of(archetypes).collect(Collectors.toSet());
    this.archetypes.forEach(x -> x.getBadgeLevels().add(this));
  }
}
