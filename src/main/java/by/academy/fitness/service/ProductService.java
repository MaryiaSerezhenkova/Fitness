package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.ProductDao;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.builders.ProductMapper;
import by.academy.fitness.domain.builders.UserMapper;
import by.academy.fitness.domain.dto.ProductDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Audit.ESSENCETYPE;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Product;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.validators.ProductValidator;
import by.academy.fitness.service.interf.IProductService;

@Service
public class ProductService implements IProductService {

	private final static String CREATED = "Product created";
	private final static String UPDATED = "Product updated";
	private final static String DELETED = "Product deleted";

	private final ProductDao productDao;
	private final ProductValidator validator;
	private final UserService userService;
	private final AuditService auditService;

	@Autowired
	public ProductService(ProductDao productDao, ProductValidator validator, UserService userService,
			AuditService auditService) {
		super();
		this.productDao = productDao;
		this.validator = validator;
		this.userService = userService;
		this.auditService = auditService;
	}

	@Transactional
	@Override
	public Product create(ProductDTO dto) {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		validator.validate(dto);
		Product product = ProductMapper.productInputMapping(dto);
		product.setUuid(UUID.randomUUID());
		product.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
		product.setDtUpdate(product.getDtCreate());
		product.setUser(user);
		auditService.create(new Audit(CREATED, ESSENCETYPE.PRODUCT, product.getName() + " " + product.getUuid()), UserMapper.userUI(user));
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
		int currentPage = skip.intValue() / amount.intValue();
		page.setPageNumber(currentPage);
		page.setTotalPages(pageSize);
		page.setFirst(skip == 0);
		page.setLast((count - skip) <= amount);
		;
		int numberOfEl = amount;
		if (amount > (count - skip)) {
			numberOfEl = count - skip;
		}
		page.setNumberOfElements(numberOfEl);
		return page;

	}

	@Transactional
	@Override
	public Product update(UUID uuid, LocalDateTime dtUpdate, ProductDTO dto) {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		Product readed = productDao.findByUuid(uuid);

		if (readed == null) {
			throw new IllegalArgumentException("Item not found");
		}
		if (!readed.getUser().equals(user)) {
			throw new IllegalArgumentException("You can only update the product you created");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("Sorry, this item has already been edited");
		}
		validator.validate(dto);
		readed.setDtUpdate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
		readed.setName(dto.getName());
		readed.setWeight(dto.getWeight());
		readed.setUnit(dto.getUnit());
		readed.setColories(dto.getColories());
		readed.setProteins(dto.getProteins());
		readed.setFats(dto.getFats());
		readed.setCarbohydrates(dto.getCarbohydrates());
		readed.setUser(UserMapper.userUI(user));
		auditService.create(new Audit(UPDATED, ESSENCETYPE.PRODUCT, readed.getName()), UserMapper.userUI(user));
		return productDao.create(readed);
	}

	@Transactional
	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		Product readed = productDao.findByUuid(uuid);
		if (!readed.getUser().equals(user)) {
			throw new IllegalArgumentException("You can only delete the product you created");
		}
		if (readed == null) {
			throw new IllegalArgumentException("Item not found");
		}
		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("Sorry, this item has already been edited");
		}

		auditService.create(new Audit(DELETED, ESSENCETYPE.PRODUCT, uuid.toString()), UserMapper.userUI(user));
		productDao.delete(uuid, dtUpdate);

	}
	@Override
	public Page<Product> get(Pageable pageable) {
		Page<Product> items = (Page<Product>) productDao.getPage(pageable);
		return items;
	}
}
