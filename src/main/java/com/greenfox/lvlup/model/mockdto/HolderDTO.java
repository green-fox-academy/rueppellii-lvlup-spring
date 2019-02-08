package com.greenfox.lvlup.model.mockdto;

import javax.validation.constraints.NotNull;

public class HolderDTO {

  @NotNull(message = "Name is required.")
  String name;
  @NotNull(message = "Message is required.")
  String message;
  @NotNull(message = "Name is required.")
  Boolean pitchStatus;

  public HolderDTO(@NotNull(message = "Name is required.") String name, @NotNull(message = "Message is required.") String message, @NotNull(message = "Name is required.") Boolean pitchStatus) {
    this.name = name;
    this.message = message;
    this.pitchStatus = pitchStatus;
  }

  public HolderDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean getPitchStatus() {
    return pitchStatus;
  }

  public void setPitchStatus(Boolean pitchStatus) {
    this.pitchStatus = pitchStatus;
  }
}
