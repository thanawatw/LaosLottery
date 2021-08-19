package intl.lotto.laoslottery.repositories;

import java.util.Optional;

import intl.lotto.laoslottery.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
