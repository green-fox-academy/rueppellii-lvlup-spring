package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.SuccessfulQuery;
import com.greenfox.lvlup.model.dto.pitches.ReviewDTO;
import com.greenfox.lvlup.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PutMapping(value = "/review", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateReview(@RequestHeader(value = "userTokenAuth", required = false) String token,
                                              @Valid @RequestBody ReviewDTO reviewDto) throws Exception {
        if (token.isEmpty() || token == null) {
            throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
        } else {
            reviewService.updateReview(reviewDto);
            return new ResponseEntity<>(new SuccessfulQuery("Success"), HttpStatus.FOUND);
        }
    }

    @GetMapping(value = "/review")
    public ResponseEntity<Object> returnReview() {
        return new ResponseEntity<>(new ReviewDTO(1L, "TestPitcherName", "TestBadgeName", "TestReviewMessage", false), HttpStatus.OK);
    }
}
