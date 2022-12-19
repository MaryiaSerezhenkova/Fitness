package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.academy.fitness.dao.ProductDao;
import by.academy.fitness.domain.builders.ProductMapper;
import by.academy.fitness.domain.dto.ProductDTO;
import by.academy.fitness.domain.entity.Product;
import by.academy.fitness.service.interf.IProductService;

@Service
public class ProductService implements IProductService {
	

	private final ProductDao productDao;

	@Autowired
	public ProductService(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}
	@Transactional
	@Override
	public Product create(ProductDTO dto) {
		Product product = ProductMapper.productInputMapping(dto);
		product.setUuid(UUID.randomUUID());
		product.setDtCreate(LocalDateTime.now());
		product.setDtUpdate(product.getDtCreate());
		//product.setUser(null);
		return ProductMapper.productOutputMapping(productDao.create(product));
	}
	@Transactional
	@Override
	public Product read(UUID uuid) {
		return productDao.read(uuid);
	}
	@Transactional
	@Override
	public List<Product> get() {
		return productDao.get();
	}
	@Transactional
	@Override
	public Product update(UUID uuid, LocalDateTime dtUpdate, ProductDTO dto) {
		Product readed = productDao.read(uuid);

		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
		}

		readed.setDtUpdate(LocalDateTime.now());
		readed.setName(dto.getName());
		readed.setWeight(dto.getWeight());
		readed.setUnit(dto.getUnit());
		readed.setColories(dto.getColories());
		readed.setProteins(dto.getProteins());
		readed.setFats(dto.getFats());
		readed.setCarbohydrates(dto.getCarbohydrates());

		return productDao.update(uuid, dtUpdate, readed);
	}
	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		Product readed = productDao.read(uuid);

		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
		}
		productDao.delete(uuid, dtUpdate);
		
	}
	

}
