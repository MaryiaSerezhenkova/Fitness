package by.academy.fitness.domain.builders;

import by.academy.fitness.domain.dto.ProductDTO;
import by.academy.fitness.domain.entity.Product;

public class ProductMapper {
	public ProductMapper() {
	}

	public static Product productInputMapping(ProductDTO dto) {
		return new Product(dto.getName(), dto.getWeight(), dto.getUnit(), dto.getColories(), dto.getProteins(), dto.getFats(), dto.getCarbohydrates());
	}

	public static Product productOutputMapping(Product product) {
		return new Product(product.getUuid(), product.getDtCreate(), product.getDtUpdate(), product.getName(),
				product.getWeight(), product.getUnit(), product.getColories(), product.getProteins(), product.getFats(), product.getCarbohydrates(), product.getUser());
	}
}
