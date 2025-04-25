package mx.uv.coatza.S22017021.copsboot.controller.user;

import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.user.User;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;

public class UserDto {
    private String userId;
    private String email;
    private String authServerId;
    private String mobileToken;

    protected UserDto() {
    }

    public UserDto(UserId id, String email, AuthServerId authServerId, String mobileToken) {
        this.userId = id.asString();
        this.email = email;
        this.authServerId = authServerId.value().toString();
        this.mobileToken = mobileToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthServerId() {
        return authServerId;
    }

    public String getMobileToken() {
        return mobileToken;
    }

    public static UserDto fromUser(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getAuthServerId(),
                user.getMobileToken());
    }
}
