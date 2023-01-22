package by.academy.fitness.dao.interf;

import java.util.UUID;

import by.academy.fitness.domain.entity.Audit;

public interface IAuditDao {
	Audit findByUuid(UUID uuid);

}
