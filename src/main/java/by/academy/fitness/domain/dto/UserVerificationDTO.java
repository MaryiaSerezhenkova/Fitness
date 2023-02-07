package by.academy.fitness.domain.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class UserVerificationDTO {
	
	@NotBlank(message = "Data is not valid")
	private String email;
	@NotBlank(message = "Data is not valid")
	private String password;
	@NotBlank(message = "Data is not valid")
	private String token;

	public UserVerificationDTO() {
		super();
	}

	public UserVerificationDTO(String email, String password, String token) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserVerificationDTO [email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVerificationDTO other = (UserVerificationDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(token, other.token);
	}


}