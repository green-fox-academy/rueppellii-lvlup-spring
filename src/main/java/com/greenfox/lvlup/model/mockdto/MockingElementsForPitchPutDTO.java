package com.greenfox.lvlup.model.mockdto;

public class MockingElementsForPitchPutDTO {
    private String validToken = "testtoken123";
    private String pitcherName = "Test Name";
    private String badgeName = "Test Badge Name";
    private String newStatus = "Test Status";
    private String newMessage = "Test Message";

    public PitchPutDTO generateValidPitchPutDTO() {
        return new PitchPutDTO(pitcherName, badgeName, newStatus, newMessage);
    }

    public PitchPutDTO generatePitchPutDTOWithoutPitcherName() {
        PitchPutDTO pitchPutDTOWithoutPitcherName = new PitchPutDTO();
        pitchPutDTOWithoutPitcherName.setBadgeName(badgeName);
        pitchPutDTOWithoutPitcherName.setNewStatus(newStatus);
        pitchPutDTOWithoutPitcherName.setNewMessage(newMessage);
        return pitchPutDTOWithoutPitcherName;
    }

    public PitchPutDTO generatePitchPutDTOWithoutBadgeName() {
        PitchPutDTO pitchPutDTOWithoutBadgeName = new PitchPutDTO();
        pitchPutDTOWithoutBadgeName.setPitcherName(pitcherName);
        pitchPutDTOWithoutBadgeName.setNewStatus(newStatus);
        pitchPutDTOWithoutBadgeName.setNewMessage(newMessage);
        return pitchPutDTOWithoutBadgeName;
    }

    public PitchPutDTO generatePitchPutDTOWithoutNewStatus() {
        PitchPutDTO pitchPutDTOWithoutNewStatus = new PitchPutDTO();
        pitchPutDTOWithoutNewStatus.setPitcherName(pitcherName);
        pitchPutDTOWithoutNewStatus.setBadgeName(badgeName);
        pitchPutDTOWithoutNewStatus.setNewMessage(newMessage);
        return pitchPutDTOWithoutNewStatus;
    }

    public PitchPutDTO generatePitchPutDTOWithoutNewMessage() {
        PitchPutDTO pitchPutDTOWithoutNewMessage = new PitchPutDTO();
        pitchPutDTOWithoutNewMessage.setPitcherName(pitcherName);
        pitchPutDTOWithoutNewMessage.setBadgeName(badgeName);
        pitchPutDTOWithoutNewMessage.setNewStatus(newStatus);
        return pitchPutDTOWithoutNewMessage;
    }

    public String getValidToken() {
        return validToken;
    }

    public void setValidToken(String validToken) {
        this.validToken = validToken;
    }
}