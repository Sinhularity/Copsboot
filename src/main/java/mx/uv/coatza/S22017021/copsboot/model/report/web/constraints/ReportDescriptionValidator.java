package mx.uv.coatza.S22017021.copsboot.model.report.web.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mx.uv.coatza.S22017021.copsboot.model.report.web.annotations.ValidReportDescription;

public class ReportDescriptionValidator implements ConstraintValidator<ValidReportDescription, String> {

    @Override
    public void initialize(ValidReportDescription constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = true;
        if (!value.toLowerCase().contains("suspect")) {
            result = false;
        }
        return result;
    }
}
