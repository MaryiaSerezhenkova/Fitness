package by.academy.fitness.domain.dto;

import java.util.HashSet;
import java.util.Set;

import by.academy.fitness.domain.entity.Role;
import by.academy.fitness.domain.entity.User.USERSTATUS;

public class UserDTO {
	private String email;
	private String username;
	private Set<Role> roles = new HashSet<>();
	private USERSTATUS status;
	private String password;

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
