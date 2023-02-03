package by.academy.fitness.domain.builders;

import java.util.ArrayList;
import java.util.List;

import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Page;

public class AuditMapper {

	public static Page<Audit> pageMapper(Page<Audit> items) {

		Page<Audit> dto = new Page<>();
		dto.setPageNumber(items.getPageNumber());
		dto.setPageSize(items.getPageSize());
		dto.setTotalPages(items.getTotalPages());
		dto.setTotalElements((int) items.getTotalElements());
		dto.setFirst(items.isFirst());
		dto.setNumberOfElements(items.getNumberOfElements());
		dto.setLast(items.isLast());

		List<Audit> report = new ArrayList<>();

		for (Audit audit : items.getContent()) {

			report.add(audit);
		}

		dto.setContent(report);

		return dto;
	}
	public static Audit auditUI (Audit audit) {
		return new Audit (audit.getUuid(), audit.getDtCreate(), UserMapper.userUI(audit.getUser()),  audit.getText(), audit.getType(), audit.getId());
		
	}

}
