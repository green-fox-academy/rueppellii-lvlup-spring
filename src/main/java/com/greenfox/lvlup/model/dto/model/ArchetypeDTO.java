package com.greenfox.lvlup.model.dto.model;

import com.greenfox.lvlup.model.BadgeLevel;

import java.util.List;
import java.util.Set;

public class ArchetypeDTO {

  private String name;
  private List<BadgeLevelDTO> badgeLevels;

  public ArchetypeDTO() {
  }

  public ArchetypeDTO(String name, List<BadgeLevelDTO> badgeLevels) {
    this.name = name;
    this.badgeLevels = badgeLevels;
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
