package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.pitches.PitchDto;
import com.greenfox.lvlup.model.mockdto.PitchPostDTO;
import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.GeneralMessage;
import javax.validation.Valid;
import com.greenfox.lvlup.model.mockdto.PitchSetDTO;
import com.greenfox.lvlup.service.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PitchController {

  private PitchSetDTO pitchSetDTO = new PitchSetDTO();

  @Autowired
  PitchService service;

  @GetMapping("/pitches")
  public ResponseEntity getPitches(@RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (token != null && !token.equals("")) {
      return new ResponseEntity<>(pitchSetDTO, HttpStatus.OK);
    } else throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }

  @PostMapping(value = "/api/pitch", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addPitch(@RequestHeader(value = "userTokenAuth") String token,
      @Valid @RequestBody PitchDto pitchDto) {
    service.savePitch(pitchDto);
    return new ResponseEntity<>(new GeneralMessage("Success"), HttpStatus.CREATED);
  }

  @GetMapping("/pitches-test")
  public ResponseEntity getPitchesTest(@RequestHeader(value = "userTokenAuth", required = false) String token, @RequestParam long id) throws GeneralException {
    if (token != null && !token.equals("")) {
      return new ResponseEntity<>(service.getUserPitchById(id), HttpStatus.OK);
    } else throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}

