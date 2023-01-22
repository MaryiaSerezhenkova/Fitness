package by.academy.fitness.domain.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public class UserRegistrationDTO {
	@NotBlank(message = "Email is not valid")
	private String email;
	@NotBlank(message = "Data is not valid")
	private String username;
	@NotBlank(message = "Data is not valid")
	private String password;
	private Set<String> roles;

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserRegistrationDTO() {
		super();
	}

	public UserRegistrationDTO(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setNick(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
