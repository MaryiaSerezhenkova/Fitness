package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.ProfileDao;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.dto.ProfileDTO;
import by.academy.fitness.domain.dto.UserDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Audit.ESSENCETYPE;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Profile;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.mapper.impl.ProfileMapper;
import by.academy.fitness.service.interf.IProfileService;

@Service
public class ProfileService implements IProfileService {

	private final static String CREATED = "Profile created";
	private final static String UPDATED = "Profile updated";
	private final static String DELETED = "Profile deleted";

	private final ProfileDao profileDao;
	private final UserService userService;
	private final AuditService auditService;
	private final ProfileMapper mapper;

	@Autowired
	public ProfileService(ProfileDao profileDao, UserService userService, AuditService auditService,
			ProfileMapper mapper) {
		super();
		this.profileDao = profileDao;
		this.userService = userService;
		this.auditService = auditService;
		this.mapper = mapper;
	}

	@Transactional
	@Override
	public ProfileDTO create(ProfileDTO dto) {
		UserDTO userDto = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Profile profile = mapper.toEntity(dto);
		profile.setUuid(UUID.randomUUID());
		profile.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
		profile.setDtUpdate(profile.getDtCreate());
		profile.setUserId(userDto.getUuid());
		auditService.create(new Audit(CREATED, ESSENCETYPE.PROFILE, profile.getUuid().toString()),
				userDto.getUuid());
		return mapper.toDTO(profileDao.create(profile));
	}

	@Transactional
	@Override
	public ProfileDTO read(UUID uuid) {
		return mapper.toDTO(profileDao.findByUuid(uuid));
	}

	@Transactional
	@Override
	public Page<ProfileDTO> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public ProfileDTO update(UUID uuid, LocalDateTime dtUpdate, ProfileDTO dto) {
		UserDTO userDto = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Profile readed = profileDao.findByUuid(uuid);

		if (readed == null) {
			throw new IllegalArgumentException("Item not found");
		}
		if (!readed.getUser().equals(userDto)) {
			throw new IllegalArgumentException("You can only update the profile you created");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("Sorry, this item has already been edited");
		}
		readed.setDtUpdate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
		readed.setDtBirthday(dto.getDtBirthday());
		readed.setHeight(dto.getHeight());
		readed.setWeight(dto.getWeight());
		readed.setTarget(dto.getTarget());
		readed.setType(dto.getType());
		readed.setGender(dto.getGender());
		readed.setUser(new User(userDto.getUuid()));
		auditService.create(new Audit(UPDATED, ESSENCETYPE.PROFILE, readed.getUuid().toString()),
				userDto.getUuid());
		return mapper.toDTO(profileDao.create(readed));
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		UserDTO userDto = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		ProfileDTO profile = mapper.toDTO(profileDao.findByUuid(uuid));

		if (profile == null) {
			throw new IllegalArgumentException("Item not found");
		}
		if (!profile.getUser().equals(userDto)) {
			throw new IllegalArgumentException("You can only update the profile you created");
		}

		if (!profile.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("Sorry, this item has already been edited");
		}
		auditService.create(new Audit(DELETED, ESSENCETYPE.PROFILE, profile.getId().toString()),
				userDto.getUuid());
		profileDao.delete(uuid, dtUpdate);
	}

	@Override
	public Profile findByUser(User user) {
		return profileDao.findByUser(user);
	}

	@Override
	public ProfileDTO findByUserId(UUID id) {
		return mapper.toDTO(profileDao.findByUserId(id));
	}

}
