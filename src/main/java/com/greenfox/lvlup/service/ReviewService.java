package com.greenfox.lvlup.service;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.pitches.ReviewDTO;
import com.greenfox.lvlup.model.entity.Review;
import com.greenfox.lvlup.repositrory.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private ModelMapper mapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ModelMapper mapper) {
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public void updateReview(ReviewDTO reviewDto) throws GeneralException {
        reviewRepository.save(convertReviewDTOToEntityForPut(reviewDto));
    }

    public Review convertReviewDTOToEntityForPut(ReviewDTO reviewDto) throws GeneralException {
        Review review = findById(reviewDto.getId());
        mapper.map(reviewDto, review);
        return review;
    }

    public Review findById(Long id) throws GeneralException {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            return review.get();
        }
        throw new GeneralException("Review does not exist", HttpStatus.NOT_FOUND);
    }
}
