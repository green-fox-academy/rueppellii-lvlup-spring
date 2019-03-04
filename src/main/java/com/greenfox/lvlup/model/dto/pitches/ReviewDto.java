package com.greenfox.lvlup.model.dto.pitches;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
  private String name;
  private String message;
  private boolean pitchStatus;
}
