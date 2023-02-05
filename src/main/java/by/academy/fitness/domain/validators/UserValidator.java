package by.academy.fitness.domain.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import by.academy.fitness.domain.dto.UserDTO;
import by.academy.fitness.exceptions.ValidationException;

@Component
public class UserValidator implements IValidator<UserDTO> {

	@Override
	public void validate(UserDTO dto) {

		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("Dto is null");
		}
		if (dto.getUsername() == null || dto.getUsername().isBlank()) {
			errors.add("Username is empty");
		}
		if (dto.getEmail() == null || dto.getEmail().isBlank()) {
			errors.add("Email is empty");
		}

		if (dto.getPassword() == null || dto.getPassword().isBlank()) {
			errors.add("Password is empty");
		}

		if (errors.size() > 0) {
			throw new ValidationException(String.join(", ", errors));
		}
	}

//	public static boolean patternMatches(String emailAddress, String regexPattern) {
//	    return Pattern.compile(regexPattern)
//	      .matcher(emailAddress)
//	      .matches();
//	}

}
