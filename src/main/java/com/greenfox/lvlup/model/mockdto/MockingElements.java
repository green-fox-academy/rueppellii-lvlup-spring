package com.greenfox.lvlup.model.mockdto;

import java.util.ArrayList;
import java.util.Arrays;

public class MockingElements {

  private String validToken = "token123";
  private String[] holdersArray = {"balazs.jozsef", "benedek.vamosi", "balazs.barna"};
  private String badgeName = "English speaker";
  private String pitchMessage = "Hello World! My English is bloody gorgeous.";

  private PitchPostDTO validPitchPostDTO = new PitchPostDTO(badgeName,
      2,
      3,
      pitchMessage,
      new ArrayList<>(Arrays.asList(holdersArray)));

  private PitchPostDTO invalidPitchPostDTO5 = new PitchPostDTO(badgeName,
      2,
      3,
      pitchMessage);

  private PitchPostDTO invalidPitchPostDTO2 = new PitchPostDTO(badgeName,
      3,
      pitchMessage,
      new ArrayList<>(Arrays.asList(holdersArray)));

  public PitchPostDTO getInvalidPitchPostDTO1() {
    return invalidPitchPostDTO1;
  }

  public void setInvalidPitchPostDTO1(PitchPostDTO invalidPitchPostDTO1) {
    this.invalidPitchPostDTO1 = invalidPitchPostDTO1;
  }

  private PitchPostDTO invalidPitchPostDTO1 = new PitchPostDTO(
      2,
      3,
      pitchMessage,
      new ArrayList<>(Arrays.asList(holdersArray)));

  private PitchPostDTO emptyPitchPostDTO5 = new PitchPostDTO(badgeName,
      2,
      3,
      pitchMessage,
      new ArrayList<>(Arrays.asList()));

  public PitchPostDTO getEmptyPitchPostDTO5() {
    return emptyPitchPostDTO5;
  }

  public void setEmptyPitchPostDTO5(PitchPostDTO emptyPitchPostDTO5) {
    this.emptyPitchPostDTO5 = emptyPitchPostDTO5;
  }

  public String getValidToken() {
    return validToken;
  }

  public void setValidToken(String validToken) {
    this.validToken = validToken;
  }

  public PitchPostDTO getValidPitchPostDTO() {
    return validPitchPostDTO;
  }

  public void setValidPitchPostDTO(PitchPostDTO validPitchPostDTO) {
    this.validPitchPostDTO = validPitchPostDTO;
  }

  public PitchPostDTO getInvalidPitchPostDTO5() {
    return invalidPitchPostDTO5;
  }

  public void setInvalidPitchPostDTO5(PitchPostDTO invalidPitchPostDTO5) {
    this.invalidPitchPostDTO5 = invalidPitchPostDTO5;
  }

  public PitchPostDTO getInvalidPitchPostDTO2() {
    return invalidPitchPostDTO2;
  }

  public void setInvalidPitchPostDTO2(PitchPostDTO invalidPitchPostDTO2) {
    this.invalidPitchPostDTO2 = invalidPitchPostDTO2;
  }

}
