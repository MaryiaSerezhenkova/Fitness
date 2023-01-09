package by.academy.fitness.dao.interf;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.academy.fitness.domain.entity.Role;
import by.academy.fitness.domain.entity.User.ROLE;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ROLE name);
}