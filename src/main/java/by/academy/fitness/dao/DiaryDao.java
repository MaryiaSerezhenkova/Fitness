package by.academy.fitness.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IDiaryDao;
import by.academy.fitness.domain.entity.Diary;
@Repository
public class DiaryDao implements IDiaryDao {

	@PersistenceContext
	private final EntityManager entityManager;
	
	private static final String SELECT_SQL = "SELECT * from app.diary ORDER BY dt_create";
	
	@Autowired

	public DiaryDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Diary create(Diary item) {
		try {
			entityManager.persist(item);
			return item;
		} catch (Exception e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	@Override
	public Diary read(UUID uuid) {
		try {
			Diary diary = entityManager.find(Diary.class, uuid);
			if (diary == null) {
				throw new Exception("Такой записи не существует");
			}
			return diary;
		} catch (Exception e) {
			throw new RuntimeException("При чтении данных произошла ошибка", e);
		}
	}

	@Override
	public List<Diary> get() {
		return entityManager.createNativeQuery(SELECT_SQL, Diary.class).getResultList();
	}

	@Override
	public Diary update(UUID uuid, LocalDateTime dtUpdate, Diary type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub
		
	}
	
	
}
