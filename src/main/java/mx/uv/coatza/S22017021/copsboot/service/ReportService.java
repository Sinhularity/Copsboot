package mx.uv.coatza.S22017021.copsboot.service;

import mx.uv.coatza.S22017021.copsboot.model.report.Report;
import mx.uv.coatza.S22017021.copsboot.repository.report.ReportRepository;
import mx.uv.coatza.S22017021.copsboot.service.report.CreateReportParameters;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public Report createReport(CreateReportParameters createReportParameters) {
        return repository.save(new Report(repository.nextId()
                ,createReportParameters.userId()
                ,createReportParameters.dateTime()
                , createReportParameters.description()));
    }
}
