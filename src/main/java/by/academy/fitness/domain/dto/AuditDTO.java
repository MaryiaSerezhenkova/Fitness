package by.academy.fitness.domain.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;
import by.academy.fitness.domain.entity.Audit.ESSENCETYPE;

public class AuditDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private UUID uuid;
	private UserShortViewDTO user;
	private String text;
	private ESSENCETYPE type;
	private String id;
	@JsonProperty(access = Access.READ_ONLY)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtCreate;

	public AuditDTO(UUID uuid, UserShortViewDTO user, String text, ESSENCETYPE type, String id,
			LocalDateTime dtCreate) {
		super();
		this.uuid = uuid;
		this.user = user;
		this.text = text;
		this.type = type;
		this.id = id;
		this.dtCreate = dtCreate;
	}
	

	public AuditDTO() {
		super();
	}


	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public UserShortViewDTO getUser() {
		return user;
	}

	public void setUser(UserShortViewDTO user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ESSENCETYPE getType() {
		return type;
	}

	public void setType(ESSENCETYPE type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dtCreate, id, text, type, user, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuditDTO other = (AuditDTO) obj;
		return Objects.equals(dtCreate, other.dtCreate) && Objects.equals(id, other.id)
				&& Objects.equals(text, other.text) && type == other.type && Objects.equals(user, other.user)
				&& Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "AuditDTO [uuid=" + uuid + ", user=" + user + ", text=" + text + ", type=" + type + ", id=" + id
				+ ", dtCreate=" + dtCreate + "]";
	}

}
