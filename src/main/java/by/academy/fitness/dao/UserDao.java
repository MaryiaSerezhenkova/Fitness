package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.criteria.CriteriaUpdate;

import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IUserDao;
import by.academy.fitness.domain.entity.User;
@Repository
public class UserDao extends BaseEntityDAO<UUID, User> implements IUserDao {

	@Override
	protected void updateFields(CriteriaUpdate<User> criteria, User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Class<User> getClazz() {
		
		return User.class;
	}

	

}
