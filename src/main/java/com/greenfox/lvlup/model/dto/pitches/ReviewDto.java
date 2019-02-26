package com.greenfox.lvlup.model.dto.pitches;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
  private String name;
  private String message;
  private boolean pitchStatus;
}
