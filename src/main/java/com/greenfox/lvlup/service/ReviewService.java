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
/*
  public void saveAllReviews(List<Review> reviews) {
    for (int i = 0; i < reviews.size() ; i++) {
      reviewRepository.save(reviews.get(i));
    }
  }*/

  public List<Review> convertSetToList(PitchDto pitchDto, Pitch pitch) throws GeneralException {
    List<Review> list = new ArrayList();
    //Review placeholder = new Review();
    System.out.println(pitchDto.getReviews());
    for (ReviewDto dto : pitchDto.getReviews()) {
      //ide kell tenni az új példányt, különben nem ment!
      Review placeholder = new Review();
      mapper.map(dto, placeholder);
      User reviewer = userService.findReviewerByName(dto.getName());
      if(pitch.getUser().getId() == reviewer.getId()) throw new GeneralException("User and reviewer cannot be the same person. Please modify reviewer.", HttpStatus.BAD_REQUEST);
      placeholder.setUser(reviewer);
      placeholder.setPitch(pitch);
      //ez itt most nem ment!! valószínűleg a főfüggvénybe kell tenni
      reviewRepository.save(placeholder);
      list.add(placeholder);
    }
   // list.add(placeholder);
    return list;
  }
}
/*
  private Set<ReviewDto> addReviewsAsSet(Pitch pitch) {
    Set<ReviewDto> reviewSet = new HashSet<>();
    for (Review review: pitch.getReviews()) {
      ReviewDto dto = mapper.map(review, ReviewDto.class);
      dto.setName(review.getUser().getName());
      reviewSet.add(dto);
    }
    return reviewSet;
  }
 */