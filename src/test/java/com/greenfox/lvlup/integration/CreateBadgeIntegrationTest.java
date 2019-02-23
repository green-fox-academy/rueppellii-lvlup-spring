package com.greenfox.lvlup.integration;

import com.greenfox.lvlup.LvlupApplication;
import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static com.greenfox.lvlup.util.Converter.stringify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LvlupApplication.class)
@WebAppConfiguration
public class CreateBadgeIntegrationTest {
    String token = "token123";
    String invalidToken = "TestToken123";
    BadgeDTO validBadgeDto = new BadgeDTO("2.4", "Test badge", "general");
    BadgeDTO existingBadge = new BadgeDTO("2.2", "Process improver", "general");
    BadgeDTO invalidBadgeDto = new BadgeDTO("2.3", "", "general");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void whenAddBadgeValidRequest_thenReturns201Created() throws Exception {
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
    public void whenAddBadgeWithExistingBadgeVersion_thenReturns400BadRequest() throws Exception {
        this.mockMvc.perform(post("/admin/add")
                .header("userTokenAuth", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringify(existingBadge)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Badge with this version already exists. Please modify version."));
    }

    @Test
    public void whenAddBadgeWithInvalidToken_thenReturns404UserNotFound() throws Exception {
        this.mockMvc.perform(post("/admin/add")
                .header("userTokenAuth", invalidToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringify(validBadgeDto)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("User was not found."));
    }

    @Test
    public void whenAddBadgeWithInvalidName_thenReturns400BadRequest() throws Exception {
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
    public void whenAddBadgeWithEmptyToken_thenReturns401Unauthorized() throws Exception {
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
    public void whenAddBadgeWithoutToken_thenReturns401Unauthorized() throws Exception {
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
    public void whenAddBadgeWithoutMediatype_thenReturns415UnsupportedMediaType() throws Exception {
        this.mockMvc.perform(post("/admin/add")
                .header("userTokenAuth", token)
                .content(stringify(validBadgeDto)))
                .andExpect(status().isUnsupportedMediaType());
    }
}