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
  public PitchService(ReviewService reviewService, BadgeLevelService badgeLevelService, BadgeService badgeService, UserService userService, PitchRepository repository, ModelMapper mapper, UserRepository userRepository,
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
    for (Review review : pitch.getReviews()) {
      ReviewDto dto = mapper.map(review, ReviewDto.class);
      dto.setName(review.getUser().getName());
      reviewSet.add(dto);
    }
    return reviewSet;
  }

  public void savePitch(PitchDto pitchDto) throws GeneralException {
    Pitch pitchToSave = convertPitchDtoToEntity(pitchDto);
    Pitch pitchExisting = findDuplicatePitch(pitchToSave.getOldLevel(), pitchToSave.getBadge().getId(), pitchToSave.getUser().getId());
    if (pitchExisting != null) {
      throw new GeneralException("You have already pitched your level for this badge.", HttpStatus.BAD_REQUEST);
    } else {
      reviewService.saveReviews(pitchToSave.getReviews());
      repository.save(pitchToSave);
    }
  }

  private Pitch convertPitchDtoToEntity(PitchDto pitchDto) throws GeneralException {
    if (pitchDto.getOldLevel() >= pitchDto.getPitchedLevel())
      throw new GeneralException("Pitched level must be higher than old level.", HttpStatus.BAD_REQUEST);
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
    pitch.setReviews(reviewService.convertSetToList(pitchDto, pitch));
    pitch.setBadgeLevel(badgeLevelToSet);
    //controls whether user has the given level of the badge:
    userService.findUserBadgeWithGivenLevel(userToSet.getId(), badgeToSet.getId(), badgeLevelToSet.getId());
    return pitch;
  }

  public Pitch findDuplicatePitch(int oldlevel, long badgeId, long userId) throws GeneralException {
    return repository.findDuplicatePitch(oldlevel, badgeId, userId);
  }
}
