package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.service.BadgeService;
import com.greenfox.lvlup.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.greenfox.lvlup.util.Converter.stringify;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    String token = "TestToken123";
    BadgeDTO validBadgeDto = new BadgeDTO("2.4", "Test badge", "general");
    BadgeDTO invalidBadgeDto = new BadgeDTO("2.3", "", "general");
    long id = 1;
    User testBadgeCreator = new User("TestUser creating new badge");
    //Badge validBadge = new Badge("2.3", "Test badge", "general");*/

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BadgeService badgeService;

    @MockBean
    private UserService userService;

    @Test
    public void addBadgeValidRequestReturns201Created() throws Exception {
        //given(badgeService.convertBadgeDTOToBadge(validBadgeDto)).willReturn(validBadge);
        //given(userService.findUserById(id)).willReturn(testBadgeCreator);
        //given(badgeService.createBadge(validBadgeDto, id)).willReturn(true);

        this.mockMvc.perform(post("/admin/add")
                .header("userTokenAuth", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringify(validBadgeDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Success"));
    }

    @Test
    public void invalidNameErrorReturns400BadRequest() throws Exception {
        //given(badgeService.convertBadgeDTOToBadge(invalidBadgeDto)).willReturn(validBadge);
        //given(userService.findUserById(id)).willReturn(testBadgeCreator);
        this.mockMvc.perform(post("/admin/add")
                .header("userTokenAuth", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringify(invalidBadgeDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\n" +
                        "    \"errorMessage\": \"Please provide all fields: 1 error(s)\",\n" +
                        "    \"errors\": [\n" +
                        "        \"Badge name must not be blank!\"\n" +
                        "    ]\n" +
                        "}"));
    }

    @Test
    public void addBadgeNotContainingTokenValueReturns401Unauthorized() throws Exception {
        this.mockMvc.perform(post("/admin/add")
                .header("userTokenAuth", "")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringify(validBadgeDto)))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Unauthorized"));
    }

    @Test
    public void addBadgeNotContainingTokenReturns401Unauthorized() throws Exception {
        this.mockMvc.perform(post("/admin/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringify(validBadgeDto)))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Unauthorized"));
    }

    @Test
    public void addBadgeNotContainingMediatypeReturns415UnsupportedMediaType() throws Exception {
        this.mockMvc.perform(post("/admin/add")
                .header("userTokenAuth", token)
                .content(stringify(validBadgeDto)))
                .andExpect(status().isUnsupportedMediaType());
    }
}
