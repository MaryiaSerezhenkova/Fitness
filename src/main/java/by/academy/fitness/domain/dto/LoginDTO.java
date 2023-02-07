package by.academy.fitness.domain.dto;

import java.util.Objects;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginDTO [email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginDTO other = (LoginDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}

	
}
