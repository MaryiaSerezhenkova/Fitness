package by.academy.fitness.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;

@Entity
@Table(name = "product", schema = "app")
public class Product implements IEntity {

	public enum UNIT {
		GR, ML

	}

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
	@Column(name = "dt_update")
	@Version
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtUpdate;
	@Column
	private String name;
	@Column
	private int weight;
	@Column
	@Enumerated(EnumType.STRING)
	private UNIT unit;
	@Column
	private int colories;
	@Column
	private double proteins;
	@Column
	private double fats;
	@Column
	private double carbohydrates;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_uuid", referencedColumnName = "uuid")
//	@LazyToOne(LazyToOneOption.PROXY)
	private User user;

	public Product() {
		super();
	}

	public Product(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, int weight, UNIT unit,
			int colories, double proteins, double fats, double carbohydrates, User user) {
		super();
		this.uuid = uuid;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.name = name;
		this.weight = weight;
		this.unit = unit;
		this.colories = colories;
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
		this.user = user;
	}

	public Product(String name, int weight, UNIT unit, int colories, double proteins, double fats,
			double carbohydrates) {
		this.name = name;
		this.weight = weight;
		this.unit = unit;
		this.colories = colories;
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
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

	public LocalDateTime getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public UNIT getUnit() {
		return unit;
	}

	public void setUnit(UNIT unit) {
		this.unit = unit;
	}

	public int getColories() {
		return colories;
	}

	public void setColories(int colories) {
		this.colories = colories;
	}

	public double getProteins() {
		return proteins;
	}

	public void setProteins(double proteins) {
		this.proteins = proteins;
	}

	public double getFats() {
		return fats;
	}

	public void setFats(double fats) {
		this.fats = fats;
	}

	public double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [uuid=");
		builder.append(uuid);
		builder.append(", dtCreate=");
		builder.append(dtCreate);
		builder.append(", dtUpdate=");
		builder.append(dtUpdate);
		builder.append(", name=");
		builder.append(name);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", unit=");
		builder.append(unit);
		builder.append(", colories=");
		builder.append(colories);
		builder.append(", proteins=");
		builder.append(proteins);
		builder.append(", fats=");
		builder.append(fats);
		builder.append(", carbohydrates=");
		builder.append(carbohydrates);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

}
