package by.academy.fitness.domain.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import by.academy.fitness.domain.dto.ProductDTO;
import by.academy.fitness.exceptions.ValidationException;
@Component
public class ProductValidator implements IValidator<ProductDTO>  {
	public ProductValidator() {
		super();
	}

	@Override
	public void validate(ProductDTO dto)  {
		

		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("Dto is null");
		}
		if (dto.getName() == null || dto.getName().isBlank()) {
			errors.add("Name is not valid");
		}
		if (dto.getWeight()<=0) {
			errors.add("Weight is not valid");
		}
		
		if (dto.getColories()<0 || dto.getFats()<0 || dto.getProteins()<0 || dto.getColories()<=0) {
			errors.add("Parameteres are not valid");
		}

		if (errors.size() > 0) {
			throw new ValidationException(String.join(", ", errors));
		}
	}
}