package it.unipr.BottiMontali;

public class Dashboard {
	protected Person loggedIn;
	protected Winehouse shop;

	public Dashboard() {
		this.loggedIn = new Person();
		this.shop = new Winehouse();
	}

	public Dashboard(Person loggingIn, Winehouse shop) {
		this.loggedIn = loggingIn;
		this.shop = shop;
	}

	public void mainMenu() {
	}
}
