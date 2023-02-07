package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.dao.UserDao;
import by.academy.fitness.domain.dto.UserDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Audit.ESSENCETYPE;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Role;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.entity.User.ROLE;
import by.academy.fitness.domain.entity.User.USERSTATUS;
import by.academy.fitness.domain.mapper.impl.UserMapper;
import by.academy.fitness.domain.validators.UserValidator;
import by.academy.fitness.service.interf.IUserService;

@Service
public class UserService implements IUserService {
	private final static String WAITING_ACTIVATION = "User sent request for activation";
	private final static String UPDATED = "User updated";

	private final UserDao userDao;
	private final RoleService roleService;
	private final UserValidator validator;
	private final PasswordEncoder encoder;
	private final AuditService auditService;
	private final UserMapper mapper;

	@Autowired
	public UserService(UserDao userDao, RoleService roleService, UserValidator validator, PasswordEncoder encoder,
			AuditService auditService, UserMapper mapper) {
		this.userDao = userDao;
		this.roleService = roleService;
		this.validator = validator;
		this.encoder = encoder;
		this.auditService = auditService;
		this.mapper = mapper;
	}

	@Transactional
	@Override
	public UserDTO create(UserDTO dto) {
		validator.validate(dto);
		Role role = roleService.findByName(ROLE.USER);
		User user = mapper.toEntity(dto);
		user.setUuid(UUID.randomUUID());
		user.setDtCreate(LocalDateTime.now());
		user.setDtUpdate(user.getDtCreate());
		user.setRoles(Set.of(role));
		user.setStatus(USERSTATUS.WAITING_ACTIVATION);
		user.setPassword(encoder.encode(dto.getPassword()));
		User newUser = userDao.create(user);
		auditService.create(new Audit(WAITING_ACTIVATION, ESSENCETYPE.USER, newUser.getUsername()), newUser.getUuid());
		return mapper.toDTO(newUser);
	}

	@Transactional
	@Override
	public UserDTO read(UUID uuid) {
		return mapper.toDTO(userDao.findByUuid(uuid));
	}

	@Transactional
	@Override
	public Page<UserDTO> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		Page<UserDTO> page = new Page<>();
		page.setContent(userDao.findAll(amount, skip, sortings, filters).stream().map(mapper::toDTO)
				.collect(Collectors.toList()));
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
	public UserDTO update(UUID uuid, LocalDateTime dtUpdate, UserDTO dto) {
		validator.validate(dto);
		User readed = userDao.findByUuid(uuid);
		if (readed == null) {
			throw new IllegalArgumentException("Item not found");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("Sorry, this item has already been edited");
		}
		readed.setDtUpdate(LocalDateTime.now());
		readed.setEmail(dto.getEmail());
		readed.setUsername(dto.getUsername());
		readed.setPassword(dto.getPassword());
		readed.setStatus(dto.getStatus());
		auditService.create(new Audit(UPDATED, ESSENCETYPE.USER, readed.getUsername()), readed.getUuid());
		return mapper.toDTO(userDao.create(readed));
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		User readed = userDao.findByUuid(uuid);
		if (readed == null) {
			throw new IllegalArgumentException("Not found");
		}
		 if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("Version is outdated");
		}
		readed.setStatus(USERSTATUS.DEACTIVATED);
		userDao.update(uuid, dtUpdate, readed);

	}

	@Transactional
	public Boolean existsByEmail(String email) {
		return userDao.existsByEmail(email);
	}

	@Transactional
	public UserDTO findByEmail(String email) {
		return mapper.toDTO(userDao.findByEmail(email));
	}

	@Transactional
	public Boolean existsByUsername(String username) {
		return userDao.existsByUsername(username);
	}

	@Transactional
	public UserDTO findByUsername(String username) {
		return mapper.toDTO(userDao.findByUsername(username));
	}

}
