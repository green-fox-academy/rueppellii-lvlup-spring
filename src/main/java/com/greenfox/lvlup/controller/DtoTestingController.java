package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.entityTestingDto.BadgeSetDTO;
import com.greenfox.lvlup.model.entityTestingDto.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import com.greenfox.lvlup.repositrory.UserRepository;
import com.greenfox.lvlup.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DtoTestingController {
    private BadgeRepository badgeRepository;
    private UserRepository userRepository;
    private BadgeService badgeService;

    @Autowired
    public DtoTestingController(BadgeRepository badgeRepository, UserRepository userRepository, BadgeService badgeService) {
        this.badgeRepository = badgeRepository;
        this.userRepository = userRepository;
        this.badgeService = badgeService;
    }
//3. solution
    @GetMapping("/testbadgedtossimple")
    public List<BadgeDTO> getAllDtos() {
       return badgeService.getDTOListFromBadge();
    }

    //2. solution makes only a BadgeSetDTO but with the same contenct as findAll() from the repository
    @GetMapping("/testbadgedtos")
    public BadgeSetDTO getAllBadgesWithDto() {
        return badgeService.getAll();
    }

    //1. solution - works only with Entities but it is perfect if you have Jsonignore notation!!
    @GetMapping("/testbadges")
    public List<Badge> getAll() {
        return badgeRepository.findAll();
    }


    @GetMapping("/testusers")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}

