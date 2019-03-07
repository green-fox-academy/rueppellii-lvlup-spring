package com.greenfox.lvlup.service;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.pitches.PitchDto;
import com.greenfox.lvlup.model.dto.pitches.ReviewDto;
import com.greenfox.lvlup.model.entity.Pitch;
import com.greenfox.lvlup.model.entity.Review;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
  private ReviewRepository reviewRepository;
  private ModelMapper mapper;
  private UserService userService;

  @Autowired
  public ReviewService(ReviewRepository reviewRepository, ModelMapper mapper, UserService userService) {
    this.reviewRepository = reviewRepository;
    this.mapper = mapper;
    this.userService = userService;
  }

  public List<Review> convertSetToList(PitchDto pitchDto, Pitch pitch) throws GeneralException {
    List<Review> list = new ArrayList();
    //Review placeholder = new Review();
    System.out.println(pitchDto.getReviews());
    for (ReviewDto dto : pitchDto.getReviews()) {
      //ide kell tenni az új példányt, különben nem ment!
      Review placeholder = new Review();
      mapper.map(dto, placeholder);
      User reviewer = userService.findReviewerByName(dto.getName());
      if (pitch.getUser().getId() == reviewer.getId())
        throw new GeneralException("User and reviewer cannot be the same person. Please modify reviewer.", HttpStatus.BAD_REQUEST);
      placeholder.setUser(reviewer);
      placeholder.setPitch(pitch);
      //reviewRepository.save(placeholder);
      list.add(placeholder);
    }
    return list;
  }

  public void saveReviews(List<Review> reviews) throws GeneralException {
    if(reviews.size()!= 3 ) throw new GeneralException("You must have 3 reviewers for your pitch.", HttpStatus.BAD_REQUEST);
    for (Review item : reviews) {
      reviewRepository.save(item);
    }
  }
}
