package com.greenfox.lvlup.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize
public class BadgeDTO {
    @NotBlank(message= "PitchDTO version must not be blank!")
    public String version;
    @NotBlank(message= "PitchDTO name must not be blank!")
    public String name;
    @NotBlank(message= "PitchDTO tag must not be blank!")
    public String tag;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<String> levels;

  public BadgeDTO() {
    levels = new ArrayList<>();
  }

  public BadgeDTO(String version, String name, String tag, List<String> levels) {
    this.version = version;
    this.name = name;
    this.tag = tag;
    this.levels = levels;
  }

  public BadgeDTO(@NotBlank(message = "PitchDTO version must not be blank!") String version, @NotBlank(message = "PitchDTO name must not be blank!") String name, @NotBlank(message = "PitchDTO tag must not be blank!") String tag) {
    this.version = version;
    this.name = name;
    this.tag = tag;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public List<String> getLevels() {
    return levels;
  }

  public void setLevels(List<String> levels) {
    this.levels = levels;
  }
}
