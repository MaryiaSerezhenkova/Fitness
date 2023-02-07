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
import by.academy.fitness.domain.entity.Profile.ACTIVITY_TYPE;
import by.academy.fitness.domain.entity.Profile.GENDER;

public class ProfileDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private UUID uuid;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProfileDTO [uuid=");
		builder.append(uuid);
		builder.append(", dtCreate=");
		builder.append(dtCreate);
		builder.append(", dtUpdate=");
		builder.append(dtUpdate);
		builder.append(", height=");
		builder.append(height);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", dtBirthday=");
		builder.append(dtBirthday);
		builder.append(", target=");
		builder.append(target);
		builder.append(", type=");
		builder.append(type);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dtBirthday, dtCreate, dtUpdate, gender, height, target, type, user, uuid, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileDTO other = (ProfileDTO) obj;
		return Objects.equals(dtBirthday, other.dtBirthday) && Objects.equals(dtCreate, other.dtCreate)
				&& Objects.equals(dtUpdate, other.dtUpdate) && gender == other.gender && height == other.height
				&& Double.doubleToLongBits(target) == Double.doubleToLongBits(other.target) && type == other.type
				&& Objects.equals(user, other.user) && Objects.equals(uuid, other.uuid)
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
	}

}
