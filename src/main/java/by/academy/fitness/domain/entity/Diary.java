package by.academy.fitness.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@JoinColumn(name = "product_uuid", nullable = true, referencedColumnName = "uuid",insertable=false, updatable=false)
	private Product product;
	@ManyToOne(optional = true)
	@JoinColumn(name = "dish_uuid", nullable = true, referencedColumnName = "uuid",insertable=false, updatable=false)
	private Dish dish;
	@Column
	private int weight;
	@Column(name = "meal_time")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime mealTime;
	@ManyToOne
	//(fetch=FetchType.LAZY)
	@JoinColumn(name = "profile_uuid", referencedColumnName = "uuid",insertable=false, updatable=false)
	private Profile profile;
	@Column(name = "profile_uuid")
	private UUID profileId;
	@Column(name = "dish_uuid")
	private UUID dishId;
	@Column(name = "product_uuid")
	private UUID productId;



	public UUID getDishId() {
		return dishId;
	}

	public void setDishId(UUID dishId) {
		this.dishId = dishId;
	}

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

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

	public UUID getProfileId() {
		return profileId;
	}

	public void setProfileId(UUID profileId) {
		this.profileId = profileId;
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

	@Override
	public int hashCode() {
		return Objects.hash(dish, dishId, dtCreate, dtUpdate, mealTime, product, productId, profile, profileId, uuid,
				weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diary other = (Diary) obj;
		return Objects.equals(dish, other.dish) && Objects.equals(dishId, other.dishId)
				&& Objects.equals(dtCreate, other.dtCreate) && Objects.equals(dtUpdate, other.dtUpdate)
				&& Objects.equals(mealTime, other.mealTime) && Objects.equals(product, other.product)
				&& Objects.equals(productId, other.productId) && Objects.equals(profile, other.profile)
				&& Objects.equals(profileId, other.profileId) && Objects.equals(uuid, other.uuid)
				&& weight == other.weight;
	}

	
}
