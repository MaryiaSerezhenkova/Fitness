package by.academy.fitness.service.interf;

import java.time.LocalDateTime;
import java.util.UUID;

import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.entity.User.ROLE;
import by.academy.fitness.domain.entity.User.USERSTATUS;

public interface IUserService extends IService<User, UserRegistrationDTO> {
	
	 User updateRole(UUID uuid, ROLE role, LocalDateTime dtUpdate);

	 User updateStatus(UUID uuid, USERSTATUS status, LocalDateTime dtUpdate);

}
