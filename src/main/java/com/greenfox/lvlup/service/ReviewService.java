package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.pitches.ReviewDTO;
import com.greenfox.lvlup.model.entity.Review;
import com.greenfox.lvlup.repositrory.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private ModelMapper mapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ModelMapper mapper) {
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
    }

    public void saveReview(ReviewDTO reviewDto) {

    }

    public Review convertReviewDTOToEntity(ReviewDTO reviewDto) {
        Review review = mapper.map(reviewDto, Review.class);
        return review;
    }


}
