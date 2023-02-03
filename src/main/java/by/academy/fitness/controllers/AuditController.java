package by.academy.fitness.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.academy.fitness.domain.builders.AuditMapper;
import by.academy.fitness.domain.dto.PaginationContextDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.service.AuditService;

@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {

	private final AuditService auditService;

	@Autowired
	public AuditController(AuditService auditService) {
		super();
		this.auditService = auditService;
	}

	@GetMapping(value = "/{uuid}")
	protected ResponseEntity<Audit> get(@PathVariable UUID uuid) {
		return ResponseEntity.ok(auditService.read(uuid));
	}

	@GetMapping
	public ResponseEntity<Page<Audit>> getList(@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<>(auditService.get(size, page * size, null, null), HttpStatus.OK);
	}

	@PostMapping(value = "/pagination")
	protected ResponseEntity<Page<Audit>> getList(@RequestBody PaginationContextDTO paging) {
		Page<Audit> page = auditService.get(paging.getAmount(), paging.getSkip(), paging.getSortings(),
				paging.getFilters());
		return ResponseEntity.ok(page);
	}
}
