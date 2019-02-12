package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.SuccessfulQuery;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.service.BadgeService;
import com.greenfox.lvlup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AdminController {
    private BadgeService badgeService;
    private UserService userService;

    @Autowired
    public AdminController(BadgeService badgeService, UserService userService) {
        this.badgeService = badgeService;
        this.userService = userService;
    }

    @PostMapping(value = "/admin/add",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBadge(@Valid @RequestBody BadgeDTO badgeDTO,
                                      @RequestHeader(value = "userTokenAuth", required = false) String token) throws Exception {
        if (token == null || token.equals("")) {
            throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
        } else {
            Badge badgeToCreate = badgeService.convertBadgeDTOToBadge(badgeDTO);
            //User Id should be extracted from token!!
            User user = userService.findUserById(1);
            //badgeService.createBadge(badgeToCreate);
            badgeService.createBadge(badgeToCreate, user);
            return new ResponseEntity<>(new SuccessfulQuery("Success"), HttpStatus.CREATED);
        }

    }
}
