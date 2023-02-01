package by.academy.fitness.service.interf;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.User;

public interface IAuditService {
	
	Audit create(Audit audit, User user);

	Audit read(UUID uuid);
	
	Page<Audit> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters);

}
