package by.academy.fitness.service.interf;

import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.entity.User;

public interface IVerificationService {
	void sendMessage(User user);

	User waitingActivation (UserRegistrationDTO dto);

}
