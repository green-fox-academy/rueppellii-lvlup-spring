package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.Archetype;
import com.greenfox.lvlup.model.BadgeLevel;
import com.greenfox.lvlup.model.dto.model.ArchetypeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchetypeService {

  @Autowired
  private ModelMapper modelMapper;

  public Archetype generateArchetype() {
    Archetype archetype1 = new Archetype();
    archetype1.setId(1L);
    archetype1.setName("Boss");
    archetype1.setBadgeLevels(generateBadgeLevels());
    return archetype1;
  }

  public List<BadgeLevel> generateBadgeLevels() {
    List<BadgeLevel> levels = new ArrayList<>();
    BadgeLevel one = new BadgeLevel();
    one.setId(25);
    BadgeLevel two = new BadgeLevel();
    one.setId(32);
    levels.add(one);
    levels.add(two);
    return levels;
  }

  public ArchetypeDTO convertArchetypeToDTO(Archetype archetype) {
    ArchetypeDTO archetypeDTO = modelMapper.map(archetype, ArchetypeDTO.class);
    return archetypeDTO;
  }
}
