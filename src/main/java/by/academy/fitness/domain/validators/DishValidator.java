package by.academy.fitness.domain.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import by.academy.fitness.domain.dto.DishDTO;
import by.academy.fitness.exceptions.ValidationException;
@Component
public class DishValidator implements IValidator<DishDTO>  {
	public DishValidator() {
		super();
	}

	@Override
	public void validate(DishDTO dto)  {
		

		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("Dto is null");
		}
		if (dto.getName() == null || dto.getName().isBlank()) {
			errors.add("Name is not valid");
		}
		
		if (dto.getIngredients() == null || dto.getIngredients().isEmpty()) {
			errors.add("Ingredients are empty");
		}

		if (errors.size() > 0) {
			throw new ValidationException(String.join(", ", errors));
		}
	}
}
