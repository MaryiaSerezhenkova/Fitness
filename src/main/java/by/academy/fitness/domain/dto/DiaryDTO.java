package by.academy.fitness.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class DiaryDTO {

	private UUID productUuid;

	private UUID dishUuid;

	private int weight;

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
