package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.Archetype;
import com.greenfox.lvlup.repository.ArchetypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DTOTestingController {

  private ArchetypeRepository archetypeRepository;

  @Autowired
  public DTOTestingController(ArchetypeRepository archetypeRepository) {
    this.archetypeRepository = archetypeRepository;
  }

  @GetMapping("/testarchetype")
  public Archetype getArchetype() {
    return new Archetype();
  }
}
