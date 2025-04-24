package mx.uv.coatza.S22017021.copsboot.controller.web;

import mx.uv.coatza.S22017021.copsboot.model.report.CreateReportParameters;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;

import java.time.Instant;

public record CreateReportRequest (Instant dateTime, String description) {
    public CreateReportParameters toParameters(UserId userId) {
        return new CreateReportParameters(userId, dateTime, description);
    }
}
