package mx.uv.coatza.S22017021.copsboot.model.report.web.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import mx.uv.coatza.S22017021.copsboot.model.report.web.constraints.UniqueMobileTokenValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueMobileTokenValidator.class)
public @interface UniqueMobileToken {
    String message() default "Mobile token already exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
