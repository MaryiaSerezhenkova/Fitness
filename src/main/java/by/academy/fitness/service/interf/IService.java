package by.academy.fitness.service.interf;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.entity.Page;

public interface IService<TYPE, DTO> {

	DTO create(DTO dto);

	DTO read(UUID uuid);

	Page<DTO> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters);

	DTO update(UUID uuid, LocalDateTime dtUpdate, DTO dto);

	void delete(UUID uuid, LocalDateTime dtUpdate);
}
