package by.academy.fitness.domain.dto;

import javax.validation.constraints.NotBlank;

public class LoginDTO {

	@NotBlank(message = "Data is not valid")
	private String email;
	@NotBlank(message = "Data is not valid")
	private String password;

	public LoginDTO() {
		super();
	}

	public LoginDTO(@NotBlank(message = "Data is not valid") String email,
			@NotBlank(message = "Data is not valid") String password) {
		super();
		this.email = email;
		this.password = password;
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

	
}
