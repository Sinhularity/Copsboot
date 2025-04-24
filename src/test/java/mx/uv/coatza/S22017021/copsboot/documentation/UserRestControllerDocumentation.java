package mx.uv.coatza.S22017021.copsboot.documentation;

import mx.uv.coatza.S22017021.copsboot.annotation.CopsbootControllerDocumentationTest;
import mx.uv.coatza.S22017021.copsboot.controller.UserDto;
import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.user.User;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;
import mx.uv.coatza.S22017021.copsboot.service.CreateUserParameters;
import mx.uv.coatza.S22017021.copsboot.service.CreateUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import mx.uv.coatza.S22017021.copsboot.controller.UserRestController;
import mx.uv.coatza.S22017021.copsboot.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@CopsbootControllerDocumentationTest(UserRestController.class)
public class UserRestControllerDocumentation {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('OFFICER')")
    public UserDto createUser(@AuthenticationPrincipal Jwt jwt, @RequestBody CreateUserRequest request) throws Exception {
        CreateUserParameters parameters = request.toParameters(jwt);
        User user = userService.createUser(parameters);
        return UserDto.fromUser(user);
    }

    @Test
    public void createOfficerExample() throws Exception {
        UserId userId = new UserId(UUID.randomUUID());

        when(userService.createUser(any(CreateUserParameters.class)))
                .thenReturn(new User(
                        userId,
                        "wim@example.com",
                        new AuthServerId(UUID.fromString("eaa8b8a5-a264-48be-98de-d8b4ae2750ac")),
                        "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0"
                ));

        mockMvc.perform(post("/api/users")
                        .with(jwt().jwt(builder -> builder.subject(UUID.randomUUID().toString()))
                                .authorities(new SimpleGrantedAuthority("ROLE_OFFICER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "mobileToken": "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0"
                }
            """))
                .andExpect(status().isCreated())
                .andDo(document("create-user",
                        requestFields(
                                fieldWithPath("mobileToken")
                                        .description("The unique mobile token of the device (for push notifications).")
                        ),
                        responseFields(
                                fieldWithPath("userId")
                                        .description("The unique id of the user."),
                                fieldWithPath("email")
                                        .description("The email address of the user."),
                                fieldWithPath("authServerId")
                                        .description("The id of the user on the authorization server."),
                                fieldWithPath("mobileToken")
                                        .description("The unique mobile token of the device (for push notifications).")
                        )
                ));
    }


}
