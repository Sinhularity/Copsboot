package mx.uv.coatza.S22017021.copsboot.repository.user;

import java.util.UUID;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public UserRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public UserId nextId() {
        return new UserId(generator.getNextUniqueId());
    }
}
