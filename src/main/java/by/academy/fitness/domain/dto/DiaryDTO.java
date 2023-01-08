package by.academy.fitness.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import by.academy.fitness.config.CustomLocalDateTimeDesSerializer;
import by.academy.fitness.config.CustomLocalDateTimeSerializer;

public class DiaryDTO {

	private UUID productUuid;

	private UUID dishUuid;

	private int weight;
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime mealTime;

	public DiaryDTO() {
		super();
	}

	public DiaryDTO(UUID productUuid, UUID dishUuid, int weight, LocalDateTime mealTime) {
		super();
		this.productUuid = productUuid;
		this.dishUuid = dishUuid;
		this.weight = weight;
		this.mealTime = mealTime;
	}

	public UUID getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(UUID productUuid) {
		this.productUuid = productUuid;
	}

	public UUID getDishUuid() {
		return dishUuid;
	}

	public void setDishUuid(UUID dishUuid) {
		this.dishUuid = dishUuid;
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
