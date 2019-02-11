package com.greenfox.lvlup.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue
    private Long id;
    private String version;
    private String name;
    private String tag;
    @OneToMany(mappedBy = "badge")
    private List<BadgeLevel> levels;

    public Badge() {
        this.levels = new ArrayList<>();
    }

    public Badge(String version, String name, String tag) {
        this();
        this.version = version;
        this.name = name;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
