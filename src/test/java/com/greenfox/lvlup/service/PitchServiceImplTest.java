package com.greenfox.lvlup.service;

import com.greenfox.lvlup.controller.PitchController;
import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.repositrory.PitchRepository;
import com.greenfox.lvlup.service.implementation.PitchServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static com.greenfox.lvlup.util.Converter.stringify;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class PitchServiceImplTest {

  @Mock
  PitchRepository repository;
  @InjectMocks
  PitchServiceImpl service;
  @Mock
  ModelMapper mapper;

//  @Before
//  public void setUp() {
//   repository = mock(PitchRepository.class);
//   service = new PitchServiceImpl(repository, mapper);
//  }

  @Test(expected = GeneralException.class)
  public void getUser_IfNotExist_ProperError() throws GeneralException{
    //when(repository.findPitchesByUserId(1)).thenThrow(new Exception());
    service.getUserPitchById(1);
  }
}
