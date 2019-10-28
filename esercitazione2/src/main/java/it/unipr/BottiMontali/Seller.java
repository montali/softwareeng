package it.unipr.BottiMontali;

/**
 * Seller is a superuser that works for the winehouse.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class Seller extends Person{

	/**
	 * This constructor generates an empty seller.
	 */
	public Seller() {
		super();
	}
	/**
	 * This constructor generates a seller from its username and password.
	 * 
	 * @param username
	 * @param password
	 */
	public Seller(String username, String password) {
		super(username, password);
	}
	/**
	 * This constructor generates a Seller from a generic Person.
	 * 
	 * @param person
	 */
	public Seller (Person person){
		super(person.getUsername(), person.getPassword());
	}
	

	/**
	 * This method ships the ordered wines.
	 * 
	 * @param shop
	 */
	public void shipOrders (Winehouse shop) {
		shop.shipWines(this);
	}
	/**
	 * This method adds a new wine to the winehouse with an arbitrary quantity.
	 * 
	 * @param newWine
	 * @param quantity
	 * @param year
	 * @param shop
	 */
	public void addWines(Wine newWine, Integer quantity, Integer year, Winehouse shop) {
		shop.addWine(this, newWine, year, quantity);
	}
	/**
	 * This method adds a single wine to the winehouse.
	 * 
	 * @param newWine
	 * @param year
	 * @param shop
	 */
	public void addWines(Wine newWine, Integer year, Winehouse shop) {
		shop.addWine(this, newWine, year);
	}
}
