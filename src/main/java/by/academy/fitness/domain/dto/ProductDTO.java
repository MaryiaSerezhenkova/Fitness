package by.academy.fitness.domain.dto;


import by.academy.fitness.domain.entity.Product.UNIT;



public class ProductDTO  {


	private String name;
	private int weight;
	private UNIT unit;
	private int colories;
	private double  proteins;
	private double  fats;
	private double  carbohydrates;
	
	public ProductDTO() {
		super();
	}

	public ProductDTO(String name, int weight, UNIT unit, int colories, double  proteins, double  fats, double carbohydrates) {
		super();
		this.name = name;
		this.weight = weight;
		this.unit = unit;
		this.colories = colories;
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
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

	public double  getProteins() {
		return proteins;
	}

	public void setProteins(double  proteins) {
		this.proteins = proteins;
	}

	public double  getFats() {
		return fats;
	}

	public void setFats(double  fats) {
		this.fats = fats;
	}

	public double  getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductDTO [name=");
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
		builder.append("]");
		return builder.toString();
	}

	
	
}
