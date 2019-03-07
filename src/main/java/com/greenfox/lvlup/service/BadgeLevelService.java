package com.greenfox.lvlup.service;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.archetype.BadgeLevelForArchetypeDTO;
import com.greenfox.lvlup.model.entity.Archetype;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.repositrory.BadgeLevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BadgeLevelService {
  private ModelMapper modelMapper;
  private BadgeLevelRepository badgeLevelRepository;

  @Autowired
  public BadgeLevelService(BadgeLevelRepository badgeLevelRepository) {
    this.badgeLevelRepository = badgeLevelRepository;
  }

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

  public BadgeLevel findBadgeLevelByLevelAndBadge(int oldLvl, Badge badge) throws GeneralException {
    BadgeLevel badgeLevel = badgeLevelRepository.findBadgeLevelByLevelAndBadge(oldLvl, badge);
    if(badgeLevel == null) throw new GeneralException("This badge does not have the given level. " +
        "Please modify old level.", HttpStatus.BAD_REQUEST);
    return badgeLevel;
  }

  public BadgeLevel findBadgeLevelByPitchedLevelAndBadge(int newLvl, Badge badge) throws GeneralException {
    BadgeLevel badgeLevel = badgeLevelRepository.findBadgeLevelByLevelAndBadge(newLvl, badge);
    if(badgeLevel == null) throw new GeneralException("The pitched level does not exit to this badge. Please modify the piched level.", HttpStatus.BAD_REQUEST);
    return badgeLevel;
  }

}
