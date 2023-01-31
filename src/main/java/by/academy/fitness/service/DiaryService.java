package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.DiaryDao;
import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.builders.UserMapper;
import by.academy.fitness.domain.dto.DiaryDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Audit.ESSENCETYPE;
import by.academy.fitness.domain.entity.Diary;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Profile;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.validators.DiaryValidator;
import by.academy.fitness.service.interf.IDiaryService;

@Service
public class DiaryService implements IDiaryService {
	private final static String CREATED = "Food diary entry created";
	private final static String UPDATED = "Food diary entry updated";
	private final static String DELETED = "Food diary entry deleted";

	private final DiaryDao diaryDao;
	private final ProductService productService;
	private final DishService dishService;
	private final DiaryValidator validator;
	private final UserService userService;
	private final AuditService auditService;
	private final ProfileService profileService;

	@Autowired
	public DiaryService(DiaryDao diaryDao, ProductService productService, DishService dishService,
			DiaryValidator validator, UserService userService, AuditService auditService,
			ProfileService profileService) {
		super();
		this.diaryDao = diaryDao;
		this.productService = productService;
		this.dishService = dishService;
		this.validator = validator;
		this.userService = userService;
		this.auditService = auditService;
		this.profileService = profileService;
	}

	@Transactional
	@Override
	public Diary create(DiaryDTO dto) {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		Diary diary = new Diary();
		validator.validate(dto);
		diary.setUuid(UUID.randomUUID());
		diary.setDtCreate(LocalDateTime.now());
		diary.setDtUpdate(diary.getDtCreate());
		if (dto.getDishUuid() != null) {
			diary.setDish(dishService.read(dto.getDishUuid()));
		}
		if (dto.getProductUuid() != null) {
			diary.setProduct(productService.read(dto.getProductUuid()));
		}
		diary.setWeight(dto.getWeight());
		diary.setMealTime(dto.getMealTime());
		diary.setProfile(profileService.findByUser(user));
		auditService.create(new Audit(CREATED, ESSENCETYPE.DIARY, diary.getUuid().toString()), UserMapper.userUI(user));
		return diaryDao.create(diary);
	}

	@Transactional
	@Override
	public Diary read(UUID uuid) {
		return diaryDao.findByUuid(uuid);
	}

	@Transactional
	@Override
	public Page<Diary> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		Page<Diary> page = new Page<>();
		page.setContent(diaryDao.findAll(amount, skip, sortings, filters));
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
	public Diary update(UUID uuid, LocalDateTime dtUpdate, DiaryDTO dto) {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
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
			readed.setProduct(productService.read(dto.getProductUuid()));
		}
		if (dto.getDishUuid() != null) {
			readed.setDish(dishService.read(dto.getDishUuid()));
		}
		readed.setMealTime(dto.getMealTime());
		auditService.create(new Audit(UPDATED, ESSENCETYPE.DIARY, readed.getUuid().toString()), UserMapper.userUI(user));

		return diaryDao.create(readed);
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		auditService.create(new Audit(DELETED, ESSENCETYPE.DIARY, diaryDao.findByUuid(uuid).getUuid().toString()),
				UserMapper.userUI(user));
		diaryDao.delete(uuid, dtUpdate);

	}

}
