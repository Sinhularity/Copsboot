package mx.uv.coatza.S22017021.copsboot.model.report.web.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import mx.uv.coatza.S22017021.copsboot.model.report.web.CreateUserRequestValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CreateUserRequestValidator.class})
public @interface ValidCreateUserRequest {
    String message() default "Invalid user";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
