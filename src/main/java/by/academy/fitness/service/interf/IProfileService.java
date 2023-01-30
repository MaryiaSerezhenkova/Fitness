package by.academy.fitness.service.interf;

import by.academy.fitness.domain.dto.ProfileDTO;
import by.academy.fitness.domain.entity.Profile;
import by.academy.fitness.domain.entity.User;

public interface IProfileService extends IService<Profile, ProfileDTO> {

	Profile findByUser (User user);
}
