package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.model.entityTestingDto.BadgeDTO;
import com.greenfox.lvlup.model.entityTestingDto.BadgeLevelDTO;
import com.greenfox.lvlup.repositrory.BadgeLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BadgeLevelService {
    private BadgeLevelRepository badgeLevelRepository;
    private UserService userService;

    @Autowired
    public BadgeLevelService(BadgeLevelRepository badgeLevelRepository, UserService userService) {
        this.badgeLevelRepository = badgeLevelRepository;
        this.userService = userService;
    }

//3. solution makes BadgeLevelDTO from BadgeLevel
    public BadgeLevelDTO getDTOfromBadgeLevel(BadgeLevel badgeLevel) {
        BadgeLevelDTO badgeLevelDTO = new BadgeLevelDTO();
        badgeLevelDTO.level = badgeLevel.getLevel();
        badgeLevelDTO.description = badgeLevel.getDescription();
        badgeLevelDTO.holders = new HashSet<>();
        //ebben Userek vannak, ezt kellene UserDTO-vá alakítni!
        Set<User> usersOfTheBadgeLevel = badgeLevel.getHolders();
       //badgeLevelDTO.holders = badgeLevel.getHolders();
   //   List<BadgeLevel> levels = badge.getLevels();
       for (User item : usersOfTheBadgeLevel) {
            badgeLevelDTO.holders.add(userService.getDTOfromUser(item));
        }

   /*
   Set<String> movies = new HashSet<>();
movies.add("Avatar");
movies.add("The Lord of the Rings");
movies.add("Titanic");
    */
        return badgeLevelDTO;
    }
/*
  public static void main(String[] args) {
     // Create a HashSet
     Set<String> hset = new HashSet<String>();

     //add elements to HashSet
     hset.add("Chaitanya");
     hset.add("Rahul");
     hset.add("Tim");
     hset.add("Rick");
     hset.add("Harry");

     for (String temp : hset) {
        System.out.println(temp);
     }
  }
 */

/*       public List<BadgeLevelDTO> getDTOListFromBadgeLevels() {
        List<BadgeLevelDTO> badgeLevelDTOs = new ArrayList<>();
        List<BadgeLevel> badgeLevels = badgeLevelRepository.findAll();
        for (BadgeLevel item : badgeLevels) {
            badgeLevelDTOs.add(getDTOfromBadgeLevel(item));
        }
        return badgeLevelDTOs;
    }*/


/*    private List<BadgeLevel> getAlL(){
        return badgeLevelRepository.findAll();
    };*/
}
