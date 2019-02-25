package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.mockdto.PitchPostDTO;
import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.SuccessfulQuery;
import javax.validation.Valid;

import com.greenfox.lvlup.model.mockdto.PitchPutDTO;
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

  @PostMapping(value = "/pitch", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addPitch(@RequestHeader(value = "userTokenAuth") String token,
      @Valid @RequestBody PitchPostDTO pitchPostDTO) throws Exception {
    if (token.isEmpty()) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
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

  @GetMapping("/pitches-test")
  public ResponseEntity getPitchesTest(@RequestHeader(value = "userTokenAuth", required = false) String token, @RequestParam long id) throws GeneralException {
    if (token != null && !token.equals("")) {
      return new ResponseEntity<>(service.getUserPitchById(id), HttpStatus.OK);
    } else throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}

