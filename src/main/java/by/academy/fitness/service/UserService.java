package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.dao.UserDao;
import by.academy.fitness.domain.builders.UserMapper;
import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Role;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.entity.User.ROLE;
import by.academy.fitness.domain.entity.User.USERSTATUS;
import by.academy.fitness.domain.validators.RegistrationValidator;
import by.academy.fitness.service.interf.IUserService;

@Service
public class UserService implements IUserService {

	private final UserDao userDao;
	private final RoleService roleService;
	private final RegistrationValidator validator;
	private final PasswordEncoder encoder;

	@Autowired
	public UserService(UserDao userDao, RoleService roleService, RegistrationValidator validator, PasswordEncoder encoder) {
		super();
		this.userDao = userDao;
		this.roleService = roleService;
		this.validator = validator;
		this.encoder=encoder;
	}

	@Transactional
	@Override
	public User create(UserRegistrationDTO dto) {
		validator.validate(dto);
		Role role = roleService.findByName(ROLE.USER);
		User user = UserMapper.userRegistrationMapping(dto);
		user.setUuid(UUID.randomUUID());
		user.setDtCreate(LocalDateTime.now());
		user.setDtUpdate(user.getDtCreate());
		user.setRoles(Set.of(role));
		user.setStatus(USERSTATUS.WAITING_ACTIVATION);
		user.setPassword(encoder.encode(dto.getPassword()));
		return userDao.create(user);
	}

	@Transactional
	@Override
	public User read(UUID uuid) {
		return userDao.findByUuid(uuid);
	}

	@Transactional
	@Override
	public Page<User> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		Page<User> page = new Page<>();
		page.setContent(userDao.findAll(amount, skip, sortings, filters));
		page.setPageSize(amount);
		int count = userDao.count(filters);
		page.setTotalElements(count);
		int pageSize = count / (amount.intValue() == 0 ? 1 : amount.intValue());
		if (count % (amount.intValue() == 0 ? 1 : amount.intValue()) > 0) {
			pageSize++;
		}
		int currentPage = skip.intValue() / amount.intValue();
		page.setPageNumber(currentPage);
		page.setTotalPages(pageSize);
		page.setFirst(skip == 0);
		page.setLast((count - skip) <= amount);
		;
		int numberOfEl = amount;
		if (amount > (count - skip) & count > skip) {
			numberOfEl = count - skip;
		}
		if (amount > (count - skip) & count < skip) {
			numberOfEl = count;
		}
		page.setNumberOfElements(numberOfEl);
		return page;
	}

	@Transactional
	@Override
	public User update(UUID uuid, LocalDateTime dtUpdate, UserRegistrationDTO dto) {
		validator.validate(dto);
		User readed = userDao.findByUuid(uuid);
		if (readed == null) {
			throw new IllegalArgumentException("Item not found");
		}
//		if (!readed.getUser().equals(user)) {
//			throw new IllegalArgumentException("You can only update the product you created");	
//		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("Sorry, this item has already been edited");
		}
		readed.setDtUpdate(LocalDateTime.now());
		readed.setEmail(dto.getEmail());
		readed.setUsername(dto.getUsername());
		readed.setPassword(dto.getPassword());
		return userDao.update(uuid, dtUpdate, readed);
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		User readed = userDao.findByUuid(uuid);
		if (readed == null) {
			throw new IllegalArgumentException("Not found");
		}
		// if (!readed.getDtUpdate().isEqual(dtUpdate)) {
//			throw new IllegalArgumentException("Version is outdated");
//		}
		readed.setStatus(USERSTATUS.DEACTIVATED);
		userDao.update(uuid, dtUpdate, readed);

	}

	@Transactional
	@Override
	public User updateRole(UUID uuid, ROLE role, LocalDateTime dtUpdate) {
		User readed = userDao.findByUuid(uuid);
		if (readed == null) {
			throw new IllegalArgumentException("Not found");
		}
		// if (!readed.getDtUpdate().isEqual(dtUpdate)) {
//			throw new IllegalArgumentException("Version is outdated");
//		}
		// readed.setRoles(role);
		return userDao.update(uuid, dtUpdate, readed);

	}

	@Transactional
	@Override
	public User updateStatus(UUID uuid, USERSTATUS status, LocalDateTime dtUpdate) {
		User readed = userDao.findByUuid(uuid);
		if (readed == null) {
			throw new IllegalArgumentException("Not found");
		}
		// if (!readed.getDtUpdate().isEqual(dtUpdate)) {
//			throw new IllegalArgumentException("Version is outdated");
//		}
		readed.setStatus(status);
		return userDao.update(uuid, dtUpdate, readed);
	}

	@Transactional
	public Boolean existsByEmail(String email) {
		return userDao.existsByEmail(email);
	}

	@Transactional
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	@Transactional
	public Boolean existsByUsername(String username) {
		return userDao.existsByUsername(username);
	}

	@Transactional
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
