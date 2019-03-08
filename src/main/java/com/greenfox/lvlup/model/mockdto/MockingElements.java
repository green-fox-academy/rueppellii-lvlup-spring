package com.greenfox.lvlup.model.mockdto;

import com.greenfox.lvlup.model.dto.pitches.PitchDto;

import java.util.ArrayList;
import java.util.Arrays;

public class MockingElements {

  private String validToken = "token123";
  private String[] holdersArray = {"balazs.jozsef", "benedek.vamosi", "balazs.barna"};
  private String badgeName = "English speaker";
  private String pitchMessage = "Hello World! My English is bloody gorgeous.";

  private PitchDto validPitchDto = new PitchDto(badgeName,
      2,
      3,
      pitchMessage,
      new ArrayList<>(Arrays.asList(holdersArray)));

  private PitchDto invalidPitchDto5 = new PitchDto(badgeName,
      2,
      3,
      pitchMessage);

  private PitchDto invalidPitchDto2 = new PitchDto(badgeName,
      3,
      pitchMessage,
      new ArrayList<>(Arrays.asList(holdersArray)));

  public PitchDto getInvalidPitchDto1() {
    return invalidPitchDto1;
  }

  public void setInvalidPitchDto1(PitchDto invalidPitchDto1) {
    this.invalidPitchDto1 = invalidPitchDto1;
  }

  private PitchDto invalidPitchDto1 = new PitchDto(
      2,
      3,
      pitchMessage,
      new ArrayList<>(Arrays.asList(holdersArray)));

  private PitchDto emptyPitchDto5 = new PitchDto(badgeName,
      2,
      3,
      pitchMessage,
      new ArrayList<>(Arrays.asList()));

  public PitchDto getEmptyPitchDto5() {
    return emptyPitchDto5;
  }

  public void setEmptyPitchDto5(PitchDto emptyPitchDto5) {
    this.emptyPitchDto5 = emptyPitchDto5;
  }

  public String getValidToken() {
    return validToken;
  }

  public void setValidToken(String validToken) {
    this.validToken = validToken;
  }

  public PitchDto getValidPitchDto() {
    return validPitchDto;
  }

  public void setValidPitchDto(PitchDto validPitchDto) {
    this.validPitchDto = validPitchDto;
  }

  public PitchDto getInvalidPitchDto5() {
    return invalidPitchDto5;
  }

  public void setInvalidPitchDto5(PitchDto invalidPitchDto5) {
    this.invalidPitchDto5 = invalidPitchDto5;
  }

  public PitchDto getInvalidPitchDto2() {
    return invalidPitchDto2;
  }

  public void setInvalidPitchDto2(PitchDto invalidPitchDto2) {
    this.invalidPitchDto2 = invalidPitchDto2;
  }

}
