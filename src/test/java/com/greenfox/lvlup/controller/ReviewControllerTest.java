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

    MockingElementsForReviewDTO reviewDTOElements = new MockingElementsForReviewDTO();
    String token = "testToken";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void reviewPutValidHeaderAndBodyTestStatus() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateValidReviewDTO())))
                .andDo(print())
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void reviewPutValidHeaderAndBodyTestMessage() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateValidReviewDTO())))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Success"))
                .andReturn();
    }

    @Test
    public void reviewPutMissingContentTypeTestStatus() throws Exception {
        this.mockMvc.perform(put("/review")
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateValidReviewDTO())))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void reviewPutUnsupportedMediaTypeTestStatus() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.TEXT_HTML)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateValidReviewDTO())))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType())
                .andReturn();
    }

    @Test
    public void reviewPutInvalidTokenTestStatus() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", "")
                .content(stringify(reviewDTOElements.generateValidReviewDTO())))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    public void reviewPutInvalidTokenTestErrorMessage() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", "")
                .content(stringify(reviewDTOElements.generateValidReviewDTO())))
                .andExpect(jsonPath("$.error").value("Unauthorized"))
                .andReturn();
    }

    @Test
    public void reviewPutWithoutIdTestStatus() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateReviewDTOWithoutId())))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void reviewPutWithoutPitcherNameTestStatus() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateReviewDTOWithoutPitcherName())))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void reviewPutWithoutBadgeNameTestStatus() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateReviewPutDTOWithoutBadgeName())))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void reviewPutWithoutPitchStatusTestStatus() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateReviewDTOWithoutPitchStatus())))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void reviewPutWithoutMessageTestStatus() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateReviewDTOWithoutMessage())))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void reviewPutWithoutIdTestMessage() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateReviewDTOWithoutId())))
                .andExpect(jsonPath("$.errors").value("ReviewDTO id is required"))
                .andReturn();
    }

    @Test
    public void reviewPutWithoutPitcherNameTestMessage() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateReviewDTOWithoutPitcherName())))
                .andExpect(jsonPath("$.errors").value("ReviewDTO pitcherName is required"))
                .andReturn();
    }

    @Test
    public void reviewPutWithoutBadgeNameTestMessage() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateReviewPutDTOWithoutBadgeName())))
                .andExpect(jsonPath("$.errors").value("ReviewDTO badgeName is required"))
                .andReturn();
    }

    @Test
    public void reviewPutWithoutPitchStatusTestMessage() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateReviewDTOWithoutPitchStatus())))
                .andExpect(jsonPath("$.errors").value("ReviewDTO pitchStatus is required"))
                .andReturn();
    }

    @Test
    public void reviewPutWithoutMessageTestMessage() throws Exception {
        this.mockMvc.perform(put("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userTokenAuth", reviewDTOElements.getValidToken())
                .content(stringify(reviewDTOElements.generateReviewDTOWithoutMessage())))
                .andExpect(jsonPath("$.errors").value("ReviewDTO message is required"))
                .andReturn();
    }

}