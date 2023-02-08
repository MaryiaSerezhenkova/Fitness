package by.academy.fitness.domain.mapper.impl;

import org.springframework.stereotype.Component;

import by.academy.fitness.domain.dto.DiaryDTO;
import by.academy.fitness.domain.entity.Diary;

@Component
public class DiaryMapper extends BaseMapper<Diary, DiaryDTO> {
	public Diary toEntity (DiaryDTO dto) {
		Diary diary=super.toEntity(dto);
		diary.setDish(null);
		diary.setProduct(null);
		if (dto.getDish() != null) {
			diary.setDishId(dto.getDish().getUuid());
		}
		if (dto.getProduct() != null) {
			diary.setProductId(dto.getProduct().getUuid());
		}
		return diary;
	}

}
