package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.SuccessfulQuery;
import com.greenfox.lvlup.model.dto.pitches.ReviewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ReviewController {

    private ReviewDTO reviewDto;

    @PutMapping(value = "/review", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> amendReview(@RequestHeader(value = "userTokenAuth", required = false) String token,
                                              @Valid @RequestBody ReviewDTO reviewDto) throws Exception {
        if (token.isEmpty() || token == null) {
            throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new SuccessfulQuery("Success"), HttpStatus.FOUND);
    }
}
