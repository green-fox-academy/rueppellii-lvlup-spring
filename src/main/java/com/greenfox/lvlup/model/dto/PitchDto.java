package com.greenfox.lvlup.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonSerialize
public class PitchDto {
  @NotEmpty(message = "PitchDto name is required.")
  String badgeName;
  @NotNull(message = "Old level is required.")
  Integer oldLVL;
  @NotNull(message = "Pitched level is required.")
  Integer pitchedLVL;
  @NotEmpty(message = "PitchDto message is required.")
  String pitchMessage;
  @NotEmpty(message = "Holders are required.")
  List<String> holders;

  public PitchDto(String badgeName, int oldLVL, int pitchedLVL, String pitchMessage, List<String> holders) {
    this.badgeName = badgeName;
    this.oldLVL = oldLVL;
    this.pitchedLVL = pitchedLVL;
    this.pitchMessage = pitchMessage;
    this.holders = holders;
  }

  public PitchDto(String badgeName, int oldLVL, int pitchedLVL, String pitchMessage) {
    this.badgeName = badgeName;
    this.oldLVL = oldLVL;
    this.pitchedLVL = pitchedLVL;
    this.pitchMessage = pitchMessage;
  }

  public PitchDto(String badgeName, int pitchedLVL, String pitchMessage, List<String> holders) {
    this.badgeName = badgeName;
    this.pitchedLVL = pitchedLVL;
    this.pitchMessage = pitchMessage;
    this.holders = holders;
  }

  public PitchDto(int oldLVL, int pitchedLVL, String pitchMessage, List<String> holders) {
    this.oldLVL = oldLVL;
    this.pitchedLVL = pitchedLVL;
    this.pitchMessage = pitchMessage;
    this.holders = holders;
  }

  public PitchDto() {
  }

  public String getBadgeName() {
    return badgeName;
  }

  public void setBadgeName(String badgeName) {
    this.badgeName = badgeName;
  }

  public Integer getOldLVL() {
    return oldLVL;
  }

  public void setOldLVL(Integer oldLVL) {
    this.oldLVL = oldLVL;
  }

  public Integer getPitchedLVL() {
    return pitchedLVL;
  }

  public void setPitchedLVL(Integer pitchedLVL) {
    this.pitchedLVL = pitchedLVL;
  }

  public String getPitchMessage() {
    return pitchMessage;
  }

  public void setPitchMessage(String pitchMessage) {
    this.pitchMessage = pitchMessage;
  }

  public List<String> getHolders() {
    return holders;
  }

  public void setHolders(List<String> holders) {
    this.holders = holders;
  }
}
