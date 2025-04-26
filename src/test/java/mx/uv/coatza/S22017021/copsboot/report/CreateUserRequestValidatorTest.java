package mx.uv.coatza.S22017021.copsboot.report;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import mx.uv.coatza.S22017021.copsboot.infrastructure.SpringProfiles;
import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.user.User;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;
import mx.uv.coatza.S22017021.copsboot.service.user.CreateUserRequest;
import mx.uv.coatza.S22017021.copsboot.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static mx.uv.coatza.S22017021.copsboot.report.constraint.ConstraintViolationSetAssert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles(SpringProfiles.REPOSITORY_TEST)
public class CreateUserRequestValidatorTest {

    @MockBean
    private UserService userService;
    @Autowired
    private ValidatorFactory factory;

    @Test
    public void invalidIfAlreadyUserWithGivenMobileToken () { // it's adding nothing -> Expected 2 violations, but got 0
        String mobileToken = "abc123";
        when(userService.findUserByMobileToken(mobileToken)).thenReturn(Optional.of(new User(
                new UserId(UUID.randomUUID()),
                "wim@example.com",
                new AuthServerId(UUID.randomUUID()),
                mobileToken
        )));

        Validator validator = factory.getValidator();

        CreateUserRequest request = new CreateUserRequest(mobileToken);
        Set<ConstraintViolation<CreateUserRequest>> violationSet = validator.validate(request);
        assertThat(violationSet)
                .hasViolationSize(1) // NotEmpty doesn't trigger
                .hasViolationOnPath("mobileToken");
    }
    @Test
    public void validIfNoUserWithGivenMobileToken() {
        String mobileToken = "abc123";
        when(userService.findUserByMobileToken(mobileToken))
                .thenReturn(Optional.empty());

        Validator validator = factory.getValidator();

        CreateUserRequest request = new CreateUserRequest(mobileToken);
        Set<ConstraintViolation<CreateUserRequest>> violationSet = validator.validate(request);
        assertThat(violationSet).hasNoViolations();
    }
}
