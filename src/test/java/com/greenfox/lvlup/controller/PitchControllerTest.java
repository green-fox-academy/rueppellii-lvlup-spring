package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.mockdto.MockingElements;
import com.greenfox.lvlup.model.mockdto.MockingElementsForPitchPutDTO;
import com.greenfox.lvlup.model.mockdto.PitchSetDTO;
import com.greenfox.lvlup.service.PitchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.greenfox.lvlup.util.Converter.stringify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(PitchController.class)
public class PitchControllerTest {

  @MockBean
  PitchService service;

  MockingElements elements = new MockingElements();
  MockingElementsForPitchPutDTO pitchPutDTOElements = new MockingElementsForPitchPutDTO();
  String token = "testToken";
  PitchSetDTO pitchSetDTO = new PitchSetDTO();
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void pitchBadgeValidHeaderAndBodyCheckStatus() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authentication", elements.getValidToken())
        .content(stringify(elements.getFullPostPitchDto())))
        .andExpect(status().isCreated())
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

  @Test
  public void pitchBadgeValidHeaderAndBodyCheckMessage() throws Exception {

    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authentication", elements.getValidToken())
        .content(stringify(elements.getFullPostPitchDto())))
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Success"))
        .andReturn();
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
  public void pitchBadgeMissingContentTypeCheckStatus() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .header("Authentication", elements.getValidToken())
        .content(stringify(elements.getFullPostPitchDto())))
        .andExpect(status().isUnsupportedMediaType());
  }

  @Test
  public void pitchPutMissingContentTypeTestStatus() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generateValidPitchPutDTO())))
        .andExpect(status().isUnsupportedMediaType());
  }

  @Test
  public void getPitchesWithCorrectHeader() throws Exception {
    mockMvc.perform(get("/pitches")
        .header("userTokenAuth", token)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(stringify(pitchSetDTO)))
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
  public void pitchPutInvalidTokenTestErrorMessage() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(pitchPutDTOElements.generateValidPitchPutDTO())))
        .andExpect(jsonPath("$.error").value("Unauthorized"))
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBodyCheckStatus2() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authentication", elements.getValidToken())
        .content(stringify(elements.getPitchPostDtoWithoutOldLvl())))
        .andExpect(status().isBadRequest())
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
  public void pitchPutWithoutNewMessageTestStatus() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutNewMessage())))
        .andExpect(status().isBadRequest())
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
  public void pitchBadgeInvalidRequestBodyCheckMessage2() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authentication", elements.getValidToken())
        .content(stringify(elements.getPitchPostDtoWithoutOldLvl())))
        .andExpect(jsonPath("$.errors").value("Old level is required."))
        .andReturn();
  }

  @Test
  public void pitchPutWithoutBadgeNameTestMessage() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutBadgeName())))
        .andExpect(jsonPath("$.errors").value("Badge level is required."))
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
  public void pitchBadgeInvalidRequestBodyCheckMessage1() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authentication", elements.getValidToken())
        .content(stringify(elements.getPitchPostDtoWithoutBadgeName())))
        .andExpect(jsonPath("$.errors").value("Badge name is required."))
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

  public void checkIfTokenIsProvided() throws Exception {
    mockMvc.perform(get("/pitches")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.error").value("unauthorized"));
  }
}
