package by.academy.fitness.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;

@Entity
@Table(name = "profile", schema = "app")
public class Profile implements IEntity {

	private static final long serialVersionUID = 1L;

	public enum ACTIVITY_TYPE {
		ACTIVE, NOT_ACTIVE

	}

	public enum GENDER {
		MALE, FEMALE
	}

	@Id
	private UUID uuid;

	@Column(name = "dt_create")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtCreate;

	@Column(name = "dt_update")
	@Version
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtUpdate;
	@Column
	private int height;
	@Column
	private double weight;
	@Column(name = "dt_birthday")
	private String dtBirthday;
	@Column
	private double target;
	@Column
	@Enumerated(EnumType.STRING)
	private ACTIVITY_TYPE type;
	@Column
	@Enumerated(EnumType.STRING)
	private GENDER gender;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_uuid", referencedColumnName = "uuid", insertable=false, updatable=false)
	private User user;
	@Column(name = "user_uuid")
	private UUID userId;

	
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	
	public Profile() {
		super();
	}

	public Profile(UUID uuid) {
		super();
		this.uuid = uuid;
	}

	public Profile(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, int height, double weight,
			String dtBirthday, double target, ACTIVITY_TYPE type, GENDER gender, User user) {
		super();
		this.uuid = uuid;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.height = height;
		this.weight = weight;
		this.dtBirthday = dtBirthday;
		this.target = target;
		this.type = type;
		this.gender = gender;
		this.user = user;
	}

	public Profile(int height, double weight, String dtBirthday, double target, ACTIVITY_TYPE type, GENDER gender) {
		this.height = height;
		this.weight = weight;
		this.dtBirthday = dtBirthday;
		this.target = target;
		this.type = type;
		this.gender = gender;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDtBirthday() {
		return dtBirthday;
	}

	public void setDtBirthday(String dtBirthday) {
		this.dtBirthday = dtBirthday;
	}

	public double getTarget() {
		return target;
	}

	public void setTarget(double target) {
		this.target = target;
	}

	public ACTIVITY_TYPE getType() {
		return type;
	}

	public void setType(ACTIVITY_TYPE type) {
		this.type = type;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public UUID getUuid() {
		return uuid;
	}

}
