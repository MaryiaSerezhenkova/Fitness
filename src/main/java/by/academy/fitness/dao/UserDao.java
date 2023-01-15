package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IUserDao;
import by.academy.fitness.domain.entity.User;

@Repository
public class UserDao extends BaseEntityDAO<UUID, User> implements IUserDao {

	@Override
	protected void updateFields(CriteriaUpdate<User> criteria, User entity) {
		criteria.set("dt_update", entity.getDtUpdate());
		criteria.set("username", entity.getUsername());
		criteria.set("password", entity.getPassword());
		criteria.set("email", entity.getEmail());

	}

	@Override
	protected Class<User> getClazz() {

		return User.class;
	}

	@Override
	public User findByUsername(String username) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(getClazz());
		Root<User> root = criteria.from(getClazz());
		criteria.select(root);

		criteria.where(builder.equal(root.get("username"), username));
		return getEntityManager().createQuery(criteria).getResultList().stream().findFirst().orElse(null);

	}
	public User findByEmail(String email) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(getClazz());
		Root<User> root = criteria.from(getClazz());
		criteria.select(root);

		criteria.where(builder.equal(root.get("email"), email));
		return getEntityManager().createQuery(criteria).getResultList().stream().findFirst().orElse(null);

	}


	@Override
	public Boolean existsByUsername(String username) {
		User user = findByUsername(username);
		if (user == null) {
			return false;

		}
		return true;
	}

	@Override
	public Boolean existsByEmail(String email) {
		User user = findByEmail(email);
		if (user == null) {
			return false;

		}
		return true;
	}

}
