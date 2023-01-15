package by.academy.fitness.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;

@Entity
@Table(name = "verification_token", schema = "app")
public class VerificationToken implements IEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private UUID uuid;

	@Column(name = "dt_create")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtCreate;

	@Column(nullable = false)
	@NotEmpty(message = "Token cannot be empty")
	private String token;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "user_uuid", nullable = false, referencedColumnName = "uuid")
	private User user;

	public VerificationToken() {
		super();
	}

	public VerificationToken(String token, User user) {
		this.token = token;
		this.user = user;
	}
	
	public VerificationToken(UUID uuid, LocalDateTime dtCreate,
			@NotEmpty(message = "Token cannot be empty") String token, User user) {
		super();
		this.uuid = uuid;
		this.dtCreate = dtCreate;
		this.token = token;
		this.user = user;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
