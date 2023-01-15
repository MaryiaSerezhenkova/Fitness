package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.dao.VerificationDao;
import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.dto.VerificationDTO;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.entity.VerificationToken;
import by.academy.fitness.security.jwt.JwtUtils;
import by.academy.fitness.service.interf.IVerificationService;
@Service
public class VerificationService implements IVerificationService {
	
	private final VerificationDao verDao;
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	JwtUtils JwtUtils;
	
	
	public VerificationService(VerificationDao verDao, PasswordEncoder passwordEncoder, UserService userService) {
		super();
		this.verDao = verDao;
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
	}
	@Transactional
	@Override
	public VerificationToken create(VerificationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public VerificationToken read(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public Page<VerificationToken> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public VerificationToken update(UUID uuid, LocalDateTime dtUpdate, VerificationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	@Override
	public VerificationToken register(UserRegistrationDTO dto) {
		userService.existsByEmail(dto.getEmail());
        User user = userService.create(dto);
    
        VerificationToken verificationToken = new VerificationToken(JwtUtils.generateJwtToken(null), user);
        verDao.create(verificationToken);
        return verificationToken;
	}
	@Transactional
	@Override
	public User verifyUser(VerificationDTO verificationDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
