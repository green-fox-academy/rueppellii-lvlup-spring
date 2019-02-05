package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.SuccessfulQuery;
import com.greenfox.lvlup.model.entityTestingDto.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import com.greenfox.lvlup.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminController {
    private BadgeService badgeService;
    private BadgeRepository badgeRepository;

    @Autowired
    public AdminController(BadgeService badgeService, BadgeRepository badgeRepository) {
        this.badgeService = badgeService;
        this.badgeRepository = badgeRepository;
    }

    @PostMapping(value = "/admin/add",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBadge(@Valid @RequestBody BadgeDTO dtoToAdd,
                                      @RequestHeader(value = "userTokenAuth", required = false) String token) throws Exception {
        if (token == null || token.equals("")) {
            throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
        } else {
            Badge badgeToCreate = badgeService.getBadgefromDTO(dtoToAdd);
            System.out.println(badgeToCreate.getName());
            System.out.println(badgeToCreate.getId());
            System.out.println(badgeToCreate.getVersion());
            System.out.println(badgeToCreate.getTag());
            System.out.println(badgeToCreate.getLevels());
            badgeService.createBadge(badgeToCreate);
            //badgeRepository.save(badgeToCreate);
            return new ResponseEntity<>(new SuccessfulQuery("Success"), HttpStatus.CREATED);
        }

    }
}

