package by.academy.fitness.service.interf;

import by.academy.fitness.domain.dto.UserDTO;
import by.academy.fitness.domain.entity.VerificationToken;

public interface IVerificationService {
	public void sendMessage(String email, String token);

	UserDTO registration(UserDTO dto);

	boolean verify(String token);

	UserDTO waitingActivation(UserDTO dto);

	VerificationToken findByUser(UserDTO dto);

}
