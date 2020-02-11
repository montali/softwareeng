package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

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

	private IntegerProperty newlyArrivedWines;
	/**
	 * This constructor generates an empty user
	 */
	public User() {
		this.newlyArrivedWines.set(0);
	}
	/**
	 * This constructor generates a user from its username and password and ordered wines
	 * 
	 * @param username
	 * @param password
	 */
	public User(String username, String password, int newlyArrivedWines) {
		super(username, password);
		this.newlyArrivedWines = new SimpleIntegerProperty(newlyArrivedWines);
	}
	/**
	 * This constructor generates a user from its username and password
	 *
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		super(username, password);
		this.newlyArrivedWines = new SimpleIntegerProperty(0);
	}
	/**
	 * This constructor generates a user from a generic person
	 * 
	 * @param user
	 */
	public User(Person user){
		super(user.getUsername(), user.getPassword());
		this.newlyArrivedWines.set(0);
	}

	/**
	 * This method notifies the user of a newly arrived wine. In real life, it could send an email.
	 */
	public void notifyWineAdded () {
		this.newlyArrivedWines.set(this.newlyArrivedWines.get()+1);
	}

	/**
	 * This method orders a wine from the winehouse.
	 * 
	 * @param wantedWine
	 * @param shop
	 * @param quantity
	 */
	public void orderWine (final Wine wantedWine, final Winehouse shop, final Integer quantity){
		shop.orderWine(this, wantedWine, quantity);
	}
	/**
	 * This method returns the user's orders
	 * 
	 * @param  shop
	 * @return the orders
	 */
	public ObservableList<Order> getMyOrders (final Winehouse shop) {
		return shop.getOrdersForUser(this);
	}
	/**
	 * This method returns a wine from the winehouse
	 * 
	 * @param name
	 * @param shop
	 * @return the wine and its quantities
	 */
	public ObservableList<Wine> getWineByName (String name, Winehouse shop) {
		return shop.findWinesName(name);
	}
	/**
	 * This method returns wines by year.
	 * 
	 * @param year
	 * @param shop
	 * @return the wines and their quantities
	 */
	public ObservableList<Wine> getWinesByYear (Integer year, Winehouse shop) {
		return shop.findWinesYear(year);
	}
	
	/*
	 * This method adds a wine to the list of request
	 * 
	 * @param requestedWine
	 * @param shop
	 */
	public void requestWine (String requestedWine, Winehouse shop) {
		shop.requestWine(this, requestedWine);
	}

	public int getOrderedNo(Winehouse shop){
		return shop.getOrdersForUser(this).size();
	}
	public int getPendingNo(Winehouse shop){
		int i = 0;
		for (Order temp: shop.getOrdersForUser(this)){
			if (!temp.isShipped())
				i++;
		}
		return i;
	}

	public void resetNewlyArrivedWines (){
		this.newlyArrivedWines.set(0);
	}
	public IntegerProperty newlyArrivedProperty (){
		return this.newlyArrivedWines;
	}

	public int getNewlyArrivedWines (){
		return this.newlyArrivedWines.get();
	}
}
