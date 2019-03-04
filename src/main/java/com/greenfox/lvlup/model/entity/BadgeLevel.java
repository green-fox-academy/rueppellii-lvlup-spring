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

    public BadgeLevel(int level) {
      this.level = level;
    }

    public BadgeLevel(int level, String description, Badge badge, Archetype... archetypes) {
        this.level = level;
        this.description = description;
        this.badge = badge;
        this.archetypes = Stream.of(archetypes).collect(Collectors.toSet());
        this.archetypes.forEach(x -> x.getBadgeLevels().add(this));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
