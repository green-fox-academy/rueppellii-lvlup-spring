package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.mockdto.MockingElements;
import com.greenfox.lvlup.model.mockdto.PitchSetDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.greenfox.lvlup.util.Converter.stringify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PitchController.class)
public class PitchControllerTest {


 MockingElements elements = new MockingElements();

  @Autowired
  private MockMvc mockMvc;
  String token = "testToken";
  PitchSetDTO pitchSetDTO = new PitchSetDTO();

  @Test
  public void pitchBadgeValidHeaderAndBodyCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchDto())))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void pitchBadgeValidHeaderAndBodyCheckMessage() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchDto())))
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Success"))
        .andReturn();
  }

  @Test
  public void pitchBadgeMissingContentTypeCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchDto())))
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
        .content(stringify(elements.getValidPitchDto())))
        .andExpect(status().isUnauthorized())
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidTokenCheckErrorMessage() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(elements.getValidPitchDto())))
        .andExpect(jsonPath("$.error").value("Unauthorized"))
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBodyCheckStatus2() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchDto2())))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBodyCheckMessage5() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchDto5())))
        .andExpect(jsonPath("$.errors").value("Holders are required."))
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBodyCheckMessage2() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchDto2())))
        .andExpect(jsonPath("$.errors").value("Old level is required."))
        .andReturn();
  }

  @Test
  public void pitchBadgeEmptyRequestBodyCheckMessage5() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getEmptyPitchDto5())))
        .andExpect(jsonPath("$.errors").value("Holders are required."))
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBodyCheckMessage1() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchDto1())))
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
