package com.greenfox.lvlup.model.mockdto;

import com.greenfox.lvlup.model.dto.pitches.ReviewDTO;

public class MockingElementsForReviewDTO {
    private String validToken = "testtoken123";
    private Long id = 123L;
    private String pitcherName = "Test Name";
    private String badgeName = "Test Badge Name";
    private boolean newStatus = false;
    private String newMessage = "Test Message";

    public ReviewDTO generateValidReviewDTO() {
        return new ReviewDTO(id, pitcherName, badgeName, newMessage, newStatus);
    }

    public ReviewDTO generateReviewDTOWithoutId() {
        ReviewDTO reviewDTOWithoutId = new ReviewDTO();
        reviewDTOWithoutId.setBadgeName(badgeName);
        reviewDTOWithoutId.setPitchStatus(newStatus);
        reviewDTOWithoutId.setMessage(newMessage);
        return reviewDTOWithoutId;
    }

    public ReviewDTO generateReviewDTOWithoutPitcherName() {
        ReviewDTO reviewDTOWithoutPitcherName = new ReviewDTO();
        reviewDTOWithoutPitcherName.setBadgeName(badgeName);
        reviewDTOWithoutPitcherName.setPitchStatus(newStatus);
        reviewDTOWithoutPitcherName.setMessage(newMessage);
        return reviewDTOWithoutPitcherName;
    }

    public ReviewDTO generateReviewPutDTOWithoutBadgeName() {
        ReviewDTO reviewDTOWithoutBadgeName = new ReviewDTO();
        reviewDTOWithoutBadgeName.setPitcherName(pitcherName);
        reviewDTOWithoutBadgeName.setPitchStatus(newStatus);
        reviewDTOWithoutBadgeName.setMessage(newMessage);
        return reviewDTOWithoutBadgeName;
    }

    public ReviewDTO generateReviewDTOWithoutNewStatus() {
        ReviewDTO reviewDTOWithoutNewStatus = new ReviewDTO();
        reviewDTOWithoutNewStatus.setPitcherName(pitcherName);
        reviewDTOWithoutNewStatus.setBadgeName(badgeName);
        reviewDTOWithoutNewStatus.setMessage(newMessage);
        return reviewDTOWithoutNewStatus;
    }

    public ReviewDTO generateReviewDTOWithoutNewMessage() {
        ReviewDTO reviewDTOWithoutNewMessage = new ReviewDTO();
        reviewDTOWithoutNewMessage.setPitcherName(pitcherName);
        reviewDTOWithoutNewMessage.setBadgeName(badgeName);
        reviewDTOWithoutNewMessage.setPitchStatus(newStatus);
        return reviewDTOWithoutNewMessage;
    }

    public String getValidToken() {
        return validToken;
    }

    public void setValidToken(String validToken) {
        this.validToken = validToken;
    }
}