package com.greenfox.lvlup.model.dto.pitches;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
  private String name;
  private String message;
  private boolean pitchStatus;
}
