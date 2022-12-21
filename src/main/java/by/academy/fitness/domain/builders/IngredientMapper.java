package by.academy.fitness.domain.builders;

import by.academy.fitness.domain.dto.IngredientDTO;
import by.academy.fitness.domain.entity.Ingredient;

public class IngredientMapper {

	public IngredientMapper() {
		super();
	}
//	public static Ingridient ingridientInputMapping(IngridientDTO dto) {
//		return new Ingridient(dto.getProductUuid(), dto.getWeight());
//	}

	public static Ingredient ingredientOutputMapping(Ingredient ingredient) {
		return new Ingredient(ingredient.getUuid(), ingredient.getDtCreate(), ingredient.getDtUpdate(), 
				ingredient.getProduct(), ingredient.getWeight());
	}
	

}
