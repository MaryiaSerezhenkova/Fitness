package by.academy.fitness.dao.interf;

import java.util.UUID;

import by.academy.fitness.domain.entity.VerificationToken;

public interface IVerificationDao extends IDao<VerificationToken>  {
	
	VerificationToken findByUser(UUID userId);
	Boolean existsByUser(UUID userId);

}
