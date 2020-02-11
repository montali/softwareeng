package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
	
	private StringProperty name;
	private StringProperty notes;
	private StringProperty vine;
	private IntegerProperty year;
	private IntegerProperty qty;
	
	/**
	 * This constructor generates an empty Wine object
	 */
	public Wine() {
		this.name = new SimpleStringProperty( "");
		this.notes = new SimpleStringProperty("");
		this.vine = new SimpleStringProperty("");
		this.year = new SimpleIntegerProperty(0);
		this.qty = new SimpleIntegerProperty(0);
	}
	
	/**
	 * This constructor generates a Wine object by its properties
	 * 
	 * @param name
	 * @param notes
	 * @param vine
	 */
	public Wine(final String name, final String notes, final String vine, final int qty, final int year) {
		this.name = new SimpleStringProperty(name);
		this.notes = new SimpleStringProperty(notes);
		this.vine = new SimpleStringProperty(vine);
		this.year = new SimpleIntegerProperty(year);
		this.qty = new SimpleIntegerProperty(qty);
	}
	
	/**
	 * This method gets the wine name
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name.get();
	}
	
	/**
	 * This method sets the wine name
	 * 
	 * @param name
	 */
	public void setName(final String name) {
		this.name.set(name);
	}
	
	/**
	 * This method gets the wine's notes
	 * 
	 * @return the notes
	 */
	public String getNotes() {
		return this.notes.get();
	}
	
	/**
	 * This method sets the wine notes
	 * 
	 * @param notes
	 */
	public void setNotes(final String notes) {
		this.notes.set(notes);
	}
	
	/**
	 * This method gets the wine's vine
	 * 
	 * @return the vine
	 */
	public String getVine() {
		return this.vine.get();
	}
	
	/**
	 * This method sets the wine's vine
	 * 
	 * @param vine
	 */
	public void setVine(final String vine) {
		this.vine.set(vine);
	}

	public void setQty(final int qty) {this.qty.set(qty);}

	public void setYear(final int year) {this.year.set(year);}

	public int getQty() {return this.qty.get();}

	public int getYear() {return this.year.get();}


	// Properties retrieval
	public StringProperty nameProperty(){
		return this.name;
	}

	public StringProperty vineProperty(){
		return this.vine;
	}

	public StringProperty notesProperty(){
		return this.notes;
	}

	public IntegerProperty qtyProperty(){
		return this.qty;
	}
	public IntegerProperty yearProperty(){
		return this.year;
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
        return (name.getValue().equals(wine.name.getValue())) && this.year.getValue() == wine.year.getValue();
    }
    
    /**
     * This method generates a string describing the wine
     */
    @Override
    public String toString() {
        return "----WINE----" + " name='" + getName() + "'\n" + "notes='" + getNotes() + "'\n" + " vine='" + getVine() + "'\n" + "\n";
    }
}
