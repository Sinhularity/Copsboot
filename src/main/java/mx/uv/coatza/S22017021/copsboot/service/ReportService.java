package mx.uv.coatza.S22017021.copsboot.service;

import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.report.CreateReportParameters;
import mx.uv.coatza.S22017021.copsboot.model.report.Report;
import mx.uv.coatza.S22017021.copsboot.model.report.ReportId;
import mx.uv.coatza.S22017021.copsboot.model.user.User;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;
import mx.uv.coatza.S22017021.copsboot.repository.ReportRepository;
import mx.uv.coatza.S22017021.copsboot.repository.UserRepository;

import java.util.Optional;

public class ReportService {
    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public Optional<Report> findReportByAuthServerId(AuthServerId authServerId) {
        return repository.findByAuthServerId(authServerId);
    }

    public Report createReport(CreateReportParameters createReportParameters) {
        ReportId reportId = repository.nextId();
        Report report = new Report(reportId, createReportParameters.reporterId(),
                createReportParameters.dateTime(),
                createReportParameters.description());
        return repository.save(report);
    }
}
