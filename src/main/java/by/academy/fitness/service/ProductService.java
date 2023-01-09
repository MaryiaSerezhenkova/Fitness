package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.ProductDao;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.builders.ProductMapper;
import by.academy.fitness.domain.dto.ProductDTO;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Product;
import by.academy.fitness.domain.validators.ProductValidator;
import by.academy.fitness.service.interf.IProductService;

@Service
public class ProductService implements IProductService {

	private final ProductDao productDao;
	private final ProductValidator validator;

	@Autowired
	public ProductService(ProductDao productDao, ProductValidator validator) {
		super();
		this.productDao = productDao;
		this.validator = validator;
	}

	@Transactional
	@Override
	public Product create(ProductDTO dto) {
		validator.validate(dto);
		Product product = ProductMapper.productInputMapping(dto);
		product.setUuid(UUID.randomUUID());
		product.setDtCreate(LocalDateTime.now());
		product.setDtUpdate(product.getDtCreate());
		return ProductMapper.productOutputMapping(productDao.create(product));
	}

	@Transactional
	@Override
	public Product read(UUID uuid) {
		return productDao.findByUuid(uuid);
	}

	@Transactional
	@Override
	public Page<Product> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		Page<Product> page = new Page<>();
		page.setContent(productDao.findAll(amount, skip, sortings, filters));
		page.setPageSize(amount);
		int count = productDao.count(filters);
		page.setTotalElements(count);
		int pageSize = count / (amount.intValue() == 0 ? 1 : amount.intValue());
		if (count % (amount.intValue() == 0 ? 1 : amount.intValue()) > 0) {
			pageSize++;
		}
		int currentPage = skip.intValue()/amount.intValue();
		page.setPageNumber(currentPage);
		page.setTotalPages(pageSize);
		page.setFirst(skip == 0);
		page.setLast(currentPage==pageSize);
		return page;

//		private int pageNumber;
//		private int pageSize;
//		private int totalPages;
//		private int totalElements;
//		private boolean first;
//		private int numberOfElements;
//		private boolean last;
//		private List<E> content;
	}

	@Transactional
	@Override
	public Product update(UUID uuid, LocalDateTime dtUpdate, ProductDTO dto) {
		Product readed = productDao.findByUuid(uuid);

		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

//		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
//			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
//		}
        validator.validate(dto);
		readed.setDtUpdate(LocalDateTime.now());
		readed.setName(dto.getName());
		readed.setWeight(dto.getWeight());
		readed.setUnit(dto.getUnit());
		readed.setColories(dto.getColories());
		readed.setProteins(dto.getProteins());
		readed.setFats(dto.getFats());
		readed.setCarbohydrates(dto.getCarbohydrates());

		return productDao.create(readed);
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		productDao.delete(uuid, dtUpdate);

	}

}
