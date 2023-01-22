package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.academy.fitness.dao.AuditDao;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.service.interf.IAuditService;
@Service
public class AuditService implements IAuditService {
	
	private final AuditDao auditDao;

	public AuditService(AuditDao auditDao) {
		super();
		this.auditDao = auditDao;
	}

	@Override
	public Audit create(Audit type, User user) {
		Audit audit = new Audit();
		audit.setUuid(UUID.randomUUID());
		audit.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
		audit.setUser(user);
		return auditDao.create(audit);
	}

	@Override
	public Page<Audit> get(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Audit read(UUID uuid) {
		return auditDao.findByUuid(uuid);
	}
	
	
	

}
