package by.academy.fitness.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Page;
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
	    public ResponseEntity<Page> getList(
	            @RequestParam int page,
	            @RequestParam int size
	    ) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Audit> report = auditService.get(pageable);

	        return new ResponseEntity<>(report, HttpStatus.OK);
	    }
}
