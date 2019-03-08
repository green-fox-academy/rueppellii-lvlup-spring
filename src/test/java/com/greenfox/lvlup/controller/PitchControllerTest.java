package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.LvlupApplication;
import com.greenfox.lvlup.model.dto.pitches.PitchDto;
import com.greenfox.lvlup.model.mockdto.MockingElements;
import com.greenfox.lvlup.model.mockdto.MockingElementsForPitchPutDTO;
import com.greenfox.lvlup.model.mockdto.PitchSetDTO;
import com.greenfox.lvlup.service.implementation.PitchServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.greenfox.lvlup.util.Converter.stringify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = LvlupApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PitchControllerTest {

//  @MockBean
//  PitchServiceImpl service;


  MockingElements elements = new MockingElements();
  MockingElementsForPitchPutDTO pitchPutDTOElements = new MockingElementsForPitchPutDTO();
  String token = "testToken";
  PitchSetDTO pitchSetDTO = new PitchSetDTO();
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void pitchesGetCheckIfTokenIsProvided() throws Exception {
    mockMvc.perform(get("/pitches?id=1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.error").value("Unauthorized"));
  }

  @Test
  public void pitchesGetWithCorrectHeader() throws Exception {
    //Mockito.when(service.getPitchesByUserId(1)).thenReturn(pitchSetDTO.getMyPitches())
    mockMvc.perform(get("/pitches?id=1")
        .header("userTokenAuth", token)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(stringify(pitchSetDTO.getMyPitches())))
        .andReturn();
  }

  @Test
  public void pitchPostValidHeaderAndBodyCheckStatus() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchDto())))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void pitchPostValidHeaderAndBodyCheckMessage() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchDto())))
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Success"))
        .andReturn();
  }

  @Test
  public void pitchPostMissingContentTypeCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchDto())))
        .andExpect(status().isUnsupportedMediaType());
  }

  @Test
  public void pitchPostInvalidTokenCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(elements.getValidPitchDto())))
        .andExpect(status().isUnauthorized())
        .andReturn();
  }

  @Test
  public void pitchPostInvalidTokenCheckErrorMessage() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(elements.getValidPitchDto())))
        .andExpect(jsonPath("$.error").value("Unauthorized"))
        .andReturn();
  }

  @Test
  public void pitchPostInvalidRequestBodyCheckStatus2() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchDto2())))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  @Test
  public void pitchPutWithoutBadgeNameTestStatus() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutBadgeName())))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  @Test
  public void pitchPutWithoutNewStatusTestStatus() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutNewStatus())))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  @Test
  public void pitchPostInvalidRequestBodyCheckMessage5() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchDto5())))
        .andExpect(jsonPath("$.errors").value("Holders are required."))
        .andReturn();
  }

  @Test
  public void pitchPostInvalidRequestBodyCheckMessage2() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchDto2())))
        .andExpect(jsonPath("$.errors").value("Old badgeLevel is required."))
        .andReturn();
  }

  @Test
  public void pitchPostEmptyRequestBodyCheckMessage5() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getEmptyPitchDto5())))
        .andExpect(jsonPath("$.errors").value("Holders are required."))
        .andReturn();
  }

  @Test
  public void pitchPostInvalidRequestBodyCheckMessage1() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchDto1())))
        .andExpect(jsonPath("$.errors").value("PitchDto name is required."))
        .andReturn();
  }

  @Test
  public void pitchPutWithoutNewStatusTestMessage() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutNewStatus())))
        .andExpect(jsonPath("$.errors").value("New status is required."))
        .andReturn();
  }

  @Test
  public void pitchPutInvalidTokenTestErrorMessage() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(pitchPutDTOElements.generateValidPitchPutDTO())))
        .andExpect(jsonPath("$.error").value("Unauthorized"))
        .andReturn();
  }

  @Test
  public void pitchPutWithoutBadgeNameTestMessage() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutBadgeName())))
        .andExpect(jsonPath("$.errors").value("Badge badgeLevel is required."))
        .andReturn();
  }

  @Test
  public void pitchPutWithoutPitcherNameTestMessage() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutPitcherName())))
        .andExpect(jsonPath("$.errors").value("Name of pitcher is required."))
        .andReturn();
  }

  @Test
  public void pitchPutWithoutNewMessageTestStatus() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutNewMessage())))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  @Test
  public void pitchPutWithoutNewMessageTestMessage() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutNewMessage())))
        .andExpect(jsonPath("$.errors").value("New message is required."))
        .andReturn();
  }

  @Test
  public void pitchPutWithoutPitcherNameTestStatus() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutPitcherName())))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  @Test
  public void pitchPutInvalidTokenTestStatus() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(pitchPutDTOElements.generateValidPitchPutDTO())))
        .andExpect(status().isUnauthorized())
        .andReturn();
  }

  @Test
  public void pitchPutMissingContentTypeTestStatus() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generateValidPitchPutDTO())))
        .andExpect(status().isUnsupportedMediaType());
  }

  @Test
  public void pitchPutValidHeaderAndBodyTestMessage() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generateValidPitchPutDTO())))
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Success"))
        .andReturn();
  }

  @Test
  public void pitchPutValidHeaderAndBodyTestStatus() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generateValidPitchPutDTO())))
        .andDo(print())
        .andExpect(status().isCreated())
        .andReturn();
  }
}
