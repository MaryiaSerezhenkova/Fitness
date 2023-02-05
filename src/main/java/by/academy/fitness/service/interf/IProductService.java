package by.academy.fitness.service.interf;

import java.util.UUID;

import by.academy.fitness.domain.dto.ProductDTO;
import by.academy.fitness.domain.entity.Product;

public interface IProductService extends IService<Product, ProductDTO> {
	
	Product readAll(UUID uuid);

}
