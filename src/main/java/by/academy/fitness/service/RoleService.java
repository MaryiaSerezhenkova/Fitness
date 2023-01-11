package by.academy.fitness.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.fitness.dao.RoleDao;
import by.academy.fitness.dao.interf.IRoleDao;
import by.academy.fitness.domain.entity.Role;
import by.academy.fitness.domain.entity.User.ROLE;

@Service
public class RoleService {
	private final RoleDao roleDao;

	@Autowired
	public RoleService(RoleDao roleDao) {
		super();
		this.roleDao = roleDao;
	}

	/***
	 * пытаемся досьать роль по имени. Если нет, то создаем
	 * 
	 * @param name
	 * @return
	 */
	@Transactional
	public Role findByName(ROLE name) {
		Role role = roleDao.findByName(name);
		if (role == null) {
			role = new Role();
			role.setUuid(UUID.randomUUID());
			role.setName(name);
			roleDao.create(role);
		}
		return role;
	}
}
