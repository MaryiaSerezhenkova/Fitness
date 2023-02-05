package by.academy.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.academy.fitness.domain.dto.LoginDTO;
import by.academy.fitness.domain.dto.UserDTO;
import by.academy.fitness.security.UserDetailsImpl;
import by.academy.fitness.security.jwt.JwtUtils;
import by.academy.fitness.security.payload.JwtResponse;
import by.academy.fitness.security.payload.MessageResponse;
import by.academy.fitness.service.UserService;
import by.academy.fitness.service.VerificationService;

@RestController
@RequestMapping("api/v1/users")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private final UserService userService;
	private final VerificationService verificationService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService,
			VerificationService verificationService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.userService = userService;
		this.verificationService = verificationService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUuid(), userDetails.getEmail()));
	}

//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO data) {
//		if (userService.existsByUsername(data.getUsername())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//		}
//
//		if (userService.existsByEmail(data.getEmail())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
//		}
//
//		// Create new user's account
//
//		// userService.create(data);
//
//		verificationService.waitingActivation(data);
//		return ResponseEntity
//				.ok(new MessageResponse("User registered successfully!Please check your email for verification link."));
//	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO data) {
		if (userService.existsByUsername(data.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userService.existsByEmail(data.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
		}

//		// Create new user's account

		// userService.create(data);

		verificationService.registration(data);
		return ResponseEntity
				.ok(new MessageResponse("You registered successfully! Please check your email for verification link."));
	}

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody UserDTO data) {
		verificationService.waitingActivation(data);
		return ResponseEntity.ok(new MessageResponse("User saved to database. Token sent to email"));
	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<?> verifyUser(@PathVariable("token") String token) {
		if (verificationService.verify(token)) {
			return ResponseEntity.ok(new MessageResponse("User activated successfully!"));
		}
		return ResponseEntity.notFound().build();
	}
}