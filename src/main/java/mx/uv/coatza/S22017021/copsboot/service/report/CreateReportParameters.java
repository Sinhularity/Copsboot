package mx.uv.coatza.S22017021.copsboot.service.report;

import mx.uv.coatza.S22017021.copsboot.model.user.UserId;

import java.time.Instant;

public record CreateReportParameters (UserId userId, Instant dateTime, String description) {
}
