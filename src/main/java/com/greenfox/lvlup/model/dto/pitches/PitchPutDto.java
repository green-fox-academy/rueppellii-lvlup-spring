package com.greenfox.lvlup.model.dto.pitches;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PitchPutDto extends PitchDto {
  @NotEmpty(message = "Username name is required.")
  private String userName;
  @NotEmpty(message = "Badge name is required.")
  private String badgeName;
  @NotNull(message = "Old level is required.")
  private int oldLevel;
  @NotNull(message = "Pitched level is required.")
  private int pitchedLevel;
  @NotEmpty(message = "Pitch message is required.")
  private String pitchedMessage;
  @NotEmpty(message = "Reviewers are required.")
  private Set<ReviewDto> reviews;
}
