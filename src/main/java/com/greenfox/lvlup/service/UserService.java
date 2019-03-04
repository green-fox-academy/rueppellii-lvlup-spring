package com.greenfox.lvlup.service;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.user.UserBadgeDTO;
import com.greenfox.lvlup.model.dto.user.UserDto;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repository.BadgeLevelRepository;
import com.greenfox.lvlup.repository.BadgeRepository;
import com.greenfox.lvlup.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

  private UserRepository repo;
  private BadgeLevelRepository badgeLevelRepository;
  private BadgeRepository badgeRepository;
  private UserRepository repository;
  private ModelMapper mapper;

  @Autowired
  public UserService(UserRepository repo, BadgeLevelRepository badgeLevelRepository, BadgeRepository badgeRepository) {
    this.repo = repo;
    this.badgeLevelRepository = badgeLevelRepository;
    this.badgeRepository = badgeRepository;
  }

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

  public List<UserBadgeDTO> convertBadgeToDTO(List <BadgeLevel> badgeList) {
    List<UserBadgeDTO> userBadgeDTOS = new ArrayList<>();
    for (BadgeLevel badgeLevel : badgeList) {
      UserBadgeDTO userBadgeDTO = mapper.map(badgeLevel, UserBadgeDTO.class);
      userBadgeDTO.setName(badgeLevel.getBadge().getName());
      userBadgeDTOS.add(userBadgeDTO);
    }
    return userBadgeDTOS;
  }

  public List<BadgeLevel> getBadges(Long id) {
    User user = repo.findById(id).orElse(null);
    List<BadgeLevel> badgeLevels = badgeLevelRepository.findAllByHoldersContaining(user);
    return badgeLevels;
  }
}
