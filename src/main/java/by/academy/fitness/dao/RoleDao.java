package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IRoleDao;
import by.academy.fitness.domain.entity.Role;
import by.academy.fitness.domain.entity.User.ROLE;

@Repository
public class RoleDao extends BaseEntityDAO<UUID, Role> implements IRoleDao {

	@Override
	protected void updateFields(CriteriaUpdate<Role> criteria, Role entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Class<Role> getClazz() {
		return Role.class;
	}
	@Override
	public Role findByName(ROLE name) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(getClazz());
		Root<Role> root = criteria.from(getClazz());
		criteria.select(root);

		criteria.where(builder.equal(root.get("name"), name));
		return getEntityManager().createQuery(criteria).getResultList().stream().findFirst().orElse(null);

	}

}
