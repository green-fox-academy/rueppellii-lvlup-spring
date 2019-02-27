package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.mockdto.MockingElementsForReviewDTO;
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
public class ReviewControllerTest {

    @MockBean
    PitchService service;

    MockingElementsForReviewDTO pitchPutDTOElements = new MockingElementsForReviewDTO();
    String token = "testToken";

    @Autowired
    private MockMvc mockMvc;

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
    public void pitchPutMissingContentTypeTestStatus() throws Exception {
        this.mockMvc.perform(put("/pitch")
                .header("userTokenAuth", pitchPutDTOElements.getValidToken())
                .content(stringify(pitchPutDTOElements.generateValidPitchPutDTO())))
                .andExpect(status().isUnsupportedMediaType());
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
    public void pitchPutWithoutNewMessageTestMessage() throws Exception {
        this.mockMvc.perform(put("/pitch")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", pitchPutDTOElements.getValidToken())
                .content(stringify(pitchPutDTOElements.generatePitchPutDTOWithoutNewMessage())))
                .andExpect(jsonPath("$.errors").value("New message is required."))
                .andReturn();
    }

}
