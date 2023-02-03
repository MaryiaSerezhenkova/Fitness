package by.academy.fitness.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;

@Entity
@Table(name = "diary", schema = "app")
public class Diary implements IEntity {

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
	@ManyToOne(optional = true)
	@JoinColumn(name = "product_uuid", nullable = true, referencedColumnName = "uuid")
	private Product product;
	@ManyToOne(optional = true)
	@JoinColumn(name = "dish_uuid", nullable = true, referencedColumnName = "uuid")
	private Dish dish;
	@Column
	private int weight;
	@Column(name = "meal_time")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime mealTime;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "profile_uuid", referencedColumnName = "uuid")
	private Profile profile;

	public Diary() {
		super();
	}

	public Diary(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, Product product, Dish dish, int weight,
			LocalDateTime mealTime) {
		super();
		this.uuid = uuid;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.product = product;
		this.dish = dish;
		this.weight = weight;
		this.mealTime = mealTime;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public LocalDateTime getMealTime() {
		return mealTime;
	}

	public void setMealTime(LocalDateTime mealTime) {
		this.mealTime = mealTime;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Diary [uuid=");
		builder.append(uuid);
		builder.append(", dtCreate=");
		builder.append(dtCreate);
		builder.append(", dtUpdate=");
		builder.append(dtUpdate);
		builder.append(", product=");
		builder.append(product);
		builder.append(", dish=");
		builder.append(dish);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", mealTime=");
		builder.append(mealTime);
		builder.append(", profile=");
		builder.append(profile);
		builder.append("]");
		return builder.toString();
	}

	
}
