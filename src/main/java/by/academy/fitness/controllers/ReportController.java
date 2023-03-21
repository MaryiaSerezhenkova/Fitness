package by.academy.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import by.academy.fitness.domain.dto.FileDTO;
import by.academy.fitness.service.ReportService;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {
	private final ReportService reportService;

	@Autowired
	public ReportController(ReportService reportService) {
		super();
		this.reportService = reportService;
	}

	@ResponseStatus(HttpStatus.OK)

	@PostMapping(value = "/download/{type}")

	public FileDTO downloadReport(@PathVariable String type) {

		return reportService.downloadReport(type);

	}
}
