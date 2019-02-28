package com.greenfox.lvlup.model.mockdto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PitchDTO {
  @Id
  @GeneratedValue
  private long id;
  @NotNull(message = "Timestamp is required.")
  String timestamp;
  @NotNull(message = "Username is required")
  String username;
  @NotNull(message = "PitchDTO name is required.")
  String badgeName;
  @NotNull(message = "Old level is required.")
  int oldLVL;
  @NotNull(message = "Pitched level is required.")
  int pitchedLVL;
  @NotNull(message = "PitchDTO message is required.")
  String pitchMessage;
  @NotNull(message = "Holders are required.")
  List<HolderDTO> holders;


  public PitchDTO(@NotNull(message = "Timestamp is required.") String timestamp, @NotNull(message = "Username is required") String username,
                  @NotNull(message = "PitchDTO name is required.") String badgeName, @NotNull(message = "Old level is required.") int oldLVL,
                  @NotNull(message = "Pitched level is required.") int pitchedLVL,
                  @NotNull(message = "PitchDTO message is required.") String pitchMessage, @NotNull(message = "Holders are required.") List<HolderDTO> holders) {
    this.timestamp = timestamp;
    this.username = username;
    this.badgeName = badgeName;
    this.oldLVL = oldLVL;
    this.pitchedLVL = pitchedLVL;
    this.pitchMessage = pitchMessage;
    this.holders = holders;
  }

  public PitchDTO(@NotNull(message = "Timestamp is required.") String timestamp, @NotNull(message = "Username is required") String username,
                  @NotNull(message = "PitchDTO name is required.") String badgeName, @NotNull(message = "Old level is required.") int oldLVL,
                  @NotNull(message = "Pitched level is required.") int pitchedLVL,
                  @NotNull(message = "PitchDTO message is required.") String pitchMessage) {
    this.timestamp = timestamp;
    this.username = username;
    this.badgeName = badgeName;
    this.oldLVL = oldLVL;
    this.pitchedLVL = pitchedLVL;
    this.pitchMessage = pitchMessage;
    this.holders = new ArrayList<HolderDTO>();
    HolderDTO holderDTO1 = new HolderDTO("sandor.vass", null, false);
    holders.add(holderDTO1);
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

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public List<HolderDTO> getHolders() {
    return holders;
  }

  public void setHolders(List<HolderDTO> holders) {
    this.holders = holders;
  }
}
