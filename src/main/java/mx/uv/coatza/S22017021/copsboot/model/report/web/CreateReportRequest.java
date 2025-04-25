package mx.uv.coatza.S22017021.copsboot.model.report.web;

import jakarta.validation.constraints.NotNull;
import mx.uv.coatza.S22017021.copsboot.service.report.CreateReportParameters;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@ValidCreateReportRequest
public record CreateReportRequest(
        Instant dateTime,
        @ValidReportDescription String description,
        boolean trafficIncident,
        int numberOfInvolvedCars,
        @NotNull MultipartFile image
) {
    public CreateReportParameters toParameters(UserId userId) {
        return new CreateReportParameters(userId, dateTime, description);
    }
}
