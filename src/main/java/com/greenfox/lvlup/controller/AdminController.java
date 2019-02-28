package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.GeneralMessage;
import com.greenfox.lvlup.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.greenfox.lvlup.model.dto.library.BadgeDTO;
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

  @Autowired
  public AdminController(BadgeService badgeService) {
    this.badgeService = badgeService;
  }

  @PostMapping(value = "/admin/add",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> addBadge(@Valid @RequestBody BadgeDTO badgeDTO,
                                    @RequestHeader(value = "userTokenAuth", required = false) String token) throws Exception {
    if (token == null || token.equals("")) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    badgeService.createBadge(badgeDTO, token);
    return new ResponseEntity<>(new GeneralMessage("Success"), HttpStatus.CREATED);
  }
}
