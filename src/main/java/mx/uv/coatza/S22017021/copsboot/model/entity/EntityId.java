package mx.uv.coatza.S22017021.copsboot.model.entity;

import java.io.Serializable;

/**
 * Interface for primary keys of entities.
 *
 * @param <T> the underlying type of the entity id
 */
public interface EntityId<T> extends Serializable {
    T getId();

    String asString();
}