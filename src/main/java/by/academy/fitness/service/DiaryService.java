package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.academy.fitness.dao.DiaryDao;
import by.academy.fitness.domain.builders.DiaryMapper;
import by.academy.fitness.domain.dto.DiaryDTO;
import by.academy.fitness.domain.entity.Diary;
import by.academy.fitness.service.interf.IDiaryService;

@Service
public class DiaryService implements IDiaryService {

	private final DiaryDao diaryDao;
	private final ProductService productService;
	private final DishService dishService;

	@Autowired
	public DiaryService(DiaryDao diaryDao, ProductService productService, DishService dishService) {
		super();
		this.diaryDao = diaryDao;
		this.productService = productService;
		this.dishService = dishService;
	}

	@Override
	public Diary create(DiaryDTO dto) {
		Diary diary = new Diary();
		diary.setUuid(UUID.randomUUID());
		diary.setDtCreate(LocalDateTime.now());
		diary.setDtUpdate(diary.getDtCreate());
		diary.setDish(dishService.read(dto.getDishUuid()));
		diary.setProduct(productService.read(dto.getProductUuid()));
		diary.setWeight(dto.getWeight());
		diary.setMealTime(dto.getMealTime());
		return DiaryMapper.diaryOutputMapping(diary);
	}

	@Override
	public Diary read(UUID uuid) {
		return diaryDao.read(uuid);
	}

	@Override
	public List<Diary> get() {
		return diaryDao.get();
	}

	@Override
	public Diary update(UUID uuid, LocalDateTime dtUpdate, DiaryDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub

	}

}
