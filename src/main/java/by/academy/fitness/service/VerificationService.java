package by.academy.fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.service.interf.IUserService;
import by.academy.fitness.service.interf.IVerificationService;

@Service
public class VerificationService implements IVerificationService {

	private final IUserService userService;
	private final EmailServiceImpl mailServiceImpl;

	@Autowired
	public VerificationService(IUserService userService, EmailServiceImpl mailServiceImpl) {
		this.userService = userService;
		this.mailServiceImpl = mailServiceImpl;
	}

	@Override
	public void sendMessage(User user) {
		String message = "To activate account follow the link " + "http://localhost:8080/api/v1/users/activate/"
				+ user.getUuid() + "/userMail/" + user.getEmail();
		this.mailServiceImpl.sendSimpleMessage(user.getEmail(), "Activation code", message);
	}

	@Transactional
	@Override
	public User waitingActivation(UserRegistrationDTO dto) {
		User user = this.userService.create(dto);
		sendMessage(user);
		return user;
	}
}