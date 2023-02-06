package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriUtils;

import by.academy.fitness.dao.VerificationDao;
import by.academy.fitness.domain.dto.UserDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Audit.ESSENCETYPE;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.entity.User.USERSTATUS;
import by.academy.fitness.domain.entity.VerificationToken;
import by.academy.fitness.domain.validators.SaveUserValidaror;
import by.academy.fitness.exceptions.ValidationException;
import by.academy.fitness.security.filters.CryptoUtil;
import by.academy.fitness.service.interf.IUserService;
import by.academy.fitness.service.interf.IVerificationService;

@Service
public class VerificationService implements IVerificationService {

	private final static String ACTIVATED = "User saved in database. Token sent to email";
	private final static String CONFIRM = "User confirm email";

	private final IUserService userService;
	private final EmailServiceImpl mailServiceImpl;
	private final VerificationDao verificationDao;
	private final AuditService auditService;
	private final SaveUserValidaror validator;

	@Autowired
	public VerificationService(IUserService userService, EmailServiceImpl mailServiceImpl,
			VerificationDao verificationDao, AuditService auditService, SaveUserValidaror validator) {
		this.userService = userService;
		this.mailServiceImpl = mailServiceImpl;
		this.verificationDao = verificationDao;
		this.auditService = auditService;
		this.validator = validator;
	}

	@Override
	public void sendMessage(String email, String token) {
		String message = "To activate account follow the link " + "http://localhost:8080/api/v1/users/verify/" + token;
		this.mailServiceImpl.sendSimpleMessage(email, "Activation code", message);
	}

//	@Transactional
//	@Override
//	public User waitingActivation(UserRegistrationDTO dto) {
//		User user = this.userService.create(dto);
//		VerificationToken token = new VerificationToken();
//		token.setDtCreate(LocalDateTime.now());
//		token.setUuid(UUID.randomUUID());
//		String encodedToken = CryptoUtil.encrypt("VerificationToken",
//				token.getUuid().toString() + "|" + user.getEmail());
//		token.setUser(user);
//		token.setToken(UriUtils.encode(encodedToken, "UTF-8"));
//
//		verificationDao.create(token);
//		sendMessage(user.getEmail(), token.getToken());
//
//		return user;
//	}
	@Transactional
	@Override
	public boolean verify(String token) {
		String info = null;
		info = CryptoUtil.decrypt("VerificationToken", UriUtils.decode(token, "UTF-8"));
		String[] parts = info.split("\\|");
		if (parts.length != 2) {
			throw new ValidationException("Invalid token");
		}
		UserDTO userDto = userService.findByEmail(parts[1]);
		if (userDto == null) {
			throw new ValidationException("User not found");
		}
		VerificationToken t = verificationDao.findByUuid(UUID.fromString(parts[0]));
		if (token == null) {
			throw new ValidationException("User not found");
		}
		if (t.getUser().equals(userDto)) {
			userDto.setStatus(USERSTATUS.ACTIVATED);
			userService.update(userDto.getUuid(), userDto.getDtUpdate(), userDto);
			return true;

		}
		auditService.create(new Audit(CONFIRM, ESSENCETYPE.USER, userDto.getUsername()), userDto.getUuid());
		return false;
	}

	@Transactional
	@Override
	public UserDTO registration(UserDTO dto) {
		return userService.create(dto);
	}

	@Transactional
	@Override
	public UserDTO waitingActivation(UserDTO dto) {
		validator.validate(dto);
		UserDTO userDto = userService.findByEmail(dto.getEmail());
		VerificationToken token = new VerificationToken();
		token.setDtCreate(LocalDateTime.now());
		token.setUuid(UUID.randomUUID());
		String encodedToken = CryptoUtil.encrypt("VerificationToken",
				token.getUuid().toString() + "|" + dto.getEmail());
		token.setUser(new User(userDto.getUuid()));
		token.setToken(UriUtils.encode(encodedToken, "UTF-8"));
		verificationDao.create(token);
		sendMessage(dto.getEmail(), token.getToken());
		auditService.create(new Audit(ACTIVATED, ESSENCETYPE.USER, userDto.getUsername()), userDto.getUuid());

		return userService.findByEmail(dto.getEmail());
	}

}