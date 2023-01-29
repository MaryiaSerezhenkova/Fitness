package by.academy.fitness.service.interf;

import org.springframework.data.domain.Pageable;

import by.academy.fitness.domain.dto.ProductDTO;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Product;

public interface IProductService extends IService<Product, ProductDTO> {

	Page<Product> get(Pageable pageable);

}
