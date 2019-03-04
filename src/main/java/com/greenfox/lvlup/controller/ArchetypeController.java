package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.archetype.ArchetypeDTO;
import com.greenfox.lvlup.model.entity.Archetype;
import com.greenfox.lvlup.repositrory.ArchetypeRepository;
import com.greenfox.lvlup.service.ArchetypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArchetypeController {

  ArchetypeRepository archetypeRepository;
  ArchetypeService archetypeService;

  @Autowired
  public ArchetypeController(ArchetypeRepository archetypeRepository, ArchetypeService archetypeService) {
    this.archetypeRepository = archetypeRepository;
    this.archetypeService = archetypeService;
  }

 @GetMapping("/archetypes")
  public List<Archetype> getArchetypes() {
   return archetypeRepository.findAll();
 }

 @GetMapping("/archetypesdto")
  public List<ArchetypeDTO> getArchetypesDTO() {
   return archetypeService.convertArchetypeToDTO();
 }
}
