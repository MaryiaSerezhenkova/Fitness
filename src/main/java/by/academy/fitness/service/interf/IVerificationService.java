package by.academy.fitness.service.interf;

import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.entity.User;

public interface IVerificationService {
	public void sendMessage(String email, String token);

	User waitingActivation(UserRegistrationDTO dto);

	boolean verify(String token);

}
