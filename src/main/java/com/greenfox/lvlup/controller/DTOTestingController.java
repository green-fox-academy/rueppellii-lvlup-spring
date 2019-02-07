package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.ArchetypeDTO;
import com.greenfox.lvlup.service.ArchetypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DTOTestingController {

  @Autowired
  ArchetypeService archetypeService;

  @GetMapping("/archetypedto")
  public ArchetypeDTO getArchetypeDTO() {
    return archetypeService.convertArchetypeToDTO(archetypeService.generateArchetype());
  }
}
