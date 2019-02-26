package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.pitches.PitchDto;
import com.greenfox.lvlup.model.dto.pitches.ReviewDTO;
import com.greenfox.lvlup.model.entity.Pitch;
import com.greenfox.lvlup.model.entity.Review;
import com.greenfox.lvlup.repositrory.PitchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PitchService {
  private PitchRepository repository;
  private ModelMapper mapper;

  @Autowired
  public PitchService(PitchRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public List<PitchDto> getUserPitchById(long id) {
    List<Pitch> pitchesList = repository.findPitchesByUserId(id);
    List<PitchDto> dtoList = new ArrayList<>();
    for (Pitch pitch : pitchesList) {
      PitchDto dto = mapper.map(pitch, PitchDto.class);
      dto.setTimestamp(pitch.getCreated());
      dto.setReviews(setReviews(pitch));
      dtoList.add(dto);
    }
    return dtoList;
  }

  private Set<ReviewDTO> setReviews(Pitch pitch) {
    Set<ReviewDTO> reviewSet = new HashSet<>();
    for (Review review: pitch.getReviews()) {
      ReviewDTO dto = mapper.map(review, ReviewDTO.class);
      dto.setPitcherName(review.getUser().getName());
      reviewSet.add(dto);
    }
    return reviewSet;
  }
}
