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

import by.academy.fitness.config.TimeConverter;
import by.academy.fitness.domain.dto.PaginationContextDTO;
import by.academy.fitness.domain.dto.ProductDTO;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Product;
import by.academy.fitness.exceptions.ServiceException;
import by.academy.fitness.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping(value = "/{uuid}")
	protected ResponseEntity<Product> get(@PathVariable UUID uuid) {
		return ResponseEntity.ok(productService.read(uuid));
	}

	@GetMapping(value = "/error")
	protected void errorTest() {
		throw new ServiceException();
	}

	@PostMapping(value = "/pagination")
	protected ResponseEntity<Page<Product>> getList(@RequestBody PaginationContextDTO paging) {
		Page<Product> page = productService.get(paging.getAmount(), paging.getSkip(), paging.getSortings(), paging.getFilters());
		return ResponseEntity.ok(page);
	}

	@PostMapping
	public ResponseEntity<Product> doPost(@RequestBody ProductDTO data) {
		Product created = this.productService.create(data);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<Product> doPut(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody ProductDTO data) {
		LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.of("UTC"));
		return ResponseEntity.ok(this.productService.update(uuid, dtUpdate, data));
	}

	@DeleteMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<?> doDelete(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody ProductDTO data) {
		 LocalDateTime dtUpdate =
		 LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.of("UTC"));
		productService.delete(uuid, dtUpdate);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
