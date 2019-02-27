package com.greenfox.lvlup.model.mockdto;

import com.greenfox.lvlup.model.dto.pitches.PitchPostDto;
import com.greenfox.lvlup.model.dto.pitches.ReviewDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MockingElements {

  private String validToken = "token123";
  private String[] holdersArray = {"balazs.jozsef", "benedek.vamosi", "balazs.barna"};
  private String badgeName = "English speaker";
  private String pitchMessage = "Hello World! My English is bloody gorgeous.";

  private PitchPostDto dto;

  public PitchPostDto getFullPostPitchDto() {
    dto = new PitchPostDto();
    dto.setBadgeName("testbadge");
    dto.setOldLevel(1);
    dto.setPitchedLevel(2);
    dto.setPitchedMessage("test message");
    ReviewDto review = new ReviewDto("testuser1", "test message", false);
    HashSet<ReviewDto> set = new HashSet<>();
    set.add(review);
    dto.setReviews(set);
    dto.setUserName("testuser2");

    return dto;
  }

  public PitchPostDto getPitchPostDtoWithoutOldLvl() {
    PitchPostDto dto2 = new PitchPostDto();
    dto2.setBadgeName("testbadge");
    dto2.setPitchedLevel(2);
    dto2.setPitchedMessage("test message");
    ReviewDto review = new ReviewDto("testuser1", "test message", false);
    HashSet<ReviewDto> set = new HashSet<>();
    set.add(review);
    dto2.setReviews(set);
    dto2.setUserName("testuser2");

    return dto2;
  }

  public PitchPostDto getPitchPostDtoWithoutBadgeName() {
    dto = new PitchPostDto();

    dto.setOldLevel(1);
    dto.setPitchedLevel(2);
    dto.setPitchedMessage("test message");
    ReviewDto review = new ReviewDto("testuser1", "test message", false);
    HashSet<ReviewDto> set = new HashSet<>();
    set.add(review);
    dto.setReviews(set);
    dto.setUserName("testuser2");

    return dto;
  }

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
