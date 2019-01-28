package com.greenfox.lvlup.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PitchDTO {
  @NotNull(message = "PitchDTO name is required.")
  String badgeName;
  @NotNull(message = "Old level is required.")
  int oldLVL;
  @NotNull(message = "Pitched level is required.")
  int pitchedLVL;
  @NotNull(message = "PitchDTO message is required.")
  String pitchMessage;
  @NotNull(message = "Holders are required.")
  List<String> holders;

  public PitchDTO(@NotNull(message = "PitchDTO name is required.") String badgeName, @NotNull(message = "Old level is required.") int oldLVL,
                  @NotNull(message = "Pitched level is required.") int pitchedLVL,
                  @NotNull(message = "PitchDTO message is required.") String pitchMessage, @NotNull(message = "Holders are required.") List<String> holders) {
    this.badgeName = badgeName;
    this.oldLVL = oldLVL;
    this.pitchedLVL = pitchedLVL;
    this.pitchMessage = pitchMessage;
    this.holders = holders;
  }

  public PitchDTO(@NotNull(message = "PitchDTO name is required.") String badgeName, @NotNull(message = "Old level is required.") int oldLVL,
                  @NotNull(message = "Pitched level is required.") int pitchedLVL, @NotNull(message = "PitchDTO message is required.") String pitchMessage) {
    this.badgeName = badgeName;
    this.oldLVL = oldLVL;
    this.pitchedLVL = pitchedLVL;
    this.pitchMessage = pitchMessage;
  }

  public PitchDTO() {
  }

  public String getBadgeName() {
    return badgeName;
  }

  public void setBadgeName(String badgeName) {
    this.badgeName = badgeName;
  }

  public int getOldLVL() {
    return oldLVL;
  }

  public void setOldLVL(int oldLVL) {
    this.oldLVL = oldLVL;
  }

  public int getPitchedLVL() {
    return pitchedLVL;
  }

  public void setPitchedLVL(int pitchedLVL) {
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
