package com.greenfox.lvlup.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    private String name;

    @ManyToMany(mappedBy = "holders")
    @JsonIgnore
    private Set<BadgeLevel> badgeLevels = new HashSet<>();

    public User() {
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

    public Set<BadgeLevel> getBadgeLevels() {
        return badgeLevels;
    }

    public void setBadgeLevels(Set<BadgeLevel> badgeLevels) {
        this.badgeLevels = badgeLevels;
    }
}
