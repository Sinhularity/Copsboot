package mx.uv.coatza.S22017021.copsboot.model.report.web.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mx.uv.coatza.S22017021.copsboot.model.report.web.annotations.UniqueMobileToken;
import mx.uv.coatza.S22017021.copsboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueMobileTokenValidator implements ConstraintValidator<UniqueMobileToken, String> {

    private final UserService userService;

    @Autowired
    public UniqueMobileTokenValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return userService.findUserByMobileToken(value).isEmpty();
    }
}

