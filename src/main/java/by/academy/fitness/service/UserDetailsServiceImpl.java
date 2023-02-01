package by.academy.fitness.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.academy.fitness.dao.UserDao;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserDao userDao;
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = new User();
		try {
			//user = userDao.findByUsername(username);
			user = userDao.findByEmail(email);
		} catch (UsernameNotFoundException e) {
			logger.error("User Not Found with username: " + email);
		}

		return UserDetailsImpl.build(user);
	}

}