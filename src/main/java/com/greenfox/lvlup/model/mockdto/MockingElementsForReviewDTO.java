package com.greenfox.lvlup.model.mockdto;

import com.greenfox.lvlup.model.dto.pitches.ReviewDTO;

public class MockingElementsForReviewDTO {
    private String validToken = "testtoken123";
    private Long id = 123L;
    private String pitcherName = "Test Name";
    private String badgeName = "Test Badge Name";
    private boolean pitchStatus = false;
    private String message = "Test Message";

    public ReviewDTO generateValidReviewDTO() {
        return new ReviewDTO(id, pitcherName, badgeName, message, pitchStatus);
    }

    public ReviewDTO generateReviewDTOWithoutId() {
        ReviewDTO reviewDTOWithoutId = new ReviewDTO();
        reviewDTOWithoutId.setPitcherName(pitcherName);
        reviewDTOWithoutId.setBadgeName(badgeName);
        reviewDTOWithoutId.setPitchStatus(pitchStatus);
        reviewDTOWithoutId.setMessage(message);
        return reviewDTOWithoutId;
    }

    public ReviewDTO generateReviewDTOWithoutPitcherName() {
        ReviewDTO reviewDTOWithoutPitcherName = new ReviewDTO();
        reviewDTOWithoutPitcherName.setId(id);
        reviewDTOWithoutPitcherName.setBadgeName(badgeName);
        reviewDTOWithoutPitcherName.setPitchStatus(pitchStatus);
        reviewDTOWithoutPitcherName.setMessage(message);
        return reviewDTOWithoutPitcherName;
    }

    public ReviewDTO generateReviewPutDTOWithoutBadgeName() {
        ReviewDTO reviewDTOWithoutBadgeName = new ReviewDTO();
        reviewDTOWithoutBadgeName.setId(id);
        reviewDTOWithoutBadgeName.setPitcherName(pitcherName);
        reviewDTOWithoutBadgeName.setPitchStatus(pitchStatus);
        reviewDTOWithoutBadgeName.setMessage(message);
        return reviewDTOWithoutBadgeName;
    }

    public ReviewDTO generateReviewDTOWithoutPitchStatus() {
        ReviewDTO reviewDTOWithoutNewStatus = new ReviewDTO();
        reviewDTOWithoutNewStatus.setId(id);
        reviewDTOWithoutNewStatus.setPitcherName(pitcherName);
        reviewDTOWithoutNewStatus.setBadgeName(badgeName);
        reviewDTOWithoutNewStatus.setMessage(message);
        return reviewDTOWithoutNewStatus;
    }

    public ReviewDTO generateReviewDTOWithoutMessage() {
        ReviewDTO reviewDTOWithoutNewMessage = new ReviewDTO();
        reviewDTOWithoutNewMessage.setId(id);
        reviewDTOWithoutNewMessage.setPitcherName(pitcherName);
        reviewDTOWithoutNewMessage.setBadgeName(badgeName);
        reviewDTOWithoutNewMessage.setPitchStatus(pitchStatus);
        return reviewDTOWithoutNewMessage;
    }

    public String getValidToken() {
        return validToken;
    }

    public void setValidToken(String validToken) {
        this.validToken = validToken;
    }
}