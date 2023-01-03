package by.academy.fitness.service.interf;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.entity.Page;

public interface IService<TYPE, DTO> {
    TYPE create(DTO dto);
    TYPE read(UUID uuid);
    Page<TYPE> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters);
    TYPE update(UUID uuid, LocalDateTime dtUpdate, DTO dto);
    void delete(UUID uuid, LocalDateTime dtUpdate);
}
