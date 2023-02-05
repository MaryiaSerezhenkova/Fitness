package by.academy.fitness.controllers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.academy.fitness.domain.dto.DiaryDTO;
import by.academy.fitness.domain.dto.PaginationContextDTO;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.service.DiaryService;

@RestController
@RequestMapping("/api/v1/profile/{profile_uuid}/diary")
public class DiaryController {

	private final DiaryService diaryService;

	@Autowired
	public DiaryController(DiaryService diaryService) {
		super();
		this.diaryService = diaryService;
	}

	@GetMapping(value = "/{uuid}")
	protected ResponseEntity<DiaryDTO> get(@PathVariable UUID uuid) {
		return ResponseEntity.ok(diaryService.read(uuid));
	}

	@GetMapping
	public ResponseEntity<Page<DiaryDTO>> getList(@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<>(diaryService.get(size, page * size, null, null), HttpStatus.OK);
	}

	@PostMapping(value = "/pagination")
	protected ResponseEntity<Page<DiaryDTO>> getList(@RequestBody PaginationContextDTO paging) {
		return ResponseEntity
				.ok(diaryService.get(paging.getAmount(), paging.getSkip(), paging.getSortings(), paging.getFilters()));
	}

	@PostMapping
	public ResponseEntity<DiaryDTO> doPost(@RequestBody DiaryDTO data) {
		return new ResponseEntity<>(this.diaryService.create(data), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<DiaryDTO> doPut(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody DiaryDTO data) {
		return ResponseEntity.ok(this.diaryService.update(uuid,
				LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.systemDefault()), data));
	}

}
