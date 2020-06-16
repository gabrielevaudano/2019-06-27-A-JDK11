package it.polito.tdp.crimes.model;

public class Arco {
	private String categoryIdUno;
	private String categoryIdDue;
	private int weight;
	
	public Arco(String categoryIdUno, String categoryIdDue, int weight) {
		super();
		this.categoryIdUno = categoryIdUno;
		this.categoryIdDue = categoryIdDue;
		this.weight = weight;
	}

	public String getCategoryIdUno() {
		return categoryIdUno;
	}
	
	public void setCategoryIdUno(String categoryIdUno) {
		this.categoryIdUno = categoryIdUno;
	}
	
	public String getCategoryIdDue() {
		return categoryIdDue;
	}
	
	public void setCategoryIdDue(String categoryIdDue) {
		this.categoryIdDue = categoryIdDue;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}

	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryIdDue == null) ? 0 : categoryIdDue.hashCode());
		result = prime * result + ((categoryIdUno == null) ? 0 : categoryIdUno.hashCode());
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
		Arco other = (Arco) obj;
		if (categoryIdDue == null) {
			if (other.categoryIdDue != null)
				return false;
		} else if (!categoryIdDue.equals(other.categoryIdDue))
			return false;
		if (categoryIdUno == null) {
			if (other.categoryIdUno != null)
				return false;
		} else if (!categoryIdUno.equals(other.categoryIdUno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Arco [categoryIdUno=" + categoryIdUno + ", categoryIdDue=" + categoryIdDue + ", weight=" + weight + "]";
	}
	
	
}
