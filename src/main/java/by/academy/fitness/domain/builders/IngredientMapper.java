package by.academy.fitness.domain.builders;

import by.academy.fitness.domain.entity.Ingredient;

public class IngredientMapper {

	public IngredientMapper() {
		super();
	}

	public static Ingredient ingredientOutputMapping(Ingredient ingredient) {
		return new Ingredient(ingredient.getUuid(), ingredient.getProduct(), ingredient.getWeight());
	}

}
