package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.archetype.BadgeLevelForArchetypeDTO;
import com.greenfox.lvlup.model.entity.Archetype;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BadgeLevelService {

  @Autowired
  ModelMapper modelMapper;

  public List<BadgeLevelForArchetypeDTO> convertBadgeLeveltoDTOForArchetype (Archetype archetype) {
    List<BadgeLevelForArchetypeDTO> badgeLevels = new ArrayList<>();
    Set<BadgeLevel> badgeLevelSet = archetype.getBadgeLevels();
    for (BadgeLevel badgeLevel : badgeLevelSet) {
      BadgeLevelForArchetypeDTO badgeLevelForArchetypeDTO = modelMapper.map(badgeLevel, BadgeLevelForArchetypeDTO.class);
      badgeLevelForArchetypeDTO.setName(badgeLevel.getBadge().getName());
      badgeLevelForArchetypeDTO.setLevel(badgeLevel.getLevel());
      badgeLevels.add(badgeLevelForArchetypeDTO);
    }
    return badgeLevels;
  }
}
