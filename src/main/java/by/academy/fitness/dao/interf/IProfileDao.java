package by.academy.fitness.dao.interf;

import java.util.UUID;

import by.academy.fitness.domain.entity.Profile;

public interface IProfileDao  extends IDao<Profile>{

	Boolean existsByUserId(UUID userId);

	Profile findByUserId(UUID userId);

}
