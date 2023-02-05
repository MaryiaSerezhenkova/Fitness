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

import by.academy.fitness.domain.dto.DishDTO;
import by.academy.fitness.domain.dto.PaginationContextDTO;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.service.DishService;

@RestController
@RequestMapping("/api/v1/dish")
public class DishController {

	private final DishService dishService;

	@Autowired
	public DishController(DishService dishService) {
		super();
		this.dishService = dishService;
	}

	@GetMapping(value = "/{uuid}")
	protected ResponseEntity<DishDTO> get(@PathVariable UUID uuid) {
		return ResponseEntity.ok(dishService.read(uuid));
	}

	@GetMapping
	public ResponseEntity<Page<DishDTO>> getList(@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<>(dishService.get(size, page * size, null, null), HttpStatus.OK);
	}

	@PostMapping(value = "/pagination")
	protected ResponseEntity<Page<DishDTO>> getList(@RequestBody PaginationContextDTO paging) {
		return ResponseEntity
				.ok(dishService.get(paging.getAmount(), paging.getSkip(), paging.getSortings(), paging.getFilters()));
	}

	@PostMapping
	public ResponseEntity<DishDTO> doPost(@RequestBody DishDTO data) {
		return new ResponseEntity<>(dishService.create(data), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<DishDTO> doPut(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody DishDTO data) {
		return ResponseEntity.ok(dishService.update(uuid,
				LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.systemDefault()), data));
	}

}
