package mx.uv.coatza.S22017021.copsboot.report;


import com.fasterxml.jackson.annotation.JsonProperty;
import dasniko.testcontainers.keycloak.KeycloakContainer;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;
import mx.uv.coatza.S22017021.copsboot.infrastructure.SpringProfiles;
import mx.uv.coatza.S22017021.copsboot.repository.user.UserRepository;
import org.junit.jupiter.api.*;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;

import java.util.Collections;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(SpringProfiles.INTEGRATION_TEST)
public class ReportRestControllerIntegrationTest {

    private static final String REALM_NAME = "copsboot";
    private static final String ROLE_NAME = "OFFICER";
    private static final String INTEGRATION_TEST_CLIENT_ID = "integration-test-client";
    private static final String TEST_USER_NAME = "wim@example.com";
    private static final String TEST_USER_PASSWORD = "test1234";

    @LocalServerPort
    private int serverport;

    static GenericContainer<?> keycloak = new GenericContainer<>("quay.io/keycloak/keycloak:22.0.1")
            .withExposedPorts(8080)
            .withEnv("KEYCLOAK_ADMIN", "admin")
            .withEnv("KEYCLOAK_ADMIN_PASSWORD", "admin")
            .withCommand("start-dev");

    private static String clientSecret;

    @BeforeAll
    static void beforeAll() {
        keycloak.start();
        Keycloak client = Keycloak.getInstance(
                "http://" + keycloak.getHost() + ":" + keycloak.getMappedPort(8080),
                "master",
                "admin",
                "admin",
                "admin-cli"
        );

        KeycloakAdminClientFacade clientFacade = new KeycloakAdminClientFacade(client);
        clientFacade.createRealm(REALM_NAME);
        clientFacade.createRealmRole(REALM_NAME, ROLE_NAME);
        clientFacade.createUser(REALM_NAME, TEST_USER_NAME, TEST_USER_PASSWORD, ROLE_NAME);
        clientSecret = clientFacade.createClient(REALM_NAME, INTEGRATION_TEST_CLIENT_ID);
    }

    @AfterAll
    static void afterAll() {
        keycloak.stop();
    }

    @AfterEach
    void afterEach(@Autowired UserRepository userRepository) {
        userRepository.deleteAll();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("com.c4-soft.springaddons.oidc.ops[0].iss", () ->
                "http://" + keycloak.getHost() + ":" + keycloak.getMappedPort(8080)
                + "/realms/" + REALM_NAME);
        registry.add("com.c4-soft.springaddons.oidc.ops[0].authorities[0].path", () -> "$.realm_access.roles");
        registry.add("com.c4-soft.springaddons.oidc.ops[0].authorities[0].prefix", () -> "ROLE_");
    }

    @BeforeEach
    public void setup() {
        RestAssured.port = serverport;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @Test
    public void officerIsUnableToPostAReportIfFileSizeIsTooBig() {
        String token = getToken();

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "mobileToken": "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0"
                        }
                         """)
                .post("/api/users")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        given()
                .header("Authorization", "Bearer " + token)
                .multiPart(new MultiPartSpecBuilder(new byte[2_000_000])
                        .fileName("picture.png")
                        .controlName("image")
                        .mimeType("image/png")
                        .build())
                .formParam("dateTime", "2018-04-11T22:59:03.189+02:00")
                .formParam("description", "The suspect is wearing a black hat.")
                .formParam("trafficIncident", "false")
                .formParam("numberOfInvolvedCars", "0")
                .when()
                .post("/api/reports")
                .then()
                .statusCode(HttpStatus.PAYLOAD_TOO_LARGE.value());
    }

    private String getToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("grant_type", Collections.singletonList("password"));
        map.put("client_id", Collections.singletonList(INTEGRATION_TEST_CLIENT_ID));
        map.put("client_secret", Collections.singletonList(clientSecret));
        map.put("username", Collections.singletonList(TEST_USER_NAME));
        map.put("password", Collections.singletonList(TEST_USER_PASSWORD));
        KeycloakToken token =
                restTemplate.postForObject(
                        "http://" + keycloak.getHost() + ":" + keycloak.getMappedPort(8080) + "/realms/" + REALM_NAME + "/protocol/openid-connect/token",
                        new HttpEntity<>(map, httpHeaders),
                        KeycloakToken.class);

        assert token != null;
        return token.accessToken();
    }

    private record KeycloakToken(@JsonProperty("access_token") String accessToken) {
    }
}
