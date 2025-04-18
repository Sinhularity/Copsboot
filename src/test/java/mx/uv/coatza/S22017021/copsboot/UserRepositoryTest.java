package mx.uv.coatza.S22017021.copsboot;

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import io.github.wimdeblauwe.jpearl.InMemoryUniqueIdGenerator;
import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;
import mx.uv.coatza.S22017021.copsboot.model.User;
import mx.uv.coatza.S22017021.copsboot.model.UserRole;
import mx.uv.coatza.S22017021.copsboot.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public UniqueIdGenerator<UUID> generator() {
            return new InMemoryUniqueIdGenerator();
        }
    }

    @Autowired
    private UserRepository userRepository;

    // @Test
    // public void testStoreUser() {
    //     HashSet<UserRole> roles = new HashSet<>();
    //     roles.add(UserRole.OFFICER);
    //     User user = userRepository.save(new User(userRepository.nextId(), "testuser", "testpassword", roles));
    //     assertThat(user).isNotNull();
    //     assertThat(userRepository.count()).isEqualTo(1L);
    // }
}
