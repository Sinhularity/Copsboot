package mx.uv.coatza.S22017021.copsboot.repository.report;

import mx.uv.coatza.S22017021.copsboot.model.report.Report;
import mx.uv.coatza.S22017021.copsboot.model.report.ReportId;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, ReportId>, ReportRepositoryCustom {
}