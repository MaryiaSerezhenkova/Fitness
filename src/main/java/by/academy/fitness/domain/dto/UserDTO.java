package by.academy.fitness.domain.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;
import by.academy.fitness.domain.entity.Role;
import by.academy.fitness.domain.entity.User.USERSTATUS;

public class UserDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private UUID uuid;
	private String email;
	private String username;
	private Set<Role> roles = new HashSet<>();
	private USERSTATUS status;
	private String password;
	@JsonProperty(access = Access.READ_ONLY)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtCreate;
	@JsonProperty(access = Access.READ_ONLY)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtUpdate;

	public UserDTO() {
		super();
	}

	public UserDTO(String email, String username, Set<Role> roles, USERSTATUS status, String password) {
		super();
		this.email = email;
		this.username = username;
		this.roles = roles;
		this.status = status;
		this.password = password;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}


	public LocalDateTime getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
	}

	public LocalDateTime getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public USERSTATUS getStatus() {
		return status;
	}

	public void setStatus(USERSTATUS status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
