package by.academy.fitness.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;
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
public class VerificationToken implements IEntity {

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
	@JoinColumn(name = "user_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	private User user;
	@Column(name = "user_uuid")
	private UUID userId;

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

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VerificationToken [uuid=");
		builder.append(uuid);
		builder.append(", dtCreate=");
		builder.append(dtCreate);
		builder.append(", token=");
		builder.append(token);
		builder.append(", user=");
		builder.append(user);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dtCreate, token, user, userId, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerificationToken other = (VerificationToken) obj;
		return Objects.equals(dtCreate, other.dtCreate) && Objects.equals(token, other.token)
				&& Objects.equals(user, other.user) && Objects.equals(userId, other.userId)
				&& Objects.equals(uuid, other.uuid);
	}

}
