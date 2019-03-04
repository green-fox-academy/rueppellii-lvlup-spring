package com.greenfox.lvlup.model.dto.pitches;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PitchDto {
  private long id;
  private Date timestamp;
  private String userName;
  private String badgeName;
  private Integer oldLevel;
  private Integer pitchedLevel;
  private String pitchedMessage;
  private Set<ReviewDto> reviews;
}
