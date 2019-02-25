package com.greenfox.lvlup.service;

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
}
