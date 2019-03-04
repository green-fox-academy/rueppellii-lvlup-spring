package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.archetype.ArchetypeDTO;
import com.greenfox.lvlup.model.entity.Archetype;
import com.greenfox.lvlup.repositrory.ArchetypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchetypeService {

  ArchetypeRepository archetypeRepository;
  BadgeLevelService badgeLevelService;

  @Autowired
  public ArchetypeService(ArchetypeRepository archetypeRepository, BadgeLevelService badgeLevelService) {
    this.archetypeRepository = archetypeRepository;
    this.badgeLevelService = badgeLevelService;
  }

  @Autowired
  private ModelMapper modelMapper;

  public List<ArchetypeDTO> convertArchetypeToDTO() {
    List<Archetype> archetypes = archetypeRepository.findAll();
    List<ArchetypeDTO> archetypeDTOList = new ArrayList<>();
    for (Archetype archetype: archetypes) {
      ArchetypeDTO archetypeDTO = modelMapper.map(archetype, ArchetypeDTO.class);
      archetypeDTOList.add(archetypeDTO);
      archetypeDTO.setBadges(badgeLevelService.convertBadgeLeveltoDTOForArchetype(archetype));
    }
    return archetypeDTOList;
  }
}
