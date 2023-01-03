package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IProductDao;
import by.academy.fitness.domain.entity.Product;

@Repository
public class ProductDao extends BaseEntityDAO<UUID, Product> implements IProductDao {

	@PersistenceContext
	private final EntityManager entityManager;
	//private static final String SELECT_SQL = "SELECT * from app.product ORDER BY dt_create";

	@Autowired
	public ProductDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	protected Class<Product> getClazz() {
		return Product.class;
	}

	@Override
	protected void updateFields(CriteriaUpdate<Product> criteria, Product entity) {
		criteria.set("dt_update", entity.getDtUpdate());
		criteria.set("name", entity.getName());
		criteria.set("weight", entity.getWeight());
		criteria.set("unit", entity.getUnit());
		criteria.set("colories", entity.getColories());
		criteria.set("proteins", entity.getProteins());
		criteria.set("fats", entity.getFats());
		criteria.set("carbohydrates", entity.getCarbohydrates());
	}

}
