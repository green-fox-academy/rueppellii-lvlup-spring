package com.greenfox.lvlup.model.mockdto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PitchPutDTO {
    @NotEmpty(message = "Name of pitcher is required.")
    String pitcherName;
    @NotNull(message = "Badge level is required.")
    String badgeName;
    @NotNull(message = "New status is required.")
    String newStatus;
    @NotEmpty(message = "New message is required.")
    String newMessage;
}
