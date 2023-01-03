package by.academy.fitness.dao.interf;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.academy.fitness.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String nick);

	Boolean existsByUsername(String nick);

	Boolean existsByEmail(String email);
}