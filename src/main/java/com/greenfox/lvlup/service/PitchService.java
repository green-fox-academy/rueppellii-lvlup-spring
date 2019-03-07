package com.greenfox.lvlup.service;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.pitches.PitchDto;
import com.greenfox.lvlup.model.dto.pitches.ReviewDto;
import com.greenfox.lvlup.model.entity.*;
import com.greenfox.lvlup.repositrory.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PitchService {
  private ModelMapper mapper;
  private PitchRepository repository;
  private UserRepository userRepository;
  private UserService userService;
  private BadgeRepository badgeRepository;
  private BadgeService badgeService;
  private ReviewRepository reviewRepository;
  private ReviewService reviewService;
  private BadgeLevelRepository badgeLevelRepository;
  private BadgeLevelService badgeLevelService;


  @Autowired
  public PitchService(ReviewService reviewService, BadgeLevelService badgeLevelService, BadgeService badgeService, UserService userService,PitchRepository repository, ModelMapper mapper, UserRepository userRepository,
                      BadgeRepository badgeRepository, ReviewRepository reviewRepository,
                      BadgeLevelRepository badgeLevelRepository) {
    this.reviewService = reviewService;
    this.badgeLevelService = badgeLevelService;
    this.badgeService = badgeService;
    this.userService = userService;
    this.mapper = mapper;
    this.repository = repository;
    this.userRepository = userRepository;
    this.badgeRepository = badgeRepository;
    this.reviewRepository = reviewRepository;
    this.badgeLevelRepository = badgeLevelRepository;
  }

  public List<PitchDto> getUserPitchById(long id) {
    List<Pitch> pitchesList = repository.findPitchesByUserId(id);
    List<PitchDto> dtoList = new ArrayList<>();
    for (Pitch pitch : pitchesList) {
      PitchDto dto = mapper.map(pitch, PitchDto.class);
      dto.setTimestamp(pitch.getCreated());
      dto.setReviews(addReviewsAsSet(pitch));
      dtoList.add(dto);
    }
    return dtoList;
  }

  private Set<ReviewDto> addReviewsAsSet(Pitch pitch) {
    Set<ReviewDto> reviewSet = new HashSet<>();
    for (Review review: pitch.getReviews()) {
      ReviewDto dto = mapper.map(review, ReviewDto.class);
      dto.setName(review.getUser().getName());
      reviewSet.add(dto);
    }
    return reviewSet;
  }

  public void savePitch(PitchDto pitchDto) throws GeneralException {
    repository.save(convertPitchDtoToEntity(pitchDto));
  }

  private Pitch convertPitchDtoToEntity(PitchDto pitchDto) throws GeneralException {
    if(pitchDto.getOldLevel()>= pitchDto.getPitchedLevel()) throw new GeneralException("Pitched level must be higher than old level.", HttpStatus.BAD_REQUEST);
    //controls whether pitched level exists
    badgeLevelService.findBadgeLevelByPitchedLevelAndBadge(pitchDto.getPitchedLevel(), badgeService.findBadgeByName(pitchDto.getBadgeName()));
    Pitch pitch = new Pitch();
    mapper.map(pitchDto, pitch);

    //pitch.setUser(userRepository.findUserByName(pitchDto.getUserName()));
    //pitch.setBadge(badgeRepository.findBadgeByName(pitchDto.getBadgeName()));
    //pitch.setBadgeLevel(badgeLevelRepository.findBadgeLevelByLevelAndBadge
    //    (pitchDto.getOldLevel(), badgeRepository.findBadgeByName(pitchDto.getBadgeName())));
    User userToSet = userService.findUserByName(pitchDto.getUserName());
    Badge badgeToSet = badgeService.findBadgeByName(pitchDto.getBadgeName());
    BadgeLevel badgeLevelToSet = badgeLevelService.findBadgeLevelByLevelAndBadge
        (pitchDto.getOldLevel(), badgeService.findBadgeByName(pitchDto.getBadgeName()));
    pitch.setUser(userToSet);
    pitch.setBadge(badgeToSet);
   // List<Review> reviewsToSaveAndSet = reviewService.convertSetToList(pitchDto, pitch);
    //reviewService.saveAllReviews(reviewService.convertSetToList(pitchDto, pitch));
    // ---------------Itt kellene meghívni a review save függvényét!
    pitch.setReviews(reviewService.convertSetToList(pitchDto, pitch));
    pitch.setBadgeLevel(badgeLevelToSet);
    //controls whether user has the given level of the badge:
    userService.findUserBadgeWithGivelLevel(userToSet.getId(), badgeToSet.getId(), badgeLevelToSet.getId());
    return pitch;
  }

  /* private List<Review> convertSetToList(PitchDto pitchDto, Pitch pitch) throws GeneralException {
    List<Review> list = new ArrayList();
    Review placeholder = new Review();
    System.out.println(pitchDto.getReviews().size());
    for (ReviewDto dto : pitchDto.getReviews()) {
      mapper.map(dto, placeholder);
      User reviewer = userService.findReviewerByName(dto.getName());
      if(pitch.getUser().getId() == reviewer.getId()) throw new GeneralException("User and reviewer cannot be the same person. Please modify reviewer.", HttpStatus.BAD_REQUEST);
      placeholder.setUser(reviewer);
      placeholder.setPitch(pitch);
      //ez itt most nem ment!! valószínűleg a főfüggvénybe kell tenni
      //reviewRepository.save(placeholder);
      list.add(placeholder);
    }
    return list;
  }*/
}
