package by.academy.fitness.domain.dto;

import by.academy.fitness.domain.entity.Profile.ACTIVITY_TYPE;
import by.academy.fitness.domain.entity.Profile.GENDER;

public class ProfileDTO {
	
	private int height;
	private double weight;
	private String dtBirthday;
	private double target;
	private ACTIVITY_TYPE type;
	private GENDER gender;
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
