package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.pitches.PitchDto;
import com.greenfox.lvlup.model.dto.pitches.ReviewDto;
import com.greenfox.lvlup.model.entity.Pitch;

import java.util.List;
import java.util.Set;

public interface PitchService {
  List<PitchDto> getUserPitchById(long id);
  Set<ReviewDto> setReviews(Pitch pitch);
}
