package com.greenfox.lvlup.service;


import com.greenfox.lvlup.model.dto.UserBadgeDTO;
import com.greenfox.lvlup.model.dto.UserBadgeSetDTO;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExplicitModelMapper {

  @Autowired
  UserRepository repo;

  //ModelMapper mapper = new ModelMapper();

  public UserBadgeSetDTO createBadgeSetDTO(long id) {
    User user = this.repo.findById(id).get();
    UserBadgeSetDTO setDto = new UserBadgeSetDTO();
    for (BadgeLevel bl :
        user.getLevels()) {
      UserBadgeDTO userBadgeDTO = new UserBadgeDTO();
      userBadgeDTO.setName(bl.getBadge().getName());
      userBadgeDTO.setLevel(bl.getLevel());
      setDto.getBadges().add(userBadgeDTO);
    }
    return setDto;
  }

   /* mapper.addMappings(new PropertyMap<User, UserBadgeSetDTO>() {
      protected void configure(){

map().getBadges().add(new UserBadgeDTO())
user.getLevels().stream().forEach(x -> x.getBadge().getName());
      }
    });*/
}
