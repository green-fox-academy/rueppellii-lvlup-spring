package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.UserBadgeSetDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhateverController {

//    @GetMapping(value = "/whatever")
//    public String hello() {
//        return "hello";
//    }


    private UserBadgeSetDTO testUserBadges = new UserBadgeSetDTO();

    @GetMapping(value = "/whatever")
    public ResponseEntity<?> hello() {
         return new ResponseEntity<>(testUserBadges, HttpStatus.OK);
    }
}
