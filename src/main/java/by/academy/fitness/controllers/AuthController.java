package by.academy.fitness.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.academy.fitness.dao.UserDao;
import by.academy.fitness.domain.dto.LoginDTO;
import by.academy.fitness.domain.dto.UserRegistrationDTO;
import by.academy.fitness.domain.entity.Role;
import by.academy.fitness.domain.entity.User;
import by.academy.fitness.domain.entity.User.ROLE;
import by.academy.fitness.security.UserDetailsImpl;
import by.academy.fitness.security.jwt.JwtUtils;
import by.academy.fitness.security.payload.JwtResponse;
import by.academy.fitness.security.payload.MessageResponse;
import by.academy.fitness.service.RoleService;
import by.academy.fitness.service.UserDetailsServiceImpl;
import by.academy.fitness.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private final UserDetailsServiceImpl userDetailsService;
	private final UserService userService;
	private final RoleService roleService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
			UserDetailsServiceImpl userDetailsService, UserService userService, RoleService roleService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.userDetailsService = userDetailsService;
		this.userService = userService;
		this.roleService = roleService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getUuid(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO data) {
		if (userService.existsByUsername(data.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userService.existsByEmail(data.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
		}

		// Create new user's account
	
		userService.create(data);
		// VerificationToken verificationToken =
		// authenticationManager.authenticate(data);

		return ResponseEntity
				.ok(new MessageResponse("User registered successfully!Please check your email for verification link."));
	}
}