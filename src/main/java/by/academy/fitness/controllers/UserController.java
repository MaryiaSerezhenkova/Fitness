package by.academy.fitness.controllers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.academy.fitness.domain.dto.PaginationContextDTO;
import by.academy.fitness.domain.dto.UserDTO;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping(value = "/{uuid}")
	protected ResponseEntity<UserDTO> get(@PathVariable UUID uuid) {
		return ResponseEntity.ok(userService.read(uuid));
	}

	@GetMapping("/me")
	public ResponseEntity<UserDTO> me() {
		return new ResponseEntity<>(
				userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()),
				HttpStatus.OK);
	}

	@PutMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<UserDTO> doPut(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody UserDTO data) {
		return ResponseEntity.ok(userService.update(uuid,
				LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.systemDefault()), data));
	}

	@DeleteMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<?> doDelete(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody UserDTO data) {
		userService.delete(uuid, LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.systemDefault()));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/pagination")
	protected ResponseEntity<Page<UserDTO>> getList(@RequestBody PaginationContextDTO paging) {
		return ResponseEntity
				.ok(userService.get(paging.getAmount(), paging.getSkip(), paging.getSortings(), paging.getFilters()));
	}

	@GetMapping
	public ResponseEntity<Page<UserDTO>> getList(@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<>(userService.get(size, page * size, null, null), HttpStatus.OK);
	}
}
