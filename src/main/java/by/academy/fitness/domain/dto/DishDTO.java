package by.academy.fitness.domain.dto;

import java.util.List;

public class DishDTO {

	private String name;

	private List<IngredientDTO> ingredients;

	public DishDTO() {
		super();
	}

	public DishDTO(String name, List<IngredientDTO> ingredients) {
		super();
		this.name = name;
		this.ingredients = ingredients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IngredientDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}

	

}
