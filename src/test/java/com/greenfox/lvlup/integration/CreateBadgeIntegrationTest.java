package com.greenfox.lvlup.integration;

import com.greenfox.lvlup.LvlupApplication;
import com.greenfox.lvlup.config.DisableSecurityForTestsConfig;
import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static com.greenfox.lvlup.util.Converter.stringify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LvlupApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc()
@ContextConfiguration(classes = DisableSecurityForTestsConfig.class)
    public class CreateBadgeIntegrationTest {
    private String token = "token123";
    private String invalidToken = "TestToken123";
    private BadgeDTO validBadgeDto = new BadgeDTO("2.4", "Test badge", "general");
    private BadgeDTO existingBadge = new BadgeDTO("2.2", "Process improver", "general");
    private BadgeDTO invalidBadgeDto = new BadgeDTO("2.3", "", "general");

    @Autowired
    private MockMvc mockMvc;

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
