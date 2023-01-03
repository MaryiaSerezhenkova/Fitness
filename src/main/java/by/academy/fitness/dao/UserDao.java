package by.academy.fitness.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import by.academy.fitness.dao.interf.IUserDao;
import by.academy.fitness.domain.entity.User;

public class UserDao implements IUserDao {

	@Override
	public User create(User item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUuid(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(UUID uuid, LocalDateTime dtUpdate, User type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub
		
	}

}
