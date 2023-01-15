package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.criteria.CriteriaUpdate;

import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IVerificationDao;
import by.academy.fitness.domain.entity.VerificationToken;
@Repository
public class VerificationDao extends BaseEntityDAO<UUID, VerificationToken> implements IVerificationDao {

	@Override
	protected void updateFields(CriteriaUpdate<VerificationToken> criteria, VerificationToken entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Class<VerificationToken> getClazz() {

		return VerificationToken.class;
	}

}
