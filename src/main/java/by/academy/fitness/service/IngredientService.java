package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.IngredientDao;
import by.academy.fitness.domain.builders.IngredientMapper;
import by.academy.fitness.domain.dto.IngredientDTO;
import by.academy.fitness.domain.entity.Ingredient;
import by.academy.fitness.domain.entity.Product;
import by.academy.fitness.service.interf.IIngredientService;

@Service
public class IngredientService implements IIngredientService {

	private final IngredientDao ingredientDao;
	private final ProductService productService;

	@Autowired
	public IngredientService(IngredientDao ingredientDao, ProductService productService) {
		super();
		this.ingredientDao = ingredientDao;
		this.productService=productService;
	}
	@Transactional
	@Override
	public Ingredient create(IngredientDTO dto) {
		Ingredient ingredient = new Ingredient();
		ingredient.setUuid(UUID.randomUUID());
		Product product = productService.read(dto.getProductUuid());
		ingredient.setProduct(product);
		ingredient.setWeight(dto.getWeight());
		return IngredientMapper.ingredientOutputMapping(ingredientDao.create(ingredient));
	}
	@Transactional
	@Override
	public Ingredient read(UUID uuid) {
	return ingredientDao.read(uuid);
	}
	@Transactional
	@Override
	public List<Ingredient> get() {
		return ingredientDao.get();
	}
	@Transactional
	@Override
	public Ingredient update(UUID uuid, LocalDateTime dtUpdate, IngredientDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub

	}

}
