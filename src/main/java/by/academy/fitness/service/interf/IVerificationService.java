package by.academy.fitness.service.interf;

import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.dto.VerificationDTO;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.entity.VerificationToken;

public interface IVerificationService extends IService<VerificationToken, VerificationDTO> {
	VerificationToken register(UserRegistrationDTO dto);

	User verifyUser(VerificationDTO verificationDTO);

}
