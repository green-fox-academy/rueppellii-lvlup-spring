package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.pitches.PitchDto;
import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.SuccessfulQuery;
import javax.validation.Valid;
import com.greenfox.lvlup.model.mockdto.PitchPutDTO;
import com.greenfox.lvlup.service.implementation.PitchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PitchController {

  @Autowired
  PitchServiceImpl service;

  @PostMapping(value = "/api/pitch", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addPitch(@RequestHeader(value = "userTokenAuth") String token,
      @Valid @RequestBody PitchDto pitchDto) throws Exception {
    if (token.isEmpty()) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    service.savePitch(pitchDto);
    return new ResponseEntity(new SuccessfulQuery("Success"), HttpStatus.CREATED);
  }

  @PutMapping(value = "/pitch", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> modifyPitch(@RequestHeader(value = "userTokenAuth", required = false) String token,
                                            @Valid @RequestBody PitchPutDTO pitchPutDTO) throws Exception {
    if (token.isEmpty() || token == null) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>(new SuccessfulQuery("Success"), HttpStatus.CREATED);
  }

  @GetMapping("/pitches")
  public ResponseEntity getPitches(@RequestHeader(value = "userTokenAuth", required = false) String token, @RequestParam long id) throws GeneralException {
    if (token != null && !token.equals("")) {
      return new ResponseEntity<>(service.getPitchesByUserId(id), HttpStatus.OK);
    } else throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}

