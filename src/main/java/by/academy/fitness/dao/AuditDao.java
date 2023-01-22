package by.academy.fitness.dao;

import java.util.UUID;

import javax.persistence.criteria.CriteriaUpdate;

import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IAuditDao;
import by.academy.fitness.domain.entity.Audit;
@Repository
public class AuditDao extends BaseEntityDAO<UUID, Audit>  implements IAuditDao {

	@Override
	protected void updateFields(CriteriaUpdate<Audit> criteria, Audit entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Class<Audit> getClazz() {
		return Audit.class;
	}

}
