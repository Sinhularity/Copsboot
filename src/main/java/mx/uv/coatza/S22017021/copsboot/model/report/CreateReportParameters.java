package mx.uv.coatza.S22017021.copsboot.model.report;

import mx.uv.coatza.S22017021.copsboot.model.user.UserId;

import java.time.Instant;

public record CreateReportParameters (UserId reporterId, Instant dateTime, String description) {
}
