package by.academy.fitness.domain.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import by.academy.fitness.domain.dto.DiaryDTO;
import by.academy.fitness.exceptions.ValidationException;
@Component
public class DiaryValidator implements IValidator<DiaryDTO>  {
	public DiaryValidator() {
		super();
	}

	@Override
	public void validate(DiaryDTO dto)  {
		

		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("Dto is null");
		}
		if (dto.getWeight()<=0) {
			errors.add("Weight is not valid");
		}
		if (dto.getDishUuid()==null & dto.getProductUuid()==null) {
			errors.add("Both parametres are empty");
		}
		
		if (errors.size() > 0) {
			throw new ValidationException(String.join(", ", errors));
		}
		
	}
}