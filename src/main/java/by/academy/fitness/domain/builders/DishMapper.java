package by.academy.fitness.domain.builders;

import by.academy.fitness.domain.dto.DishDTO;
import by.academy.fitness.domain.entity.Dish;

public class DishMapper {

	public DishMapper() {
		super();
	}

	public static Dish dishInputMapping(DishDTO dto) {
		return new Dish(dto.getName(), dto.getIngredients());
	}

	public static Dish dishOutputMapping(Dish dish) {
		return new Dish(dish.getUuid(), dish.getDtCreate(), dish.getDtUpdate(), dish.getName(), dish.getIngredients());
	}

}
