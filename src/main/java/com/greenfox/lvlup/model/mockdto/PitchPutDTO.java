package com.greenfox.lvlup.model.mockdto;

import javax.validation.constraints.NotEmpty;

public class PitchPutDTO {
    @NotEmpty(message = "Name of pitcher is required.")
    String pitcherName;
    @NotEmpty(message = "Badge level is required.")
    String badgeName;
    @NotEmpty(message = "New status is required.")
    String newStatus;
    @NotEmpty(message = "New message is required.")
    String newMessage;


    public PitchPutDTO(String pitcherName, String badgeName, String newStatus, String newMessage) {
        this.pitcherName = pitcherName;
        this.badgeName = badgeName;
        this.newStatus = newStatus;
        this.newMessage = newMessage;
    }

    public PitchPutDTO() {
    }

    public String getPitcherName() {
        return pitcherName;
    }

    public void setPitcherName(String pitcherName) {
        this.pitcherName = pitcherName;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }
}
