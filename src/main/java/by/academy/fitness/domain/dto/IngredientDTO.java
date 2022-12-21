package by.academy.fitness.domain.dto;

import java.util.UUID;

public class IngredientDTO {

	private UUID productUuid;

	private int weight;

	public IngredientDTO() {
		super();
	}

	public IngredientDTO(UUID productUuid, int weight) {
		super();
		this.productUuid = productUuid;
		this.weight = weight;
	}

	public UUID getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(UUID productUuid) {
		this.productUuid = productUuid;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
