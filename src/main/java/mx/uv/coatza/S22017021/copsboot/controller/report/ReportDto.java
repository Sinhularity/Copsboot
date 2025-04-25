package mx.uv.coatza.S22017021.copsboot.controller.report;

import mx.uv.coatza.S22017021.copsboot.model.report.Report;
import mx.uv.coatza.S22017021.copsboot.model.report.ReportId;
import mx.uv.coatza.S22017021.copsboot.service.user.UserService;

import java.time.Instant;


public record ReportDto(ReportId id,
                        String reporter,
                        Instant dateTime,
                        String description) {

    public static ReportDto fromReport(Report report, UserService userService) {
        return new ReportDto(report.getId(),
                userService.getUserById(report.getReporterId()).getEmail(),
                report.getDateTime(),
                report.getDescription());
    }
}
