package by.academy.fitness.domain.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;

public class VerificationTokenDTO {
	

	
	@JsonProperty(access = Access.READ_ONLY)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtCreate;

	@Column(nullable = false)
	@NotEmpty(message = "Token cannot be empty")
	private String token;
	@JsonProperty(access = Access.READ_ONLY)
	private UserShortViewDTO user;
	
	

	public VerificationTokenDTO() {
		super();
	}
	
	
	public VerificationTokenDTO(@NotEmpty(message = "Token cannot be empty") String token) {
		super();
		this.token = token;
	}


	public LocalDateTime getDtCreate() {
		return dtCreate;
	}
	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserShortViewDTO getUser() {
		return user;
	}
	public void setUser(UserShortViewDTO user) {
		this.user = user;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VerificationTokenDTO [dtCreate=");
		builder.append(dtCreate);
		builder.append(", token=");
		builder.append(token);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}


	@Override
	public int hashCode() {
		return Objects.hash(dtCreate, token, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerificationTokenDTO other = (VerificationTokenDTO) obj;
		return Objects.equals(dtCreate, other.dtCreate) && Objects.equals(token, other.token)
				&& Objects.equals(user, other.user);
	}

}
