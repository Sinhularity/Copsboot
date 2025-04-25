package mx.uv.coatza.S22017021.copsboot.service.user;

import java.util.Optional;

import jakarta.transaction.Transactional;
import mx.uv.coatza.S22017021.copsboot.Exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.user.User;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;
import mx.uv.coatza.S22017021.copsboot.repository.user.UserRepository;

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

    public User getUserById(UserId userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public Optional<User> findUserByMobileToken(String mobileToken) {
        return repository.findByMobileToken(mobileToken);
    }
}
