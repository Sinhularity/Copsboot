package mx.uv.coatza.S22017021.copsboot.model.report;

import mx.uv.coatza.S22017021.copsboot.model.ArtifactForFramework;
import mx.uv.coatza.S22017021.copsboot.model.entity.AbstractEntityId;

import java.util.UUID;

public class ReportId extends AbstractEntityId<UUID> {

    @ArtifactForFramework
    protected ReportId() {}

    public ReportId(UUID id) {super(id);}
}
