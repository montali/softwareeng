package it.unipr.BottiMontali;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Winehouse {
	
	private String name;
	private HashMap<Wine,InventoryItem> wines;
	private ArrayList<Seller> sellers;
	private ArrayList<User> members;
	private ArrayList<Order> orders;
	private ArrayList<Request> requestedWines;
	
	public Winehouse() {
		this.name = "";
		this.wines = new HashMap<Wine,InventoryItem>();
		this.sellers = new ArrayList<Seller>();
		this.members = new ArrayList<User>();
		this.orders= new ArrayList<Order>(); //vanno messi nel costruttore?
		this.requestedWines= new ArrayList<Request>();
	}
	
	public Winehouse(final String name, final HashMap<Wine,InventoryItem> wines, final ArrayList<Seller> sellers, final ArrayList<User> members) {
		this.name = name;
		this.wines = new HashMap<Wine,InventoryItem>(wines);
		Collections.copy(this.sellers, sellers);
		Collections.copy(this.members,members);
		//costruttori vuoti
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public HashMap<Wine,InventoryItem> getWines(){
		return this.wines;
	}
	
	public void setWines(HashMap<Wine,InventoryItem> wines) {
		this.wines = new HashMap<Wine,InventoryItem>(wines);
	}
	
	public ArrayList<Seller> getSellers(){
		return this.sellers;
	}
	
	public void setSellers(ArrayList<Seller> Sellers) {
		Collections.copy(this.sellers, sellers);
	}

	public ArrayList<User> getMembers(){
		return this.members;
	}
	
	public void setMembers(ArrayList<User> members) {
		Collections.copy(this.members, members);
	}
	
	public ArrayList<Order> getOrders() {
		return this.orders;
	}
	
	public User login (String username, String password){
		for (User user: this.members) {
			if (user.getUsername() == username) {
				if (user.checkLogin(password)){
					return user;
				}
			}
		}
		return null;
	}

	public void signUp (String username, String password) {
		for (User user: this.members) {
			if (user.getUsername() == username) {
				System.out.println("Utente gia' esistente");
				return;
			}
		}
		this.members.add(new User(username, password));
	}

	//CERCO VINI PER NOME
	public Map.Entry<Wine,InventoryItem> findWinesName(User authorizer, String toSearch){
		if(!this.members.contains(authorizer)) {
			return null;
		}
		for(Map.Entry<Wine,InventoryItem> temp : this.wines.entrySet()) {
			if(temp.getKey().getName() == toSearch) {
				return temp;
			}
		}
		return null;
	}
	
	//CERCO VINI PER ANNO
	public HashMap<Wine,InventoryItem> findWinesYear(User authorizer, Integer toSearch){
		if(!this.members.contains(authorizer)) {
			return null;
		}
		HashMap<Wine,InventoryItem> searched = new HashMap<Wine,InventoryItem>();
		for(Map.Entry<Wine,InventoryItem> temp : this.wines.entrySet()) {
			if(temp.getValue().getQuantity(toSearch)>0) {
				searched.put(temp.getKey(),temp.getValue());
			}
		}
		return null;
	}
	
	//AGGIUNGO VINI (DAL SELLER)
	public void addWine(Seller authorizer, Wine toAdd, Integer year) {
		//guardo se authorizer è un seller
		if(!this.sellers.contains(authorizer)) {
			return;
		}
		if(this.wines.containsKey(toAdd)) {
			this.wines.get(toAdd).sumQuantity(year, 1);
		}
		else {
			this.wines.put(toAdd,new InventoryItem(year,Integer.valueOf(1)));
		}
		for (Request temp: this.requestedWines) {
			if (temp.checkIfRequested(toAdd.getName())){
				this.requestedWines.remove(temp);
			}
		}
		System.out.println("Vino aggiunto con successo: " + toAdd.getName() + " del " + year);
	}
		//AGGIUNGO VINI (DAL SELLER)
	public void addWine(Seller authorizer, Wine toAdd, Integer year, Integer quantity) {
		//guardo se authorizer è un seller
		if(!this.sellers.contains(authorizer)) {
			return;
		}
		if(this.wines.containsKey(toAdd)) {
			this.wines.get(toAdd).sumQuantity(year, quantity);
		}
		else {
			this.wines.put(toAdd,new InventoryItem(year,quantity));
		}
		for (Request temp: this.requestedWines) {
			if (temp.checkIfRequested(toAdd.getName())){
				this.requestedWines.remove(temp);
			}
		}
		System.out.println("Vino aggiunto con successo: " + toAdd.getName() + " del " + year);
	}
	//RIMUOVO VINI (DAL SELLER)
	public boolean removeWine(Seller authorizer, Wine toRemove, Integer year) {
		//verifico authorizer
		if(this.wines.containsKey(toRemove) && this.sellers.contains(authorizer)) {
			return (this.wines.get(toRemove).sumQuantity(year, -1));
		}
		return false;
	}
	//RIMUOVO VINI (DAL SELLER)
	public boolean removeWine(Seller authorizer, Wine toRemove, Integer year, Integer quantity) {
		//verifico authorizer
		if(this.wines.containsKey(toRemove) && this.sellers.contains(authorizer)) {
			return (this.wines.get(toRemove).sumQuantity(year, quantity*-1));
		}
		return false;
	}
	//aggiungo vino alle richieste se non disponibile
	public void requestWine(User requester, String toRequest) {
		for(Map.Entry<Wine,InventoryItem> temp : this.wines.entrySet()) {
			if(temp.getKey().getName() == toRequest) {
				return;
			}
		}
		this.requestedWines.add(new Request(requester, toRequest));
	}

	public void orderWine (User orderer, Wine wantedWine, Integer year, Integer quantity){
		this.orders.add(new Order(orderer, wantedWine, year, quantity, false));
	}

	public void shipWines (Seller authorizer) {
		for (Order tempOrder: this.orders){
			if (!tempOrder.isShipped()){
				if (removeWine(authorizer, tempOrder.getOrderedWine(), tempOrder.getYear(), tempOrder.getQuantity())) {
					tempOrder.setShipped(true);
					System.out.println("Vino spedito con successo: " + tempOrder.getOrderedWine().getName());
				} 
				else {
					System.out.println("C'e' stato un errore con l'ordine: "+ tempOrder.getOrderedWine().getName()+"; Riempi il magazzino.");
				}
			}
		}
	}

	public ArrayList<Order> getOrdersForUser (User requester) {
		if(!this.members.contains(requester)) {
			return null;
		}
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order tempOrder: this.orders) {
			if (tempOrder.getOrderer() == requester){
				orders.add(tempOrder);
			}
		}
		return orders;
	}
}
