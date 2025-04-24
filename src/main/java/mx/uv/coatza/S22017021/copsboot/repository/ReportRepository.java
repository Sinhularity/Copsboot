package mx.uv.coatza.S22017021.copsboot.repository;

import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.report.Report;
import mx.uv.coatza.S22017021.copsboot.model.report.ReportId;
import mx.uv.coatza.S22017021.copsboot.model.report.ReportRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReportRepository extends CrudRepository<Report, ReportId>, ReportRepositoryCustom {
    Optional<Report> findByAuthServerId(AuthServerId authServerId);
}