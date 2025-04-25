package mx.uv.coatza.S22017021.copsboot.model.report.web;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mx.uv.coatza.S22017021.copsboot.model.report.web.annotations.ValidCreateUserRequest;
import mx.uv.coatza.S22017021.copsboot.service.user.CreateUserRequest;
import mx.uv.coatza.S22017021.copsboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateUserRequestValidator implements ConstraintValidator <ValidCreateUserRequest, CreateUserRequest> {
    private final UserService userService;

    @Autowired
    public CreateUserRequestValidator(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void initialize(ValidCreateUserRequest constraintAnnotation) {

    }

    @Override
    public boolean isValid(CreateUserRequest userRequest, ConstraintValidatorContext context) {
        boolean result = true;
        if (userService.findUserByMobileToken(userRequest.mobileToken()).isPresent()) {
            context.buildConstraintViolationWithTemplate(
                            "There is already a user with the given mobile token.")
                    .addPropertyNode("mobileToken").addConstraintViolation();

            result = false;
        }

        return result;
    }
}
