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
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Dish;
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
	protected ResponseEntity<Dish> get(@PathVariable UUID uuid) {
		return ResponseEntity.ok(dishService.read(uuid));
	}

	@GetMapping
	public ResponseEntity<Page<Dish>> getList(@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<>(dishService.get(size, page * size, null, null), HttpStatus.OK);
	}

	@PostMapping(value = "/pagination")
	protected ResponseEntity<Page<Dish>> getList(@RequestBody PaginationContextDTO paging) {
	    Page<Dish> page = dishService.get(paging.getAmount(), paging.getSkip(), paging.getSortings(), paging.getFilters());
	    return ResponseEntity.ok(page);
	}
	
	@PostMapping
	public ResponseEntity<Dish> doPost(@RequestBody DishDTO data) {
		Dish created = this.dishService.create(data);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	@PutMapping(value="/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<Dish> doPut(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody DishDTO data) {
		LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.systemDefault());
		return ResponseEntity.ok(this.dishService.update(uuid, dtUpdate, data));
	}

}


