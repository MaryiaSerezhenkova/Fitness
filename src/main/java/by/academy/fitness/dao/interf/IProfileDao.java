package by.academy.fitness.dao.interf;

import by.academy.fitness.domain.entity.Profile;
import by.academy.fitness.domain.entity.User;

public interface IProfileDao  extends IDao<Profile>{
	Profile findByUser(User user);

	Boolean existsByUser(User user);

}
