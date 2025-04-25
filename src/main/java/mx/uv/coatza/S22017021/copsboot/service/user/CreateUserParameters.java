package mx.uv.coatza.S22017021.copsboot.service.user;

import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;

public record CreateUserParameters(AuthServerId authServerId, String email, String mobileToken) {

}
