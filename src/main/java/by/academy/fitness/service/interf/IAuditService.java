package by.academy.fitness.service.interf;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.User;

public interface IAuditService {
	
	Audit create(Audit audit, User user);

	Page<Audit> get(Pageable pageable);

	Audit read(UUID uuid);

}
