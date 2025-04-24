package mx.uv.coatza.S22017021.copsboot.model.entity;


import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import mx.uv.coatza.S22017021.copsboot.model.ArtifactForFramework;

@MappedSuperclass
public abstract class AbstractEntity <T extends EntityId<?>> implements Entity<T> {
    @EmbeddedId
    private T id;

    @ArtifactForFramework
    protected AbstractEntity() {
    }

    public AbstractEntity(T id) {
        this.id = checkNotNull(id);
    }

    @Override
    public T getId() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (this == obj) result = true;

        if (obj instanceof AbstractEntity) {
            AbstractEntity<?> other = (AbstractEntity<?>) obj;
            result = id.equals(other.id);
        } 

        return result;
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    @Override
    public String toString() {
        return toStringHelper(this).add("id", id).toString();
    }
    
}
