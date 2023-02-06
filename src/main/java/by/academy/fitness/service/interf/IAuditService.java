package by.academy.fitness.service.interf;

import java.util.List;
import java.util.UUID;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.dto.AuditDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Page;

public interface IAuditService {

	AuditDTO create(Audit type, UUID userId);

	AuditDTO read(UUID uuid);

	Page<AuditDTO> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters);

}
