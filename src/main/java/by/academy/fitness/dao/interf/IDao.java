package by.academy.fitness.dao.interf;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IDao<TYPE> {
    TYPE create(TYPE item);
    TYPE read(UUID uuid);
    List<TYPE> get();
    TYPE update(UUID uuid, LocalDateTime dtUpdate, TYPE type);
    void delete(UUID uuid, LocalDateTime dtUpdate);
}