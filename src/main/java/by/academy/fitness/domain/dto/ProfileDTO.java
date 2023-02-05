package by.academy.fitness.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;
import by.academy.fitness.domain.entity.Profile.ACTIVITY_TYPE;
import by.academy.fitness.domain.entity.Profile.GENDER;

public class ProfileDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private UUID id;
	@JsonProperty(access = Access.READ_ONLY)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtCreate;
	@JsonProperty(access = Access.READ_ONLY)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtUpdate;
	private int height;
	private double weight;
	private String dtBirthday;
	private double target;
	private ACTIVITY_TYPE type;
	private GENDER gender;
	@JsonProperty(access = Access.READ_ONLY)
	private UserShortViewDTO user;
	

	public ProfileDTO() {
		super();
	}

	public ProfileDTO(int height, double weight, String dtBirthday, double target, ACTIVITY_TYPE type, GENDER gender) {
		super();
		this.height = height;
		this.weight = weight;
		this.dtBirthday = dtBirthday;
		this.target = target;
		this.type = type;
		this.gender = gender;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UserShortViewDTO getUser() {
		return user;
	}

	public void setUser(UserShortViewDTO user) {
		this.user = user;
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

}
