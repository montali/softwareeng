package it.unipr.BottiMontali;

public class Wine {
	
	private String name;
	private String notes;
	private String vine;
	
	public Wine() {
		this.name = "";
		this.notes = "";
		this.vine = "";
	}
	
	public Wine(final String name, final String notes, final String vine) {
		this.name = name;
		this.notes = notes;
		this.vine = vine;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(final String name) {
		this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Wine)) {
            return false;
        }
        Wine wine = (Wine) o;
        return (name.equals(wine.name)) && (notes.equals(wine.notes)) && (vine.equals(wine.vine));
    }
    
    @Override
    public String toString() {
        return "----WINE----" + " name='" + getName() + "'\n" + "notes='" + getNotes() + "'\n" + " vine='" + getVine() + "'\n" + "\n";
    }
}
