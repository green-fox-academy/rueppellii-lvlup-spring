package com.greenfox.lvlup.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greenfox.lvlup.model.dto.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Service
public class BadgeServiceImpl implements BadgeService {
    private BadgeRepository badgeRepository;

    @Autowired
    public BadgeServiceImpl(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    @Override
    public BadgeDTO getDTOfromBadge(Badge badge) {
          BadgeDTO badgeDTO = new BadgeDTO();
          badgeDTO.version = badge.getVersion();
          badgeDTO.name = badge.getName();
          badgeDTO.tag = badge.getTag();
          //badgeDTO.levels = new ArrayList<>();
          //??
          return badgeDTO;
        }

    @Override
    public List<BadgeDTO> getDTOListFromEntities() {
        List<BadgeDTO> badgeDTOs = new ArrayList<>();
        List<Badge> badges = badgeRepository.findAll();
        for (Badge badge : badges) {
            badgeDTOs.add(getDTOfromBadge(badge));
        }
        return badgeDTOs;
    }

    @Override
    public Badge getBadgefromDTO(BadgeDTO badgeDTO) {
        Badge badge = new Badge();
        badge.setVersion(badgeDTO.getVersion());
        badge.setName(badgeDTO.getName());
        badge.setTag(badgeDTO.getTag());
        badge.setLevels(new ArrayList<>());
        //badgeRepository.save(badge);
        return badge;
    }

    @Override
    public void createBadge(Badge badge) {
        badgeRepository.save(badge);
    }
}
/*
 @Override
  public List<UserDto> getDtosFromEntities(List<User> users) {
    List<UserDto> userDtos = new ArrayList<>();

    for (User user : users) {
      userDtos.add(getDtoFromEntity(user));
    }
    return userDtos;
  }

  @Override
  public UserDto getDtoFromEntity(User user) {
    UserDto userDto = new UserDto();

    userDto.id = user.getId();
    userDto.username = user.getUsername();
    userDto.password = user.getPassword();
    userDto.calendars = user.getCalendars();
    return userDto;
  }
 */