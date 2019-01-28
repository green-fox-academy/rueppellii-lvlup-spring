package com.greenfox.lvlup.model;

import java.util.ArrayList;
import java.util.Arrays;

public class MockingElements {
  private String validToken = "token123";

  private PitchDTO validPitchDTO = new PitchDTO("English speaker",
      2,
      3,
      "Hello World! My English is bloody gorgeous.",
      new ArrayList<>(Arrays.asList("balazs.jozsef", "benedek.vamosi", "balazs.barna")));

  private PitchDTO invalidPitchDTO = new PitchDTO("English speaker",
      2,
      3,
      "Hello World! My English is bloody gorgeous.");

  public String getValidToken() {
    return validToken;
  }

  public void setValidToken(String validToken) {
    this.validToken = validToken;
  }

  public PitchDTO getValidPitchDTO() {
    return validPitchDTO;
  }

  public void setValidPitchDTO(PitchDTO validPitchDTO) {
    this.validPitchDTO = validPitchDTO;
  }

  public PitchDTO getInvalidPitchDTO() {
    return invalidPitchDTO;
  }

  public void setInvalidPitchDTO(PitchDTO invalidPitchDTO) {
    this.invalidPitchDTO = invalidPitchDTO;
  }
}
