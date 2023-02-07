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

import by.academy.fitness.domain.dto.PaginationContextDTO;
import by.academy.fitness.domain.dto.ProductDTO;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping(value = "/{uuid}")
	protected ResponseEntity<ProductDTO> get(@PathVariable UUID uuid) {
		return ResponseEntity.ok(productService.read(uuid));
	}

	@GetMapping
	public ResponseEntity<Page<ProductDTO>> getList(@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<>(productService.get(size, page * size, null, null), HttpStatus.OK);
	}

	@PostMapping(value = "/pagination")
	protected ResponseEntity<Page<ProductDTO>> getList(@RequestBody PaginationContextDTO paging) {
		return ResponseEntity.ok(
				productService.get(paging.getAmount(), paging.getSkip(), paging.getSortings(), paging.getFilters()));
	}

	@PostMapping
	public ResponseEntity<ProductDTO> doPost(@RequestBody ProductDTO data) {
		return new ResponseEntity<>(productService.create(data), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<ProductDTO> doPut(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody ProductDTO data) {
		return ResponseEntity.ok(productService.update(uuid,
				LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.systemDefault()), data));
	}

}
