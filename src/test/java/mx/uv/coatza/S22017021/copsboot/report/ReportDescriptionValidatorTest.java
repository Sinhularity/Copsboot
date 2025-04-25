package mx.uv.coatza.S22017021.copsboot.report;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import mx.uv.coatza.S22017021.copsboot.model.report.web.CreateReportRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.time.Instant;
import java.util.Set;

import static mx.uv.coatza.S22017021.copsboot.constraints.ConstraintViolationSetAssert.assertThat;


public class ReportDescriptionValidatorTest {

    @Test
    public void givenEmptyString_notValid() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            CreateReportRequest request = new CreateReportRequest(Instant.now(), "", false, 0,
                    createImage());
            Set<ConstraintViolation<CreateReportRequest>> violationSet = validator.validate(request);
            assertThat(violationSet).hasViolationOnPath("description");
        }
    }

    @Test
    public void givenSuspectWordPresent_valid() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            CreateReportRequest request = new CreateReportRequest(Instant.now(),
                    "The suspect was wearing a black hat.", false, 0,
                    createImage());
            Set<ConstraintViolation<CreateReportRequest>> violationSet = validator.validate(request);
            assertThat(violationSet).hasNoViolations();
        }
    }

    @NotNull
    private static MockMultipartFile createImage() {
        return new MockMultipartFile("image", "picture.png", MediaType.IMAGE_PNG_VALUE, new byte[]{1, 2, 3});
    }
}
