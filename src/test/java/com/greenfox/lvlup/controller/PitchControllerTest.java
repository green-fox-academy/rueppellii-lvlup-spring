package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.mockdto.MockingElements;
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
  String token = "testToken";
  PitchSetDTO pitchSetDTO = new PitchSetDTO();
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void pitchBadgeValidHeaderAndBodyCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchPostDTO())))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void pitchBadgeValidHeaderAndBodyCheckMessage() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchPostDTO())))
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Success"))
        .andReturn();
  }

  @Test
  public void pitchBadgeMissingContentTypeCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchPostDTO())))
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
  public void pitchBadgeInvalidTokenCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(elements.getValidPitchPostDTO())))
        .andExpect(status().isUnauthorized())
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidTokenCheckErrorMessage() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(elements.getValidPitchPostDTO())))
        .andExpect(jsonPath("$.error").value("Unauthorized"))
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBodyCheckStatus2() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchPostDTO2())))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBodyCheckMessage5() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchPostDTO5())))
        .andExpect(jsonPath("$.errors").value("Holders are required."))
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBodyCheckMessage2() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchPostDTO2())))
        .andExpect(jsonPath("$.errors").value("Old level is required."))
        .andReturn();
  }

  @Test
  public void pitchBadgeEmptyRequestBodyCheckMessage5() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getEmptyPitchPostDTO5())))
        .andExpect(jsonPath("$.errors").value("Holders are required."))
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBodyCheckMessage1() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchPostDTO1())))
        .andExpect(jsonPath("$.errors").value("PitchDto name is required."))
        .andReturn();
  }

  public void checkIfTokenIsProvided() throws Exception {
    mockMvc.perform(get("/pitches")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.error").value("unauthorized"));
  }
}
