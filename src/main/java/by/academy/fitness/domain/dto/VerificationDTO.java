package by.academy.fitness.domain.dto;

import javax.validation.constraints.NotBlank;

public class VerificationDTO {
	@NotBlank(message = "Data is not valid")
	private String email;
	@NotBlank(message = "Data is not valid")
	private String password;
	@NotBlank(message = "Data is not valid")
	private String token;

	public VerificationDTO() {
		super();
	}

	public VerificationDTO(String email, String password, String token) {
		super();
		this.email = email;
		this.password = password;
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}