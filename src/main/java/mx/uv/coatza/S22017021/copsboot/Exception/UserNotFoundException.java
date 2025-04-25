package mx.uv.coatza.S22017021.copsboot.Exception;

import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UserId userId) {
        super(String.format("Unable to find user with id %s", userId));
    }

    public UserNotFoundException(AuthServerId authServerId) {
        super(String.format("Unable to find user with auth server id %s", authServerId));
    }
}
