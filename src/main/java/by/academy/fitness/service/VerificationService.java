package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.VerificationDao;
import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.entity.User.USERSTATUS;
import by.academy.fitness.domain.entity.VerificationToken;
import by.academy.fitness.security.filters.CryptoUtil;
import by.academy.fitness.service.interf.IUserService;
import by.academy.fitness.service.interf.IVerificationService;

@Service
public class VerificationService implements IVerificationService {

	private final IUserService userService;
	private final EmailServiceImpl mailServiceImpl;
	private final VerificationDao verificationDao;

	@Autowired
	public VerificationService(IUserService userService, EmailServiceImpl mailServiceImpl, VerificationDao verificationDao) {
		this.userService = userService;
		this.mailServiceImpl = mailServiceImpl;
		this.verificationDao=verificationDao;
	}

	@Override
	public void sendMessage(String email, String token) {
		String message = "To activate account follow the link " + "http://localhost:8080/api/auth/verify/"
				+ token;
		this.mailServiceImpl.sendSimpleMessage(email, "Activation code", message);
	}

	@Transactional
	@Override
	public User waitingActivation(UserRegistrationDTO dto) {
		User user = this.userService.create(dto);
		VerificationToken token = new VerificationToken();
		token.setDtCreate(LocalDateTime.now());
		token.setUuid(UUID.randomUUID());
		token.setUser(user);
		token.setToken(CryptoUtil.encrypt("VerificationToken", token.getUuid().toString()+"|"+user.getEmail()));
		verificationDao.create(token);
		sendMessage(user.getEmail(), token.getToken());
		
		return user;
	}

	@Override
	public boolean verify(String token) {
		String info = CryptoUtil.decrypt("VerificationToken", token);
		String[]parts = info.split("\\|");
		if (parts.length!=2) {
			//to do throw exception
		}
		User user = userService.findByEmail(parts[1]);
		//if null
		VerificationToken t = verificationDao.findByUuid(UUID.fromString(parts[0]));
		//if null
		if( t.getUser().equals(user)){
			user.setStatus(USERSTATUS.ACTIVATED);
			return true;
		}
		return false;
	}
	
}