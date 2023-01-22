package by.academy.fitness.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;

@Entity
public class Audit implements IEntity {

	public enum ESSENCETYPE {
		PRODUCT, DISH, USER, DIARY, REPORT, PROFILE
	}

	private static final long serialVersionUID = 1L;

	@Id
	private UUID uuid;
	@Column(name = "dt_create")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtCreate;
	@ManyToOne
	@JoinColumn(name = "user_uuid")
	private User user;
	@Column
	private String text;
	@Column
	@Enumerated(value = EnumType.STRING)
	private ESSENCETYPE type;
	@Column
	private String id;

	public Audit() {
		super();
	}

	public Audit(UUID uuid, LocalDateTime dtCreate, User user, String text, ESSENCETYPE type, String id) {
		super();
		this.uuid = uuid;
		this.dtCreate = dtCreate;
		this.user = user;
		this.text = text;
		this.type = type;
		this.id = id;
	}
	public Audit(String text, ESSENCETYPE type, String id) {
		this.text = text;
		this.type = type;
		this.id = id;
	}

	public LocalDateTime getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public UUID getUuid() {
		return uuid;
	}

}
