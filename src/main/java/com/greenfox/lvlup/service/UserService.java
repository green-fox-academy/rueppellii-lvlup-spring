package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.UserBadgeDTO;
import com.greenfox.lvlup.model.dto.UserDto;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
  @Autowired
  UserRepository repo;

  ModelMapper mapper = new ModelMapper();

  public UserDto getUserDetailsById(long id) {
    User user = this.repo.findById(id).orElse(null);
    UserDto dto = mapper.map(user, UserDto.class);
    dto.setBadges(getUserBadgeDTOs(user));
    //not needed
    //mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    return dto;
  }

  public Set<UserBadgeDTO> getUserBadgeDTOs(User user) {
    Set<UserBadgeDTO> badgeSet = new HashSet<>();

    for (BadgeLevel bl :
        user.getBagdes()) {
//      not needed
//      UserBadgeDTO userBadgeDTO = new UserBadgeDTO();
//      userBadgeDTO.setName(bl.getBadge().getKiskutyus());
//      userBadgeDTO.setLevel(bl.getLevel());

      //  1
     /* mapper.addMappings(new PropertyMap<BadgeLevel, UserBadgeDTO>() {
        protected void configure() {
          map().setName(source.getBadge().getKiskutyus());
        }
      });*/

      //  2
//      mapper.addMappings(mapper -> {
//        mapper.map(src -> src.getBillingAddress().getStreet(),
//            Destination::setBillingStreet);
//        mapper.map(src -> src.getBillingAddress().getCity(),
//            Destination::setBillingCity);
//      });

      UserBadgeDTO dto = mapper.map(bl, UserBadgeDTO.class);
      //dto.setName(bl.getBadge().getKiskutyus());
      badgeSet.add(dto);
    }

    return badgeSet;
  }

  //didn't work
/*  public Set<UserBadgeDTO> getUserBadgeDTOsExplicitly(User user) {
    Set<UserBadgeDTO> badgeSet = new HashSet<>();
    UserBadgeDTO dto = mapper.map(new BadgeLevel(), UserBadgeDTO.class);
    mapper.addMappings(mapper -> {
      mapper.map(user.getBagdes().forEach(badge. ->user.getBagdes(),
          UserBadgeDTO::setKiskutyus);
//      mapper.map(src -> src.getBillingAddress().getCity(),
//          Destination::setBillingCity);
    });
    return badgeSet;
  }*/

}
