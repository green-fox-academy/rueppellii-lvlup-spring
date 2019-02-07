package com.greenfox.lvlup.model.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class ArchetypeDTO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  private Long id;
  private String name;
  private List<BadgeLevelDTO> badgeLevels;

  public ArchetypeDTO() {
  }

  public ArchetypeDTO(String name, List<BadgeLevelDTO> badgeLevels) {
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

  public List<BadgeLevelDTO> getBadgeLevels() {
    return badgeLevels;
  }

  public void setBadgeLevels(List<BadgeLevelDTO> badgeLevels) {
    this.badgeLevels = badgeLevels;
  }
}
