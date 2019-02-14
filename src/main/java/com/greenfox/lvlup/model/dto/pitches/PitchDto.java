package com.greenfox.lvlup.model.dto.pitches;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class PitchDto {
  private long id;
  private Date timestamp;
  private String userName;
  private String badgeName;
  private int oldLevel;
  private int pitchedLevel;
  private String pitchedMessage;
  private Set<ReviewDto> reviews;

}
