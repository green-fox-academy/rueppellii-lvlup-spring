package com.greenfox.lvlup.model.entity;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    @CreationTimestamp
    private Date dateOfCreation;


    @OneToMany(mappedBy = "badge")
    @JsonIgnore
    private List<BadgeLevel> levels;

    @OneToMany(mappedBy = "badge")
    private List<Pitch> pitches;

    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public List<Pitch> getPitches() {
        return pitches;
    }

    public void setPitches(List<Pitch> pitches) {
        this.pitches = pitches;
    }
}

