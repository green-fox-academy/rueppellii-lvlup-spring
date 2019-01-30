package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.MockingElements;
import com.greenfox.lvlup.model.SuccessfulQuery;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PitchController {

  @PostMapping(value = "/pitch", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addPitch(@RequestHeader(value = "userTokenAuth") String token,
      @Valid @RequestBody PitchDto pitchDto) throws Exception {
    if (token.isEmpty()) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity(new SuccessfulQuery("Success"), HttpStatus.CREATED);
  }

}
