package by.academy.fitness.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;

public class DiaryDTO {
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
	private ProductDTO product;

	private DishDTO dish;
	private ProfileDTO profile;

	private int weight;
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime mealTime;

	public DiaryDTO() {
		super();
	}

	public DiaryDTO(ProductDTO product, DishDTO dish, int weight, LocalDateTime mealTime) {
		super();
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

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public DishDTO getDish() {
		return dish;
	}

	public void setDish(DishDTO dish) {
		this.dish = dish;
	}

	public ProfileDTO getProfile() {
		return profile;
	}

	public void setProfile(ProfileDTO profile) {
		this.profile = profile;
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

	

}
