package by.academy.fitness.controllers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.academy.fitness.domain.dto.DiaryDTO;
import by.academy.fitness.domain.dto.PaginationContextDTO;
import by.academy.fitness.domain.entity.Diary;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.service.DiaryService;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

	private final DiaryService diaryService;

	@Autowired
	public DiaryController(DiaryService diaryService) {
		super();
		this.diaryService = diaryService;
	}

	@GetMapping(value = "/{uuid}")
	protected ResponseEntity<Diary> get(@PathVariable UUID uuid) {
		return ResponseEntity.ok(diaryService.read(uuid));
	}

	@PostMapping(value = "/pagination")
	protected ResponseEntity<Page<Diary>> getList(@RequestBody PaginationContextDTO paging) {
		Page<Diary> page = diaryService.get(paging.getAmount(), paging.getSkip(), paging.getSortings(), paging.getFilters());
		return ResponseEntity.ok(page);
	}

	@PostMapping
	public ResponseEntity<Diary> doPost(@RequestBody DiaryDTO data) {
		Diary created = this.diaryService.create(data);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<Diary> doPut(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody DiaryDTO data) {
		LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.systemDefault());
		return ResponseEntity.ok(this.diaryService.update(uuid, dtUpdate, data));
	}

	@DeleteMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<?> doDelete(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody DiaryDTO data) {
		LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.systemDefault());
		diaryService.delete(uuid, dtUpdate);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
