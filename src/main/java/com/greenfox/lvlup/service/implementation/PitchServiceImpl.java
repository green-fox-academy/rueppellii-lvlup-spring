package com.greenfox.lvlup.service.implementation;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.pitches.PitchDto;
import com.greenfox.lvlup.model.dto.pitches.ReviewDto;
import com.greenfox.lvlup.model.entity.Pitch;
import com.greenfox.lvlup.model.entity.Review;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import com.greenfox.lvlup.repositrory.PitchRepository;
import com.greenfox.lvlup.repositrory.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PitchServiceImpl {
  private PitchRepository repository;
  private ModelMapper mapper;
  private UserRepository userRepository;
  private BadgeRepository badgeRepository;

  @Autowired
  public PitchServiceImpl(PitchRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public List<PitchDto> getPitchesByUserId(long id) throws GeneralException {
    List<Pitch> pitchesList;
    try {
      pitchesList = repository.findPitchesByUserId(id);
    } catch (Exception ex) {
      throw new GeneralException("User not found with that specified id.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    List<PitchDto> dtoList = new ArrayList<>();
    for (Pitch pitch : pitchesList) {
      PitchDto dto = mapper.map(pitch, PitchDto.class);
      dto.setTimestamp(pitch.getCreated());
      dto.setReviews(setReviews(pitch));
      dtoList.add(dto);
    }
    return dtoList;
  }

  public Set<ReviewDto> setReviews(Pitch pitch) {
    Set<ReviewDto> reviewSet = new HashSet<>();
    for (Review review : pitch.getReviews()) {
      ReviewDto dto = mapper.map(review, ReviewDto.class);
      dto.setName(review.getUser().getName());
      reviewSet.add(dto);
    }
    return reviewSet;
  }

  public void savePitch(PitchDto pitchDto) {
    repository.save(convertPitchDtoToEntity(pitchDto));
  }

  private PitchDto convertPitchEntityToDto(Pitch pitch) {
    PitchDto pitchDto = new PitchDto();
    mapper.map(pitch, pitchDto);
    return pitchDto;
  }

  private Pitch convertPitchDtoToEntity(PitchDto pitchDto) {
    Pitch pitch = new Pitch();
    mapper.map(pitchDto, pitch);
    pitch.setUser(userRepository.findUserByName(pitchDto.getUserName()));
    pitch.setCreated(pitchDto.getTimestamp());
    pitch.setBadge(badgeRepository.findBadgeByName(pitchDto.getBadgeName()));
    return pitch;
  }
}
