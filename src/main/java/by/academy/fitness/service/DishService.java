package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.DishDao;
import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.dto.DishDTO;
import by.academy.fitness.domain.dto.IngredientDTO;
import by.academy.fitness.domain.dto.UserDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Audit.ESSENCETYPE;
import by.academy.fitness.domain.entity.Dish;
import by.academy.fitness.domain.entity.Ingredient;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.mapper.impl.DishMapper;
import by.academy.fitness.domain.validators.DishValidator;
import by.academy.fitness.service.interf.IDishService;

@Service
public class DishService implements IDishService {
	private final static String CREATED = "New dish created";
	private final static String UPDATED = "Dish updated";
	private final static String DELETED = "Dish deleted";

	private final DishDao dishDao;
	private final DishValidator validator;
	private final UserService userService;
	private final AuditService auditService;
	private final DishMapper mapper;

	@Autowired
	public DishService(DishDao dishDao, ProductService productService, DishValidator validator, UserService userService,
			AuditService auditService, DishMapper mapper) {
		super();
		this.dishDao = dishDao;
		this.validator = validator;
		this.userService = userService;
		this.auditService = auditService;
		this.mapper = mapper;
	}

	@Transactional
	@Override
	public DishDTO create(DishDTO dto) {
		UserDTO userDto = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Dish dish = mapper.toEntity(dto);
		validator.validate(dto);
		dish.setUuid(UUID.randomUUID());
		dish.setDtCreate(LocalDateTime.now());
		dish.setDtUpdate(dish.getDtCreate());
		List<Ingredient> ingr = new ArrayList<>();

		for (IngredientDTO i : dto.getIngredients()) {
			Ingredient x = new Ingredient();
			x.setUuid(UUID.randomUUID());
			x.setProductId(i.getProduct().getUuid());
			x.setWeight(i.getWeight());
			ingr.add(x);
		}
		dish.setIngredients(ingr);
		dish.setUserId(userDto.getUuid());;
		auditService.create(new Audit(CREATED, ESSENCETYPE.DISH, dish.getName() + " " + dish.getUuid().toString()),
				userDto.getUuid());

		return mapper.toDTO(dishDao.create(dish));
	}

	@Transactional
	public DishDTO read(UUID uuid) {
		return mapper.toDTO(dishDao.findByUuid(uuid));
	}

	@Transactional
	@Override
	public DishDTO update(UUID uuid, LocalDateTime dtUpdate, DishDTO dto) {
		UserDTO userDto = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Dish readed = dishDao.findByUuid(uuid);

		if (readed == null) {
			throw new IllegalArgumentException("Item not found");
		}
		if (!readed.getUserId().equals(userDto.getUuid())) {
			throw new IllegalArgumentException("You can only update the product you created");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("Sorry, this item has already been edited");
		}
		validator.validate(dto);
		readed.setDtUpdate(LocalDateTime.now());
		readed.setName(dto.getName());
		List<Ingredient> ingr = new ArrayList<>();
		for (IngredientDTO i : dto.getIngredients()) {
			Ingredient x = new Ingredient();
			x.setUuid(UUID.randomUUID());
			x.setProductId(i.getProduct().getUuid());
			x.setWeight(i.getWeight());
			ingr.add(x);
		}
		readed.setIngredients(ingr);
		auditService.create(new Audit(UPDATED, ESSENCETYPE.DISH, readed.getName() + " " + readed.getUuid().toString()),
				userDto.getUuid());

		return mapper.toDTO(dishDao.create(readed));
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		UserDTO userDto = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if (!dishDao.findByUuid(uuid).getUser().getUuid().equals(userDto.getUuid())) {
			throw new IllegalArgumentException("You can only delete the product you created");
		}
		auditService.create(new Audit(DELETED, ESSENCETYPE.DISH, uuid.toString()), userDto.getUuid());
		dishDao.delete(uuid, dtUpdate);

	}

	@Transactional
	@Override
	public Page<DishDTO> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		Page<DishDTO> page = new Page<>();
		page.setContent(dishDao.findAll(amount, skip, sortings, filters).stream().map(mapper::toDTO)
				.collect(Collectors.toList()));
		page.setPageSize(amount);
		int count = dishDao.count(filters);
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

}
