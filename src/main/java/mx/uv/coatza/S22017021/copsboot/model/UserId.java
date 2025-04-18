package mx.uv.coatza.S22017021.copsboot.model;

import java.util.UUID;

public class UserId extends AbstractEntityId<UUID> {
    protected UserId() {}
    
    public UserId(UUID id) {
        super(id);
    }
    
}
