package by.academy.fitness.dao.interf;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;

public interface IDao<E> {
    E create(E item);
    E findByUuid(UUID uuid);
    List<E> findAll(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters);
    E update(UUID uuid, LocalDateTime dtUpdate, E type);
    void delete(UUID uuid, LocalDateTime dtUpdate);
}