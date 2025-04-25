package mx.uv.coatza.S22017021.copsboot.repository.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;



import mx.uv.coatza.S22017021.copsboot.model.AuthServerId;
import mx.uv.coatza.S22017021.copsboot.model.user.User;
import mx.uv.coatza.S22017021.copsboot.model.user.UserId;

public interface UserRepository extends CrudRepository<User, UserId>, UserRepositoryCustom {
    Optional<User> findByAuthServerId(AuthServerId authServerId);
}
