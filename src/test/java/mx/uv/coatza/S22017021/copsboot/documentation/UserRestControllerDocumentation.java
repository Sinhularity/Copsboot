package mx.uv.coatza.S22017021.copsboot.documentation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import com.c4_soft.springaddons.security.oauth2.test.webmvc.AutoConfigureAddonsWebmvcResourceServerSecurity;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import mx.uv.coatza.S22017021.copsboot.controller.UserRestController;
import mx.uv.coatza.S22017021.copsboot.security.WebSecurityConfiguration;
import mx.uv.coatza.S22017021.copsboot.service.UserService;

@WebMvcTest(UserRestController.class)
@AutoConfigureAddonsWebmvcResourceServerSecurity
@Import(WebSecurityConfiguration.class)
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
public class UserRestControllerDocumentation {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public RestDocsMockMvcConfigurationCustomizer restDocsMockMvcConfigurationCustomizer() {
            return configurer -> configurer.operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint(),
                            modifyHeaders().removeMatching("X.*")
                                    .removeMatching("Pragma")
                                    .removeMatching("Expires"));
        }
    }

    @Test
    public void ownUserDetailsWhenNotLoggedInExample() throws Exception {
        mockMvc.perform(get("/api/user/ownUserDetails"))
                .andExpect(status().isUnauthorized())
                .andDo(document("own-details-unauthorized"));
    }

    @Test
    public void authenticatedOfficerDetailsExample() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/me")
                .with(jwt()
                        .jwt(builder -> builder.subject(UUID.randomUUID().toString()))
                        .authorities(new SimpleGrantedAuthority("ROLE_OFFICER"))))
                .andExpect(status().isOk())
                .andDo(document("own-details",
                        responseFields(
                                fieldWithPath("subject").description("The subject from the JWT token"),
                                subsectionWithPath("claims").description("The claims from the JWT token"))));
    }

}
