package com.greenfox.lvlup.service;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.user.UserBadgeDTO;
import com.greenfox.lvlup.model.dto.user.UserDto;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
  private UserRepository repository;
  private ModelMapper mapper;

  @Autowired
  public UserService(UserRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public User findUserByTokenAuth(String tokenAuth) throws GeneralException {
    Optional<User> user = repository.findByTokenAuth(tokenAuth);
    if(user.isPresent()) {
      return user.get();
    }
    throw new GeneralException("User was not found.", HttpStatus.NOT_FOUND);
  }

  public UserDto getUserDetailsById(long id) {
    User user = this.repository.findById(id).orElse(null);
    UserDto dto = mapper.map(user, UserDto.class);
    dto.setBadges(getUserBadgeDTOs(user));
    return dto;
  }

  public Set<UserBadgeDTO> getUserBadgeDTOs(User user) {
    Set<UserBadgeDTO> badgeSet = new HashSet<>();
    for (BadgeLevel bl : user.getBadgeLevels()) {
      UserBadgeDTO dto = mapper.map(bl, UserBadgeDTO.class);
      dto.setName(bl.getBadge().getName());
      badgeSet.add(dto);
    }
    return badgeSet;
  }

}
