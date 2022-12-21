package by.academy.fitness.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IDishDao;
import by.academy.fitness.domain.entity.Dish;
import by.academy.fitness.domain.entity.Product;
@Repository
public class DishDao implements IDishDao {

	@PersistenceContext
	private final EntityManager entityManager;
	
	private static final String SELECT_SQL = "SELECT * from app.dish ORDER BY dt_create";
	
	@Autowired
	public DishDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Dish create(Dish item) {
		try {
			entityManager.persist(item);
			return item;
		} catch (Exception e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	@Override
	public Dish read(UUID uuid) {
		try {
			Dish dish = entityManager.find(Dish.class, uuid);
			if (dish == null) {
				throw new Exception("Такой записи не существует");
			}
			return dish;
		} catch (Exception e) {
			throw new RuntimeException("При чтении данных произошла ошибка", e);
		}
	}

	@Override
	public List<Dish> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dish update(UUID uuid, LocalDateTime dtUpdate, Dish type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub
		
	}
	
	


}
