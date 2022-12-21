package by.academy.fitness.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import by.academy.fitness.dao.interf.IIngredientDao;
import by.academy.fitness.domain.entity.Ingredient;

@Repository
public class IngredientDao implements IIngredientDao {

	@PersistenceContext
	private final EntityManager entityManager;
	// private static final String SELECT_SQL = "SELECT * from app.ingridient ORDER
	// BY dt_create";

	@Autowired
	public IngredientDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Ingredient create(Ingredient item) {
		try {
			entityManager.persist(item);
			return item;
		} catch (Exception e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	@Override
	public Ingredient read(UUID uuid) {
		try {
			Ingredient ingredient = entityManager.find(Ingredient.class, uuid);
			if (ingredient == null) {
				throw new Exception("Такой записи не существует");
			}
			return ingredient;
		} catch (Exception e) {
			throw new RuntimeException("При чтении данных произошла ошибка", e);
		}
	}

	@Override
	public List<Ingredient> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ingredient update(UUID uuid, LocalDateTime dtUpdate, Ingredient type) {
		try {
			Ingredient ingredient = entityManager.find(Ingredient.class, uuid);
			if (ingredient == null) {
				throw new Exception("Такой записи не существует");
			}
			if (!ingredient.getDtUpdate().equals(dtUpdate)) {
				throw new RuntimeException("Запись устарела");
			}
			ingredient.setDtUpdate(type.getDtUpdate());
			ingredient.setProduct(type.getProduct());
			ingredient.setWeight(type.getWeight());
			;
		} catch (Exception e) {
			throw new RuntimeException("При чтении данных произошла ошибка", e);
		}
		return type;
	}

	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		try {
			Ingredient ingredient = entityManager.find(Ingredient.class, uuid);
			if (ingredient == null) {
				throw new Exception("Такой записи не существует");
			}
			if (!ingredient.getDtUpdate().equals(dtUpdate)) {
				throw new RuntimeException("Запись устарела");
			}
			entityManager.remove(ingredient);

			;
		} catch (Exception e) {
			throw new RuntimeException("При чтении данных произошла ошибка", e);
		}
	}

}
