package it.unipr.BottiMontali;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class User extends Person{

	public User () {
	}
	public User (String username, String password) {
		super(username, password);
	}
	public User(Person user){
		super(user.getUsername(), user.getPassword());
	}

	public void notifyWineAdded () {
		System.out.println("Un vino richiesto da "+ this.getUsername() + " e' stato aggiunto.");
	}

	public void orderWine (final Wine wantedWine, final Winehouse shop, final Integer year, final Integer quantity){
		shop.orderWine(this, wantedWine, year, quantity);
	}
	public ArrayList<Order> getMyOrders (final Winehouse shop) {
		return shop.getOrdersForUser(this);
	}
	public Map.Entry<Wine,InventoryItem> getWineByName (String name, Winehouse shop) {
		return shop.findWinesName(this, name);
	}
	public HashMap<Wine,InventoryItem> getWinesByYear (Integer year, Winehouse shop) {
		return shop.findWinesYear(this, year);
	}
}
