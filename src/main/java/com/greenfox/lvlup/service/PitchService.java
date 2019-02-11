package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.UserPitchDto;
import com.greenfox.lvlup.model.entity.Pitch;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.PitchRepository;
import com.greenfox.lvlup.repositrory.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PitchService {
  private PitchRepository repository;
  private ModelMapper mapper;

  @Autowired
  public PitchService(PitchRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public List<UserPitchDto> getUserPitchById(long id) {
    List<Pitch> pitchesList = repository.findPitchesByUserId(id);
    List<UserPitchDto> dtoList = new ArrayList<>();
    for (Pitch pitch : pitchesList) {
      UserPitchDto dto = mapper.map(pitch, UserPitchDto.class);
      dtoList.add(dto);
    }
    return dtoList;
  }


}
