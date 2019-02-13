package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.mockdto.PitchDto;
import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.GeneralMessage;
import javax.validation.Valid;

import com.greenfox.lvlup.model.mockdto.PitchSetDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PitchController {

  private PitchSetDTO pitchSetDTO = new PitchSetDTO();

  @GetMapping("/pitches")
  public ResponseEntity getPitches(@RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (token != null && !token.equals("")) {
      return new ResponseEntity<>(pitchSetDTO, HttpStatus.OK);
    } else throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }

  @PostMapping(value = "/pitch", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addPitch(@RequestHeader(value = "userTokenAuth") String token,
      @Valid @RequestBody PitchDto pitchDto) throws Exception {
    if (token.isEmpty()) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>(new GeneralMessage("Success"), HttpStatus.CREATED);
  }

}

