package by.academy.fitness.controllers;

import java.time.LocalDateTime;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.TimeConverter;
import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;
import by.academy.fitness.domain.dto.DishDTO;
import by.academy.fitness.domain.dto.ProductDTO;
import by.academy.fitness.domain.entity.Dish;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Product;
import by.academy.fitness.service.DishService;

@RestController
@RequestMapping("/api/dish")
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
	protected ResponseEntity<Page<Dish>> getList(Integer amount, Integer skip, @RequestParam(required=false)List<Sorting> sortings, List<Filtering> filtering) {
	    Page<Dish> page = dishService.get(amount, skip, sortings, filtering);
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
		//LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.of("UTC"));
		LocalDateTime dtUpdate = TimeConverter.convert(dtUpdateRow);
		return ResponseEntity.ok(this.dishService.update(uuid, dtUpdate, data));
	}

	@DeleteMapping(value="/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<?> doDelete(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody ProductDTO data) {
	//	LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.of("UTC"));
		LocalDateTime dtUpdate = TimeConverter.convert(dtUpdateRow);
		dishService.delete(uuid, dtUpdate);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


