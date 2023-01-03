package by.academy.fitness.dao;

import javax.persistence.metamodel.SingularAttribute;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Filtering {

	private String id;
	private Object value;
	private boolean isNot = false;
	private boolean isLike = false;
	private RangeCondition rangeCondition;

	public Filtering() {
		super();
	}

	public Filtering(String id, Object value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Filtering(String id, Object value, RangeCondition rangeCondition) {
		super();
		this.id = id;
		this.value = value;
		this.rangeCondition = rangeCondition;
	}

	public Filtering(String id, Object value, boolean isNot) {
		super();
		this.id = id;
		this.value = value;
		this.isNot = isNot;
	}

	public Filtering(String id, Object value, boolean isNot, boolean isLike) {
		super();
		this.id = id;
		this.value = value;
		this.isNot = isNot;
		this.isLike = isLike;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isNot() {
		return isNot;
	}

	public void setNot(boolean isNot) {
		this.isNot = isNot;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	public RangeCondition getRangeCondition() {
		return rangeCondition;
	}

	public void setRangeCondition(RangeCondition rangeCondition) {
		this.rangeCondition = rangeCondition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filtering other = (Filtering) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Filtering [id=");
		builder.append(id);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

	public enum RangeCondition {
		GREATHER, GREATHER_OR_EQUAL, LESS, LESS_OR_EQUAL
	}

}