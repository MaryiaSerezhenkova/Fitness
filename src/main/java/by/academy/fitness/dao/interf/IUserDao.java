package by.academy.fitness.dao.interf;

import by.academy.fitness.domain.entity.User;

public interface IUserDao extends IDao<User>{
	
	User findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}


