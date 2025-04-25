package mx.uv.coatza.S22017021.copsboot.report;

import mx.uv.coatza.S22017021.copsboot.annotation.CopsbootControllerTest;
import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.report.Report;
import mx.uv.coatza.S22017021.copsboot.model.report.ReportId;
import mx.uv.coatza.S22017021.copsboot.model.report.web.ReportRestController;
import mx.uv.coatza.S22017021.copsboot.model.user.User;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;
import mx.uv.coatza.S22017021.copsboot.service.ReportService;
import mx.uv.coatza.S22017021.copsboot.service.report.CreateReportParameters;
import mx.uv.coatza.S22017021.copsboot.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CopsbootControllerTest(ReportRestController.class)
public class ReportRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService service;

    @MockBean
    private UserService userService;

    @Test
    public void officerIsAbleToPostAReport() throws Exception {
        UserId userId = new UserId(UUID.randomUUID());
        AuthServerId authServerId = new AuthServerId(UUID.fromString("eaa8b8a5-a264-48be-98de-d8b4ae2750ac"));
        User user = new User(userId,
                "wim@example.com",
                authServerId,
                "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0");
        when(userService.findUserByAuthServerId(authServerId))
                .thenReturn(Optional.of(user));
        when(userService.getUserById(userId))
                .thenReturn(user);
        when(service.createReport(any(CreateReportParameters.class)))
                .thenReturn(new Report(new ReportId(UUID.randomUUID()),
                        userId,
                        Instant.parse("2023-04-11T22:59:03.189+02:00"),
                        "This is a test report description. The suspect was wearing a black hat."));
        mockMvc.perform(multipart("/api/reports")
                        .file(new MockMultipartFile("image", "picture.png", MediaType.IMAGE_PNG_VALUE, new byte[]{1,2,3}))
                        .param("dateTime", "2023-04-11T22:59:03.189+02:00")
                        .param("description", "This is a test report description. The suspect was wearing a black hat.")
                        .param("trafficIncident", "false")
                        .param("numberOfInvolvedCars", "0")
                        .with(jwt().jwt(builder -> builder.subject(authServerId.value().toString())
                                        .claim("email", "wim@example.com"))
                                .authorities(new SimpleGrantedAuthority("ROLE_OFFICER"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("reporter").value("wim@example.com"))
                .andExpect(jsonPath("dateTime").value("2023-04-11T20:59:03.189Z"))
                .andExpect(jsonPath("description").value("This is a test report description. The suspect was wearing a black hat."));
    }
}
