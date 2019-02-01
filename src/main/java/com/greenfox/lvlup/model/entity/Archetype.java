/*
package com.greenfox.lvlup.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "archetypes")
public class Archetype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String archetypeName;

    @ManyToMany(mappedBy = "archetypes")
    @JsonIgnore
    private Set<BadgeLevel> archetpyeBadgeLevels = new HashSet<>();

    public Archetype() {
    }

    public Archetype(String archetypeName, Set<BadgeLevel> archetpyeBadgeLevels) {
        this.archetypeName = archetypeName;
        this.archetpyeBadgeLevels = archetpyeBadgeLevels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArchetypeName() {
        return archetypeName;
    }

    public void setArchetypeName(String archetypeName) {
        this.archetypeName = archetypeName;
    }

    public Set<BadgeLevel> getArchetpyeBadgeLevels() {
        return archetpyeBadgeLevels;
    }

    public void setArchetpyeBadgeLevels(Set<BadgeLevel> archetpyeBadgeLevels) {
        this.archetpyeBadgeLevels = archetpyeBadgeLevels;
    }
}
*/
