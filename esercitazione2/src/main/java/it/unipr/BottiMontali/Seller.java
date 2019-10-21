package it.unipr.BottiMontali;

public class Seller extends Person{

	public Seller() {
		super();
	}
	public Seller(String username, String password) {
		super(username, password);
	}
	public Seller (Person person){
		super(person.getUsername(), person.getPassword);
	}
	

	public void shipOrders (Winehouse shop) {
		shop.shipWines(this);
	}
	public void addWines(Wine newWine, Integer quantity, Integer year, Winehouse shop) {
		shop.addWine(this, newWine, year, quantity);
	}
	public void addWines(Wine newWine, Integer year, Winehouse shop) {
		shop.addWine(this, newWine, year);
	}
}
