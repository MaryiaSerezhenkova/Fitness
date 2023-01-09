package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.dao.UserDao;
import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.service.interf.IUserService;
@Service
public class UserService implements IUserService {
	
	private final UserDao userDao;

	public UserService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public User create(UserRegistrationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User read(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(UUID uuid, LocalDateTime dtUpdate, UserRegistrationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub
		
	}
	
	

}
