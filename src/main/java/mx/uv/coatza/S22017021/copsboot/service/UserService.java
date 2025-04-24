package mx.uv.coatza.S22017021.copsboot.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.user.User;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;
import mx.uv.coatza.S22017021.copsboot.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findUserByAuthServerId(AuthServerId authServerId) {
        return repository.findByAuthServerId(authServerId);
    }

    public User createUser(CreateUserParameters createUserParameters) {
        UserId userId = repository.nextId();
        User user = new User(userId, createUserParameters.email(),
                createUserParameters.authServerId(),
                createUserParameters.mobileToken());
        return repository.save(user);
    }
}
