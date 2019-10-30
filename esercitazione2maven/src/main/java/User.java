import java.util.ArrayList;
import java.util.HashMap;

/**
 * User is a simple user that can buy wine from the winehouse.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class User extends Person{

	/**
	 * This constructor generates an empty user
	 */
	public User () {
	}
	/**
	 * This constructor generates a user from its username and password
	 * 
	 * @param username
	 * @param password
	 */
	public User (String username, String password) {
		super(username, password);
	}
	/**
	 * This constructor generates a user from a generic person
	 * 
	 * @param user
	 */
	public User(Person user){
		super(user.getUsername(), user.getPassword());
	}

	/**
	 * This method notifies the user of a newly arrived wine. In real life, it could send an email.
	 */
	public void notifyWineAdded () {
		System.out.println("Un vino richiesto da "+ this.getUsername() + " e' stato aggiunto.");
	}

	/**
	 * This method orders a wine from the winehouse.
	 * 
	 * @param wantedWine
	 * @param shop
	 * @param year
	 * @param quantity
	 */
	public void orderWine (final Wine wantedWine, final Winehouse shop, final Integer year, final Integer quantity){
		shop.orderWine(this, wantedWine, year, quantity);
	}
	/**
	 * This method returns the user's orders
	 * 
	 * @param shop
	 * @return the orders
	 */
	public ArrayList<Order> getMyOrders (final Winehouse shop) {
		return shop.getOrdersForUser(this);
	}
	/**
	 * This method returns a wine from the winehouse
	 * 
	 * @param name
	 * @param shop
	 * @return the wine and its quantities
	 */
	public HashMap<Wine,InventoryItem> getWineByName (String name, Winehouse shop) {
		return shop.findWinesName(this, name);
	}
	/**
	 * This method returns wines by year.
	 * 
	 * @param year
	 * @param shop
	 * @return the wines and their quantities
	 */
	public HashMap<Wine,InventoryItem> getWinesByYear (Integer year, Winehouse shop) {
		return shop.findWinesYear(this, year);
	}
}
