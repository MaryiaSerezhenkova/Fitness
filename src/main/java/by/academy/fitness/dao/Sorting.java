package by.academy.fitness.dao;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

public class Sorting {

    private String id;
    private boolean desc;

    private List<SortCase> cases;

    public Sorting() {
        super();
    }

    public Sorting(String id, boolean desc) {
        this.id = id;
        this.desc = desc;
    }

  
    static class SortCase {
        private String literal;
        private Integer sortOrder;
    }

    public static Sorting of(String id) {
        return new Sorting(id, false);
    }

//    public static Sorting of(SingularAttribute<?, ?> id) {
//        return new Sorting(id.getName(), false);
//    }

    public static Sorting of(String id, boolean desc) {
        return new Sorting(id, desc);
    }

//    public static Sorting of(SingularAttribute<?, ?> id, boolean desc) {
//        return new Sorting(id.getName(), desc);
//    }

    public static Sorting sortingFrom(String id, boolean desc) {
        return new Sorting(id, desc);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public List<SortCase> getCases() {
		return cases;
	}

	public void setCases(List<SortCase> cases) {
		this.cases = cases;
	}

}
