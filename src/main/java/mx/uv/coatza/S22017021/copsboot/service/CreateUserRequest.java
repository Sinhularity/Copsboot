package mx.uv.coatza.S22017021.copsboot.service;

import java.util.UUID;

import org.springframework.security.oauth2.jwt.Jwt;

import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;

public record CreateUserRequest(String mobileToken) {
    public CreateUserParameters toParameters(Jwt jwt) {
        AuthServerId authServerId = new AuthServerId(UUID.fromString(jwt.getSubject()));

        String email = jwt.getClaimAsString("email");
        return new CreateUserParameters(authServerId,email,mobileToken);
    }
}
