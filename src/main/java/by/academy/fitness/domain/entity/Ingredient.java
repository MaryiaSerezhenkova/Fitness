package by.academy.fitness.domain.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient", schema = "app")
public class Ingredient implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private UUID uuid;
	@ManyToOne
	@JoinColumn(name = "product_uuid", referencedColumnName = "uuid")
	private Product product;
	@Column
	private int weight;
	

	public Ingredient() {
		super();
	}

	public Ingredient(UUID uuid, Product product, int weight) {
		super();
		this.product = product;
		this.weight = weight;
	}

	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
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
		builder.append("Ingredient [uuid=");
		builder.append(uuid);
		builder.append(", product=");
		builder.append(product);
		builder.append(", weight=");
		builder.append(weight);
		builder.append("]");
		return builder.toString();
	}

	
}
