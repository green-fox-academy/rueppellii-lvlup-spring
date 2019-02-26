package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.pitches.PitchPostDto;
import com.greenfox.lvlup.model.dto.pitches.ReviewDto;
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

import java.util.HashSet;
import java.util.Set;

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

//passes
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

  //passes
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

  //passes
  @Test
  public void pitchBadgeMissingContentTypeCheckStatus() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .header("userTokenAuth", elements.getValidToken())
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

// milegyen??
  @Test
  public void pitchBadgeInvalidTokenCheckStatus() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authentication", "")
        .content(stringify(elements.getFullPostPitchDto())))
        .andExpect(status().isUnauthorized())
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
//ua mint elobb
  @Test
  public void pitchBadgeInvalidTokenCheckErrorMessage() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(elements.getValidPitchPostDTO())))
        .andExpect(jsonPath("$.error").value("Unauthorized"))
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
//not working
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
/* I dont think we need this, there is not even Holder field anymore anywhere
  @Test
  public void pitchBadgeInvalidRequestBodyCheckMessage5() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchPostDTO5())))
        .andExpect(jsonPath("$.errors").value("Holders are required."))
        .andReturn();
  }*/

  @Test
  public void pitchPutWithoutPitcherNameTestMessage() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutPitcherName())))
        .andExpect(jsonPath("$.errors").value("Name of pitcher is required."))
        .andReturn();
  }
//does not act as should
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

  /* holders again, we dont have them
  @Test
  public void pitchBadgeEmptyRequestBodyCheckMessage5() throws Exception {
    this.mockMvc.perform(post("/api/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getEmptyPitchPostDTO5())))
        .andExpect(jsonPath("$.errors").value("Holders are required."))
        .andReturn();
  }*/

  @Test
  public void pitchPutWithoutNewStatusTestMessage() throws Exception {
    this.mockMvc.perform(put("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", pitchPutDTOElements.getValidToken())
        .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutNewStatus())))
        .andExpect(jsonPath("$.errors").value("New status is required."))
        .andReturn();
  }


// passes
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
