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
import by.academy.fitness.domain.builders.ProfileMapper;
import by.academy.fitness.domain.builders.UserMapper;
import by.academy.fitness.domain.dto.ProfileDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Audit.ESSENCETYPE;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Profile;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.service.interf.IProfileService;

@Service
public class ProfileService implements IProfileService {

	private final static String CREATED = "Profile created";
	private final static String UPDATED = "Profile updated";
	private final static String DELETED = "Profile deleted";

	private final ProfileDao profileDao;
	private final UserService userService;
	private final AuditService auditService;

	@Autowired
	public ProfileService(ProfileDao profileDao, UserService userService, AuditService auditService) {
		super();
		this.profileDao = profileDao;
		this.userService = userService;
		this.auditService = auditService;
	}

	@Transactional
	@Override
	public Profile create(ProfileDTO dto) {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		Profile profile = ProfileMapper.profileInputMapping(dto);
		profile.setUuid(UUID.randomUUID());
		profile.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
		profile.setDtUpdate(profile.getDtCreate());
		profile.setUser(UserMapper.userUI(user));
		auditService.create(new Audit(CREATED, ESSENCETYPE.PROFILE, profile.getUuid().toString()), user);
		return ProfileMapper.profileOutputMapping(profileDao.create(profile));
	}

	@Transactional
	@Override
	public Profile read(UUID uuid) {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return profileDao.findByUuid(uuid);
	}

	@Transactional
	@Override
	public Page<Profile> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Profile update(UUID uuid, LocalDateTime dtUpdate, ProfileDTO dto) {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		Profile readed = profileDao.findByUuid(uuid);

		if (readed == null) {
			throw new IllegalArgumentException("Item not found");
		}
		if (!readed.getUser().equals(user)) {
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
		readed.setUser(UserMapper.userUI(user));
		auditService.create(new Audit(UPDATED, ESSENCETYPE.PROFILE, readed.getUuid().toString()), UserMapper.userUI(user));
		return profileDao.create(readed);
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		Profile readed = profileDao.findByUuid(uuid);

		if (readed == null) {
			throw new IllegalArgumentException("Item not found");
		}
		if (!readed.getUser().equals(user)) {
			throw new IllegalArgumentException("You can only update the profile you created");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("Sorry, this item has already been edited");
		}
		auditService.create(new Audit(DELETED, ESSENCETYPE.PROFILE, readed.getUuid().toString()), UserMapper.userUI(user));
		profileDao.delete(uuid, dtUpdate);
	}

	@Override
	public Profile findByUser(User user) {
		return profileDao.findByUser(user);
	}

}
