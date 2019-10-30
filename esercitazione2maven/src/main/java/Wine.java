/**
 * Wine describes a single type of wine.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class Wine {
	
	private String name;
	private String notes;
	private String vine;
	
	/**
	 * This constructor generates an empty Wine object
	 */
	public Wine() {
		this.name = "";
		this.notes = "";
		this.vine = "";
	}
	
	/**
	 * This constructor generates a Wine object by its properties
	 * 
	 * @param name
	 * @param notes
	 * @param vine
	 */
	public Wine(final String name, final String notes, final String vine) {
		this.name = name;
		this.notes = notes;
		this.vine = vine;
	}
	
	/**
	 * This method gets the wine name
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This method sets the wine name
	 * 
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * This method gets the wine's notes
	 * 
	 * @return the notes
	 */
	public String getNotes() {
		return this.notes;
	}
	
	/**
	 * This method sets the wine notes
	 * 
	 * @param notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}
	
	/**
	 * This method gets the wine's vine
	 * 
	 * @return the vine
	 */
	public String getVine() {
		return this.vine;
	}
	
	/**
	 * This method sets the wine's vine
	 * 
	 * @param vine
	 */
	public void setVine(final String vine) {
		this.vine = vine;
	}

    /**
     * This method checks if two wines are equal
     */
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
    
    /**
     * This method generates a string describing the wine
     */
    @Override
    public String toString() {
        return "----WINE----" + " name='" + getName() + "'\n" + "notes='" + getNotes() + "'\n" + " vine='" + getVine() + "'\n" + "\n";
    }
}
