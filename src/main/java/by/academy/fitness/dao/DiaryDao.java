package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IDiaryDao;
import by.academy.fitness.domain.entity.Diary;
@Repository
public class DiaryDao extends BaseEntityDAO<UUID, Diary>  implements IDiaryDao {

	@PersistenceContext
	private final EntityManager entityManager;
	
	//private static final String SELECT_SQL = "SELECT * from app.diary ORDER BY dt_create";
	
	@Autowired

	public DiaryDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	protected void updateFields(CriteriaUpdate<Diary> criteria, Diary entity) {
		criteria.set("dt_update", entity.getDtUpdate());
		criteria.set("product_uuid", entity.getProduct());
		criteria.set("dish_uuid", entity.getDish());
		criteria.set("weight", entity.getWeight());
		criteria.set("meal_time", entity.getMealTime());
	}

	@Override
	protected Class<Diary> getClazz() {
		return Diary.class;
	}

	
	
}
