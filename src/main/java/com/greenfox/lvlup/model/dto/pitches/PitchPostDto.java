package com.greenfox.lvlup.model.dto.pitches;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PitchPostDto extends PitchDto {
    @NotEmpty(message = "Username is required.")
    private String userName;
    @NotEmpty(message = "Badge name is required.")
    private String badgeName;
    @NotNull(message = "Old level is required.")
    private Integer oldLevel;
    @NotNull(message = "Pitched level is required.")
    private Integer pitchedLevel;
    @NotEmpty(message = "Pitch message is required.")
    private String pitchedMessage;
    @NotEmpty(message = "Reviewers are required.")
    private Set<ReviewDto> reviews;
}
