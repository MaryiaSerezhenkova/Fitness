package by.academy.fitness.domain.builders;

import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.entity.User;

public class UserMapper {
	public static User userRegistrationMapping(UserRegistrationDTO dto) {
		return new User(dto.getEmail(), dto.getUsername(), dto.getPassword());
	}

	public static User userUI(User user) {
		return new User(user.getUuid(), user.getDtCreate(), user.getDtUpdate());
	}
}
