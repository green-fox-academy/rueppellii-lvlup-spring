package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.pitches.PitchDto;
import com.greenfox.lvlup.model.dto.pitches.ReviewDto;
import com.greenfox.lvlup.model.entity.Pitch;
import com.greenfox.lvlup.model.entity.Review;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
  private BadgeRepository badgeRepository;
  private ReviewRepository reviewRepository;
  private BadgeLevelRepository badgeLevelRepository;

  @Autowired
  public PitchService(PitchRepository repository, ModelMapper mapper, UserRepository userRepository,
                      BadgeRepository badgeRepository, ReviewRepository reviewRepository,
                      BadgeLevelRepository badgeLevelRepository) {
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

  public void savePitch(PitchDto pitchDto) {
    repository.save(convertPitchDtoToEntity(pitchDto));
  }

  private Pitch convertPitchDtoToEntity(PitchDto pitchDto) {
    Pitch pitch = new Pitch();
    mapper.map(pitchDto, pitch);
    pitch.setUser(userRepository.findUserByName(pitchDto.getUserName()));
    pitch.setBadge(badgeRepository.findBadgeByName(pitchDto.getBadgeName()));
    pitch.setReviews(convertSetToList(pitchDto, pitch));
    pitch.setBadgeLevel(badgeLevelRepository.findBadgeLevelByLevelAndBadge
        (pitchDto.getOldLevel(), badgeRepository.findBadgeByName(pitchDto.getBadgeName())));
    return pitch;
  }

  private List<Review> convertSetToList(PitchDto pitchDto, Pitch pitch) {
    List<Review> list = new ArrayList();
    Review placeholder = new Review();
    for (ReviewDto dto : pitchDto.getReviews()) {
      mapper.map(dto, placeholder);
      placeholder.setUser(userRepository.findUserByName(dto.getName()));
      placeholder.setPitch(pitch);
      reviewRepository.save(placeholder);
      list.add(placeholder);
    }
    return list;
  }
}
