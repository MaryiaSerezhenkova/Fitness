package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.criteria.CriteriaUpdate;

import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IDishDao;
import by.academy.fitness.domain.entity.Dish;
@Repository
public class DishDao extends BaseEntityDAO<UUID, Dish> implements IDishDao {


	@Override
	protected void updateFields(CriteriaUpdate<Dish> criteria, Dish entity) {
		criteria.set("dt_update", entity.getDtUpdate());
		criteria.set("name", entity.getName());
		criteria.set("ingridients", entity.getIngredients());
		
	}

	@Override
	protected Class<Dish> getClazz() {
		return Dish.class;
	}
	
	


}
