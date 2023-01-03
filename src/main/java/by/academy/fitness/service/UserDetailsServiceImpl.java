package by.academy.fitness.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.academy.fitness.dao.interf.UserRepository;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.service.interf.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(nick)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + nick));

		return UserDetailsImpl.build(user);
	}

}