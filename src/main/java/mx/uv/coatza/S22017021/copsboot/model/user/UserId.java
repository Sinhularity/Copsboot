package mx.uv.coatza.S22017021.copsboot.model.user;

import mx.uv.coatza.S22017021.copsboot.model.entity.AbstractEntityId;

import java.util.UUID;

public class UserId extends AbstractEntityId<UUID> {
    protected UserId() {}
    
    public UserId(UUID id) {
        super(id);
    }
}
