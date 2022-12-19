package by.academy.fitness.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IProductDao;
import by.academy.fitness.domain.entity.Product;

@Repository
public class ProductDao implements IProductDao {

	@PersistenceContext
	private final EntityManager entityManager;
	private static final String SELECT_SQL = "SELECT * from app.product ORDER BY dt_create";

	@Autowired
	public ProductDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Product create(Product item) {
		try {
			entityManager.persist(item);
			return item;
		} catch (Exception e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}

	}

	@Override
	public Product read(UUID uuid) {
		try {
			Product product = entityManager.find(Product.class, uuid);
			if (product == null) {
				throw new Exception("Такой записи не существует");
			}
			return product;
		} catch (Exception e) {
			throw new RuntimeException("При чтении данных произошла ошибка", e);
		}
	}

	@Override
	public List<Product> get() {
		try {
			List<Product> product = entityManager.createNativeQuery(SELECT_SQL).getResultList();
			if (product == null) {
				throw new Exception("Такой записи не существует");
			}
			return product;
		} catch (Exception e) {
			throw new RuntimeException("При чтении данных произошла ошибка", e);
		}
	}

	@Override
	public Product update(UUID uuid, LocalDateTime dtUpdate, Product type) {
		try {
			Product product = entityManager.find(Product.class, uuid);
			if (product == null) {
				throw new Exception("Такой записи не существует");
			}
			if (!product.getDtUpdate().equals(dtUpdate)) {
				throw new RuntimeException("Запись устарела");
			}
			product.setDtUpdate(type.getDtUpdate());
			product.setName(type.getName());
			product.setWeight(type.getWeight());
			product.setUnit(type.getUnit());
			product.setWeight(type.getWeight());
			product.setColories(type.getColories());
			product.setProteins(type.getProteins());
			product.setFats(type.getFats());
			product.setCarbohydrates(type.getCarbohydrates());
			;
		} catch (Exception e) {
			throw new RuntimeException("При чтении данных произошла ошибка", e);
		}
		return type;
	}

	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		try {
			Product product = entityManager.find(Product.class, uuid);
			if (product == null) {
				throw new Exception("Такой записи не существует");
			}
			if (!product.getDtUpdate().equals(dtUpdate)) {
				throw new RuntimeException("Запись устарела");
			}
			entityManager.remove(product);

			;
		} catch (Exception e) {
			throw new RuntimeException("При чтении данных произошла ошибка", e);
		}
	}

}
