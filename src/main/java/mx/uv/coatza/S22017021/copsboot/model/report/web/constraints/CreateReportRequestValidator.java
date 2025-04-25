package mx.uv.coatza.S22017021.copsboot.model.report.web.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mx.uv.coatza.S22017021.copsboot.model.report.web.CreateReportRequest;
import mx.uv.coatza.S22017021.copsboot.model.report.web.ValidCreateReportRequest;

public class CreateReportRequestValidator implements ConstraintValidator<ValidCreateReportRequest, CreateReportRequest> {

    @Override
    public void initialize(ValidCreateReportRequest constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreateReportRequest value, ConstraintValidatorContext context) {
        boolean result = true;
        if (value.trafficIncident() && value.numberOfInvolvedCars() <= 0) {
            result = false;
        }
        return result;
    }
}
