package com.greenfox.lvlup.model.dto.archetype;

import java.util.List;

public class ArchetypeDTO {

    private Long id;
    private String name;
    private List<BadgeLevelForArchetypeDTO> badges;

    public ArchetypeDTO() {
    }

    public ArchetypeDTO(String name, List<BadgeLevelForArchetypeDTO> badges) {
      this.name = name;
      this.badges = badges;
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

    public List<BadgeLevelForArchetypeDTO> getBadges() {
      return badges;
    }

    public void setBadges(List<BadgeLevelForArchetypeDTO> badges) {
      this.badges = badges;
    }
  }
