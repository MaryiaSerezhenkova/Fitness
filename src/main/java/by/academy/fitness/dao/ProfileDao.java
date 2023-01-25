package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.criteria.CriteriaUpdate;

import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IProfileDao;
import by.academy.fitness.domain.entity.Profile;

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

}
