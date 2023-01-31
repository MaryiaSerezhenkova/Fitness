package by.academy.fitness.security.payload;

import java.util.List;
import java.util.UUID;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private UUID uuid;
	private String username;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, UUID uuid, String username, String email, List<String> roles) {
		this.token = accessToken;
		this.uuid = uuid;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
	public JwtResponse(String accessToken, UUID uuid, String email) {
		this.token = accessToken;
		this.uuid = uuid;
		this.email = email;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getRoles() {
		return roles;
	}
}