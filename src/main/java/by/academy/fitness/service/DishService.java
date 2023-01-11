package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.DishDao;
import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.dto.DishDTO;
import by.academy.fitness.domain.dto.IngredientDTO;
import by.academy.fitness.domain.entity.Dish;
import by.academy.fitness.domain.entity.Ingredient;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.validators.DishValidator;
import by.academy.fitness.service.interf.IDishService;

@Service
public class DishService implements IDishService {

	private final DishDao dishDao;
	private final ProductService productService;
	private final DishValidator validator;

	@Autowired
	public DishService(DishDao dishDao, ProductService productService, DishValidator validator) {
		super();
		this.dishDao = dishDao;
		this.productService = productService;
		this.validator=validator;
	}

	@Transactional
	@Override
	public Dish create(DishDTO dto) {
		Dish dish = new Dish();
		validator.validate(dto);
		dish.setUuid(UUID.randomUUID());
		dish.setDtCreate(LocalDateTime.now());
		dish.setDtUpdate(dish.getDtCreate());
		dish.setName(dto.getName());
		List<Ingredient> ingr = new ArrayList<>();

		for (IngredientDTO i : dto.getIngredients()) {
			Ingredient x = new Ingredient();
			x.setUuid(UUID.randomUUID());
			x.setProduct(productService.read(i.getProductUuid()));
			x.setWeight(i.getWeight());
			ingr.add(x);
		}
		dish.setIngredients(ingr);

		return dishDao.create(dish);
	}

	@Transactional
	public Dish read(UUID uuid) {
		return dishDao.findByUuid(uuid);
	}

	@Transactional
	@Override
	public Dish update(UUID uuid, LocalDateTime dtUpdate, DishDTO dto) {
		Dish readed = dishDao.findByUuid(uuid);

		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

//		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
//			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
//		}
		validator.validate(dto);
		readed.setDtUpdate(LocalDateTime.now());
		readed.setName(dto.getName());
		List<Ingredient> ingr = new ArrayList<>();
		for (IngredientDTO i : dto.getIngredients()) {
			Ingredient x = new Ingredient();
			x.setUuid(UUID.randomUUID());
			x.setProduct(productService.read(i.getProductUuid()));
			x.setWeight(i.getWeight());
			ingr.add(x);
		}
		readed.setIngredients(ingr);

		return dishDao.create(readed);
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		dishDao.delete(uuid, dtUpdate);

	}

	@Transactional
	@Override
	public Page<Dish> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
			Page<Dish> page = new Page<>();
			page.setContent(dishDao.findAll(amount, skip, sortings, filters));
			page.setPageSize(amount);
			int count = dishDao.count(filters);
			page.setTotalElements(count);
			int pageSize = count / (amount.intValue() == 0 ? 1 : amount.intValue());
			if (count % (amount.intValue() == 0 ? 1 : amount.intValue()) > 0) {
				pageSize++;
			}
			int currentPage = skip.intValue()/amount.intValue();
			page.setPageNumber(currentPage);
			page.setTotalPages(pageSize);
			page.setFirst(skip == 0);
			page.setLast((count-skip)<=amount);;
			int numberOfEl = amount;
			if (amount>(count-skip)) {
				numberOfEl=count-skip;
			}
			page.setNumberOfElements(numberOfEl);
			return page;
	}

}
