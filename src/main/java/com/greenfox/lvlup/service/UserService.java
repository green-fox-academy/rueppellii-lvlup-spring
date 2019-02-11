package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.UserBadgeDTO;
import com.greenfox.lvlup.model.dto.user.UserDto;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
  private UserRepository repository;
  private ModelMapper mapper;

  @Autowired
  public UserService(UserRepository badgeRepository, ModelMapper mapper) {
    this.repository = badgeRepository;
    this.mapper = mapper;
  }

  public UserDto getUserDetailsById(long id) {
    User user = this.repository.findById(id).orElse(null);
    UserDto dto = mapper.map(user, UserDto.class);
    dto.setBadges(getUserBadgeDTOs(user));
    return dto;
  }

  public Set<UserBadgeDTO> getUserBadgeDTOs(User user) {
    Set<UserBadgeDTO> badgeSet = new HashSet<>();
    for (BadgeLevel bl :
        user.getBadgeLevels()) {
      UserBadgeDTO dto = mapper.map(bl, UserBadgeDTO.class);
      badgeSet.add(dto);
    }
    return badgeSet;
  }
}
