package mx.uv.coatza.S22017021.copsboot.model.entity;

/**
 * Interface for entity objects.
 * @param <T> the type of {@link EntityId} that will be used in this entity
 */
public interface  Entity <T extends EntityId> {
    T getId();
}
