package by.academy.fitness.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import by.academy.fitness.dao.AuditDao;
import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.dto.AuditDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.mapper.impl.AuditMapper;
import by.academy.fitness.service.interf.IAuditService;

@Service
public class AuditService implements IAuditService {

	private final AuditDao auditDao;
	private final AuditMapper mapper;

	public AuditService(AuditDao auditDao, AuditMapper mapper) {
		super();
		this.auditDao = auditDao;
		this.mapper = mapper;
	}

	@Override
	public AuditDTO create(Audit type, UUID userId) {
		Audit audit = new Audit();
		audit.setUuid(UUID.randomUUID());
		audit.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
		audit.setUserId(userId);
		audit.setType(type.getType());
		audit.setText(type.getText());
		audit.setId(type.getId());
		return mapper.toDTO(auditDao.create(audit));
	}

	@Override
	public AuditDTO read(UUID uuid) {
		return mapper.toDTO(auditDao.findByUuid(uuid));
	}

	@Override
	public Page<AuditDTO> get(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		Page<AuditDTO> page = new Page<>();
		List<AuditDTO> list = auditDao.findAll(amount, skip, sortings, filters).stream().map(mapper::toDTO)
				.collect(Collectors.toList());

		// page.setContent(auditDao.findAll(amount, skip, sortings, filters));
		page.setContent(list);
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
