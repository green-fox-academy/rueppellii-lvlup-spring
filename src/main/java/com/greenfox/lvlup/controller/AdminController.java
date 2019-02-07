package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.SuccessfulQuery;
import com.greenfox.lvlup.model.entityTestingDto.BadgeDTO;
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
/*    private BadgeService badgeService;

    @Autowired
    public AdminController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }*/

    @PostMapping(value = "/admin/add",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBadge(@Valid @RequestBody BadgeDTO dtoToAdd,
                                      @RequestHeader(value = "userTokenAuth", required = false) String token) throws Exception {
        if (token == null || token.equals("")) {
            throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
        } else {
            //Badge badgeToCreate = badgeService.getBadgefromDTO(dtoToAdd);
            //badgeService.createBadge(badgeToCreate);
            return new ResponseEntity<>(new SuccessfulQuery("Success"), HttpStatus.CREATED);
        }

    }
}

