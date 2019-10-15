package it.unipr.BottiMontali;

public class Wine {
	
	private String name;
	private int year;
	private String notes;
	private String vine;
	private int quantity;
	
	public Wine() {
		this.name = "";
		this.year = 0;
		this.notes = "";
		this.vine = "";
		this.quantity = 0;
	}
	
	public Wine(final String name, final int year, final String notes, final String vine, final int quantity) {
		this.name = name;
		this.year = year;
		this.notes = notes;
		this.vine = vine;
		this.quantity = quantity;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public void setYear(final int year) {
		this.year = year;
	}
	
	public String getNotes() {
		return this.notes;
	}
	
	public void setNotes(final String notes) {
		this.notes = notes;
	}
	
	public String getVine() {
		return this.vine;
	}
	
	public void setVine(final String vine) {
		this.vine = vine;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Wine)) {
            return false;
        }
        Wine wine = (Wine) o;
        return (name == wine.name) && (year == wine.year) && (notes == wine.notes) && (vine == wine.vine);
    }
    
    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", year ='" + getYear() + "'" + " notes='" + getNotes() + "'" + " vine='" + getVine() + "'" + "}";
    }
}
