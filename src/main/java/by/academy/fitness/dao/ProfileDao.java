package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IProfileDao;
import by.academy.fitness.domain.entity.Profile;
import by.academy.fitness.domain.entity.User;

@Repository
public class ProfileDao extends BaseEntityDAO<UUID, Profile> implements IProfileDao {

	@Override
	protected void updateFields(CriteriaUpdate<Profile> criteria, Profile entity) {
		criteria.set("dt_update", entity.getDtUpdate());
		criteria.set("dt_birthday", entity.getDtBirthday());
		criteria.set("weight", entity.getWeight());
		criteria.set("height", entity.getHeight());
		criteria.set("target", entity.getTarget());
		criteria.set("type", entity.getType());
		criteria.set("gender", entity.getGender());

	}

	@Override
	protected Class<Profile> getClazz() {
		return Profile.class;
	}

	@Override
	public Profile findByUser(User user) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Profile> criteria = builder.createQuery(getClazz());
		Root<Profile> root = criteria.from(getClazz());
		criteria.select(root);

		criteria.where(builder.equal(root.get("user_uuid"), user.getUuid()));
		return getEntityManager().createQuery(criteria).getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public Boolean existsByUser(User user) {
		return findByUser(user) != null;
	}

	public Profile findByUserId(UUID id) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Profile> criteria = builder.createQuery(getClazz());
		Root<Profile> root = criteria.from(getClazz());
		criteria.select(root);

		criteria.where(builder.equal(root.get("user_uuid"), id));
		return getEntityManager().createQuery(criteria).getResultList().stream().findFirst().orElse(null);
	}

}
