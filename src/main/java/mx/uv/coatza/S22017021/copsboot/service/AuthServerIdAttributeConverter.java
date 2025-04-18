package mx.uv.coatza.S22017021.copsboot.service;

import java.util.UUID;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;

@Converter(autoApply = true)
public class AuthServerIdAttributeConverter implements AttributeConverter<AuthServerId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(AuthServerId attribute) {
        return attribute.value();
    }

    @Override
    public AuthServerId convertToEntityAttribute(UUID dbData) {
        return new AuthServerId(dbData);
    }

}
