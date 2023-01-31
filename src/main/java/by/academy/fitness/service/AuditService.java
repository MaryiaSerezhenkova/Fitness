package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.academy.fitness.dao.AuditDao;
import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.builders.AuditMapper;
import by.academy.fitness.domain.builders.UserMapper;
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
		audit.setUser(UserMapper.userUI(user));
		audit.setType(type.getType());
		audit.setText(type.getText());
		audit.setId(type.getId());
		return auditDao.create(audit);
	}

	@Override
	public Page<Audit> get(Pageable pageable) {
		Page<Audit> items = (Page<Audit>) auditDao.getPage(pageable);
		return AuditMapper.pageMapper(items);
	}

	@Override
	public Audit read(UUID uuid) {
		return auditDao.findByUuid(uuid);
	}

	@Override
	public Page<Audit> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		Page<Audit> page = new Page<>();
		page.setContent(auditDao.findAll(amount, skip, sortings, filters));
		page.setPageSize(amount);
		int count = auditDao.count(filters);
		page.setTotalElements(count);
		int pageSize = count / (amount.intValue() == 0 ? 1 : amount.intValue());
		if (count % (amount.intValue() == 0 ? 1 : amount.intValue()) > 0) {
			pageSize++;
		}
		int currentPage = skip.intValue() / amount.intValue();
		page.setPageNumber(currentPage);
		page.setTotalPages(pageSize);
		page.setFirst(skip == 0);
		page.setLast((count - skip) <= amount);
		;
		int numberOfEl = amount;
		if (amount > (count - skip)) {
			numberOfEl = count - skip;
		}
		page.setNumberOfElements(numberOfEl);
		return page;
	}
	
	
	

}
