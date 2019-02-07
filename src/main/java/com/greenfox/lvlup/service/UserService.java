package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.UserBadgeDTO;
import com.greenfox.lvlup.model.dto.UserDto;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
  @Autowired
  UserRepository repo;

  ModelMapper mapper = new ModelMapper();

  public Set<UserBadgeDTO> createBadgeSetDTO(long id) {
    User user = this.repo.findById(id).get();
    Set<UserBadgeDTO> badgeSet = new HashSet<>();

   /* mapper.addMappings(new PropertyMap<User, UserBadgeSetDTO>() {
      protected void configure() {
        map().getBadges().add(new UserBadgeDTO());
        //user.getBagdes().stream().forEach(x -> x.getBadge().getName());
      }
    });*/

    for (BadgeLevel bl :
        user.getBagdes()) {
      UserBadgeDTO userBadgeDTO = new UserBadgeDTO();
      userBadgeDTO.setName(bl.getBadge().getName());
      userBadgeDTO.setLevel(bl.getLevel());
      badgeSet.add(userBadgeDTO);
    }
    return badgeSet;
  /*  mapper.addMappings(new PropertyMap<User, UserBadgeSetDTO>() {
      protected void configure(){

map().getBadges().add(new UserBadgeDTO())
user.getLevels().stream().forEach(x -> x.getBadge().getName());
      }
    });*/
  }

 public UserDto getUserDetailsById(long id){
   User user = this.repo.findById(id).orElse(null);
   UserDto dto= mapper.map(user, UserDto.class);
   dto.setBadges(createBadgeSetDTO(id));
   return dto;
 }


}
