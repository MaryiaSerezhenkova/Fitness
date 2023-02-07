package by.academy.fitness.domain.dto;

import java.util.Objects;
import java.util.UUID;

public class IngredientDTO {

	private UUID uuid;
	private ProductDTO product;

	private int weight;

	public IngredientDTO() {
		super();
	}

	public IngredientDTO(UUID uuid, ProductDTO product, int weight) {
		super();
		this.uuid = uuid;
		this.product = product;
		this.weight = weight;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IngredientDTO [uuid=");
		builder.append(uuid);
		builder.append(", product=");
		builder.append(product);
		builder.append(", weight=");
		builder.append(weight);
		builder.append("]");
		return builder.toString();
	}

}
