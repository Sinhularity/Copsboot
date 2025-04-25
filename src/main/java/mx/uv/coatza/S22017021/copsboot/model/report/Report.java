package mx.uv.coatza.S22017021.copsboot.model.report;

import jakarta.persistence.Entity;
import mx.uv.coatza.S22017021.copsboot.model.ArtifactForFramework;
import mx.uv.coatza.S22017021.copsboot.model.entity.AbstractEntity;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;

import java.time.Instant;

@Entity
public class Report extends AbstractEntity<ReportId> {

    private UserId reporterId;
    private Instant dateTime;
    private String description;

    @ArtifactForFramework
    protected Report() {
    }

    public Report(ReportId id, UserId reporterId, Instant dateTime, String description) {
        super(id);
        this.reporterId = reporterId;
        this.dateTime = dateTime;
        this.description = description;
    }

    public UserId getReporterId() {
        return reporterId;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }
}