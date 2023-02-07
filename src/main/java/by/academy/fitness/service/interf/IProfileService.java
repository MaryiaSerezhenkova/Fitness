package by.academy.fitness.service.interf;

import java.util.UUID;

import by.academy.fitness.domain.dto.ProfileDTO;
import by.academy.fitness.domain.entity.Profile;

public interface IProfileService extends IService<Profile, ProfileDTO> {


	ProfileDTO findByUserId(UUID id);
}
