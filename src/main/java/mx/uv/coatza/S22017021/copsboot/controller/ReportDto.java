package mx.uv.coatza.S22017021.copsboot.controller;

import jakarta.persistence.Entity;
import mx.uv.coatza.S22017021.copsboot.model.report.Report;
import mx.uv.coatza.S22017021.copsboot.model.report.ReportId;

import java.time.Instant;


public class ReportDto {
    private ReportId reportId;
    private Instant dateTime;
    private String description;

    protected ReportDto(ReportId reportId, Instant dateTime, String description) {
        this.reportId = reportId;
        this.dateTime = dateTime;
        this.description = description;
    }

    public ReportId getReportId() {
        return reportId;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }
    public static ReportDto fromReport(Report report) {
        return new ReportDto(
                report.getId(),
                report.getDateTime(),
                report.getDescription()
        );
    }
}
