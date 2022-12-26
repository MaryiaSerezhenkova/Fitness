package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.DishDao;
import by.academy.fitness.domain.dto.DishDTO;
import by.academy.fitness.domain.dto.IngredientDTO;
import by.academy.fitness.domain.entity.Dish;
import by.academy.fitness.domain.entity.Ingredient;
import by.academy.fitness.service.interf.IDishService;

@Service
public class DishService implements IDishService {

	private final DishDao dishDao;
	private final IngredientService ingrService;
	private final ProductService productService;

	@Autowired
	public DishService(DishDao dishDao, IngredientService ingrService, ProductService productService) {
		super();
		this.dishDao = dishDao;
		this.ingrService = ingrService;
		this.productService=productService;
	}

	@Transactional
	@Override
	public Dish create(DishDTO dto) {
		Dish dish = new Dish();
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
	@Override
	public Dish read(UUID uuid) {
		return dishDao.read(uuid);
	}

	@Transactional
	@Override
	public List<Dish> get() {
		return dishDao.get();
	}

	@Transactional
	@Override
	public Dish update(UUID uuid, LocalDateTime dtUpdate, DishDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub

	}

}
