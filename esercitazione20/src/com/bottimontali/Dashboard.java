package com.bottimontali;

/**
 * This class describes a generic dashboard.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class Dashboard {
	protected Person loggedIn;
	protected Winehouse shop;

	/**
	 * This constructor generates an empty dashboard.
	 */
	public Dashboard() {
		this.loggedIn = new Person();
		this.shop = new Winehouse();
	}

	/**
	 * This constructor logs a user in a winehouse.
	 * 
	 * @param loggingIn
	 * @param shop
	 */
	public Dashboard(Person loggingIn, Winehouse shop) {
		this.loggedIn = loggingIn;
		this.shop = shop;
	}

	/**
	 * This method is implemented in subclasses.
	 */
	public void mainMenu() {
	}
}
