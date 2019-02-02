package com.greenfox.lvlup.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(exclude = "holders")
@Entity
@Table(name = "badgelevels")
public class BadgeLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @JsonIgnore
    private String name;
    private int level;
    private String description;

    @ManyToOne
    @JsonIgnore
    private Badge badge;

    //@JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "badgelevel_user",
            joinColumns = @JoinColumn(name = "badgelevel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> holders;

    public BadgeLevel() {
    }

    public BadgeLevel(String name, int level, String description, Badge badge, User... holders) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.badge = badge;
        this.holders = Stream.of(holders).collect(Collectors.toSet());
        this.holders.forEach(x -> x.getBagdes().add(this));
    }

    public BadgeLevel(String name, int level, String description, Badge badge) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.badge = badge;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
