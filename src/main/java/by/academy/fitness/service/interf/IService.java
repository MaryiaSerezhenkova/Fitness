package by.academy.fitness.service.interf;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IService<TYPE, DTO> {
    TYPE create(DTO dto);
    TYPE read(UUID uuid);
    List<TYPE> get();
    TYPE update(UUID uuid, LocalDateTime dtUpdate, DTO dto);
    void delete(UUID uuid, LocalDateTime dtUpdate);
}
