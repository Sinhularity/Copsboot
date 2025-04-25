package mx.uv.coatza.S22017021.copsboot.controller.user;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.user.User;
import mx.uv.coatza.S22017021.copsboot.service.user.CreateUserParameters;
import mx.uv.coatza.S22017021.copsboot.service.user.CreateUserRequest;
import mx.uv.coatza.S22017021.copsboot.service.user.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public Map<String, Object> myself(@AuthenticationPrincipal Jwt jwt) {
        Optional<User> userByAuthServerId = userService
                .findUserByAuthServerId(new AuthServerId(UUID.fromString(jwt.getSubject())));

        Map<String, Object> result = new HashMap<>();
        userByAuthServerId.ifPresent(user -> result.put("userId", user.getId().asString()));
        result.put("subject", jwt.getSubject());
        result.put("claims", jwt.getClaims());
        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('OFFICER')")
    public UserDto createUser(@AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateUserRequest request) {
        CreateUserParameters parameters = request.toParameters(jwt);
        User user = userService.createUser(parameters);
        return UserDto.fromUser(user);
    }
}
