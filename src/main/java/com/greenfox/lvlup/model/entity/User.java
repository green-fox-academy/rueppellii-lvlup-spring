package com.greenfox.lvlup.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  private String tokenAuth;
  private String pic;
  @ManyToMany(mappedBy = "holders")
  @JsonIgnore
  private Set<BadgeLevel> badgeLevels = new HashSet<>();
  @OneToMany (mappedBy = "user")
  @JsonIgnore
  private List<Pitch> pitches;

  public User(String name, String tokenAuth, String pic) {
    this.name = name;
    this.tokenAuth = tokenAuth;
    this.pic = pic;
  }

  public User(String name) {
    this.name = name;
  }
}
