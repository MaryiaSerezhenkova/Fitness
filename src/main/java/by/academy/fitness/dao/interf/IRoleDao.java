package by.academy.fitness.dao.interf;

import org.springframework.stereotype.Repository;

import by.academy.fitness.domain.entity.Role;
import by.academy.fitness.domain.entity.User.ROLE;

@Repository
public interface IRoleDao  {
	Role findByName(ROLE name);
}