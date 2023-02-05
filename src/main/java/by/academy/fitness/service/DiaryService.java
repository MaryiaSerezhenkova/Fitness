package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.DiaryDao;
import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.dto.DiaryDTO;
import by.academy.fitness.domain.dto.ProfileDTO;
import by.academy.fitness.domain.dto.UserDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Audit.ESSENCETYPE;
import by.academy.fitness.domain.entity.Diary;
import by.academy.fitness.domain.entity.Dish;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Product;
import by.academy.fitness.domain.entity.Profile;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.mapper.impl.DiaryMapper;
import by.academy.fitness.domain.validators.DiaryValidator;
import by.academy.fitness.service.interf.IDiaryService;

@Service
public class DiaryService implements IDiaryService {
	private final static String CREATED = "Food diary entry created";
	private final static String UPDATED = "Food diary entry updated";
	private final static String DELETED = "Food diary entry deleted";

	private final DiaryDao diaryDao;
	private final DiaryValidator validator;
	private final UserService userService;
	private final AuditService auditService;
	private final ProfileService profileService;
	private final DiaryMapper mapper;

	@Autowired
	public DiaryService(DiaryDao diaryDao, DishService dishService, DiaryValidator validator, UserService userService,
			AuditService auditService, ProfileService profileService, DiaryMapper mapper) {
		super();
		this.diaryDao = diaryDao;
		this.validator = validator;
		this.userService = userService;
		this.auditService = auditService;
		this.profileService = profileService;
		this.mapper = mapper;
	}

	@Transactional
	@Override
	public DiaryDTO create(DiaryDTO dto) {
		UserDTO user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		validator.validate(dto);
		Diary diary = mapper.toEntity(dto);

		ProfileDTO profile = profileService.findByUserId(user.getId());
		diary.setUuid(UUID.randomUUID());

		diary.setDtCreate(LocalDateTime.now());
		diary.setDtUpdate(diary.getDtCreate());
		if (dto.getDishUuid() != null) {
			diary.setDish(new Dish(dto.getDishUuid()));
		}
		if (dto.getProductUuid() != null) {
			diary.setProduct(new Product(dto.getProductUuid()));
		}
		diary.setWeight(dto.getWeight());
		diary.setMealTime(dto.getMealTime());
		diary.setProfile(new Profile(profile.getId()));
		auditService.create(new Audit(CREATED, ESSENCETYPE.DIARY, diary.getUuid().toString()), new User(user.getId()));
		return mapper.toDTO(diaryDao.create(diary));
	}

	@Transactional
	@Override
	public DiaryDTO read(UUID uuid) {
		return mapper.toDTO(diaryDao.findByUuid(uuid));
	}

	@Transactional
	@Override
	public Page<DiaryDTO> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		Page<DiaryDTO> page = new Page<>();
		page.setContent(diaryDao.findAll(amount, skip, sortings, filters).stream().map(mapper::toDTO)
				.collect(Collectors.toList()));
		page.setPageSize(amount);
		int count = diaryDao.count(filters);
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
		if (amount > (count - skip)) {
			numberOfEl = count - skip;
		}
		page.setNumberOfElements(numberOfEl);
		return page;
	}

	@Transactional
	@Override
	public DiaryDTO update(UUID uuid, LocalDateTime dtUpdate, DiaryDTO dto) {
		UserDTO user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		Diary readed = diaryDao.findByUuid(uuid);

		if (readed == null) {
			throw new IllegalArgumentException("Item not found");
		}
		if (!readed.getProfile().getUser().equals(user)) {
			throw new IllegalArgumentException("You can only update the product you created");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("Sorry, this item has already been edited");
		}
		validator.validate(dto);
		readed.setDtUpdate(LocalDateTime.now());
		readed.setWeight(dto.getWeight());
		if (dto.getProductUuid() != null) {
			readed.setProduct(new Product(dto.getProductUuid()));
		}
		if (dto.getDishUuid() != null) {
			readed.setDish(new Dish(dto.getDishUuid()));
		}
		readed.setMealTime(dto.getMealTime());
		auditService.create(new Audit(UPDATED, ESSENCETYPE.DIARY, readed.getUuid().toString()), new User(user.getId()));

		return mapper.toDTO(diaryDao.create(readed));
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		UserDTO user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		auditService.create(new Audit(DELETED, ESSENCETYPE.DIARY, diaryDao.findByUuid(uuid).getUuid().toString()),
				new User(user.getId()));
		diaryDao.delete(uuid, dtUpdate);

	}

}
