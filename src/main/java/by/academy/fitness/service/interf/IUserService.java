package by.academy.fitness.service.interf;

import by.academy.fitness.domain.dto.UserDTO;
import by.academy.fitness.domain.entity.User;

public interface IUserService extends IService<User, UserDTO> {


	UserDTO findByEmail(String email);

	UserDTO findByUsername(String username);

}
