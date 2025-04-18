package mx.uv.coatza.S22017021.copsboot.model;

import java.util.UUID;

import org.springframework.util.Assert;

public record AuthServerId(UUID value) {
    public AuthServerId {
        Assert.notNull(value, "The AuthServerId value should not be null");
    }
}
