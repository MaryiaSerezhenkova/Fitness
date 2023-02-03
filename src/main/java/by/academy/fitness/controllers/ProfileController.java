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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import by.academy.fitness.domain.dto.ProfileDTO;
import by.academy.fitness.domain.entity.Audit;
import by.academy.fitness.domain.entity.Page;
import by.academy.fitness.domain.entity.Profile;
import by.academy.fitness.service.ProfileService;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

	private final ProfileService profileService;

	@Autowired
	public ProfileController(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}

	@GetMapping(value = "/{uuid}")
	protected ResponseEntity<Profile> get(@PathVariable UUID uuid) {
		return ResponseEntity.ok(profileService.read(uuid));
	}

	@PostMapping
	public ResponseEntity<Profile> doPost(@RequestBody ProfileDTO data) {
		Profile created = this.profileService.create(data);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<Page<Profile>> getList(@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<>(profileService.get(size, page * size, null, null), HttpStatus.OK);
	}

	@PutMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<Profile> doPut(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody ProfileDTO data) {
		LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.systemDefault());
		return ResponseEntity.ok(this.profileService.update(uuid, dtUpdate, data));
	}

	@DeleteMapping(value = "/{uuid}/dtUpdate/{dt_update}")
	protected ResponseEntity<?> doDelete(@PathVariable UUID uuid, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody ProfileDTO data) {
		LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.systemDefault());
		profileService.delete(uuid, dtUpdate);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
