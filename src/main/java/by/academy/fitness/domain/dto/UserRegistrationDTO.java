package by.academy.fitness.domain.dto;

import javax.validation.constraints.NotBlank;

public class UserRegistrationDTO {
	@NotBlank(message = "Email is mandatory")
	private String email;
	@NotBlank(message = "Name is mandatory")
	private String username;
	
	private String password;

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
