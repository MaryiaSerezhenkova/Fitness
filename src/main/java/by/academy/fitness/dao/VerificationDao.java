package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IVerificationDao;
import by.academy.fitness.domain.entity.VerificationToken;

@Repository
public class VerificationDao extends BaseEntityDAO<UUID, VerificationToken> implements IVerificationDao {

	@Override
	protected void updateFields(CriteriaUpdate<VerificationToken> criteria, VerificationToken entity) {

	}

	@Override
	protected Class<VerificationToken> getClazz() {

		return VerificationToken.class;
	}

	public VerificationToken findByUser(UUID userId) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<VerificationToken> criteria = builder.createQuery(getClazz());
		Root<VerificationToken> root = criteria.from(getClazz());
		criteria.select(root);

		criteria.where(builder.equal(root.get("user"), userId));
		return getEntityManager().createQuery(criteria).getResultList().stream().findFirst().orElse(null);

	}

	@Override
	public Boolean existsByUser(UUID userId) {

		return findByUser(userId) != null;
	}

}
