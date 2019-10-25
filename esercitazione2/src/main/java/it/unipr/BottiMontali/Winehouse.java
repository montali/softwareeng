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
		this.sellers = new ArrayList<Seller>(sellers);
		this.members = new ArrayList<User> (members);
		this.orders= new ArrayList<Order>(); //vanno messi nel costruttore?
		this.requestedWines= new ArrayList<Request>();
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
	
	public void setRequestedWines(ArrayList<Request> requestedWines) {
		Collections.copy(this.requestedWines, requestedWines);
	}

	public ArrayList<Request> getRequestedWines(){
		return this.requestedWines;
	}
	public void setMembers(ArrayList<User> members) {
		Collections.copy(this.members, members);
	}
	
	public ArrayList<Order> getOrders() {
		return this.orders;
	}
	
	public Person login (String username, String password){
		// First, we check if the person is a normal user (more frequent)
		for (User user: this.members) {
			if (username.equals(user.getUsername())) {
				if (user.checkLogin(password)){
					return user;
				}
			}
		}
		// If not, let's check if it's a seller
		for (Seller seller: this.sellers) {
			if (username.equals(seller.getUsername())) {
				if (seller.checkLogin(password)){
					return seller;
				}
			}
		}
		return null;
	}

	public void signUp (String username, String password) {
		for (User user: this.members) {
			if (username.equals(user.getUsername())) {
				System.out.println("Utente gia' esistente");
				return;
			}
		}
		this.members.add(new User(username, password));
	}

	//CERCO VINI PER NOME
	public HashMap<Wine,InventoryItem> findWinesName(Person authorizer, String toSearch){
		if (!(this.members.contains(authorizer)||this.sellers.contains(authorizer))) {
			return null;
		}
		HashMap<Wine,InventoryItem> searched = new HashMap<Wine,InventoryItem>();
		for(Map.Entry<Wine,InventoryItem> temp : this.wines.entrySet()) {
			if(toSearch.equals(temp.getKey().getName())) {
				searched.put(temp.getKey(), temp.getValue());
			}
		}
		return searched;
	}
	
	//CERCO VINI PER ANNO
	public HashMap<Wine,InventoryItem> findWinesYear(User authorizer, Integer toSearch){
		if(!this.members.contains(authorizer)) {
			return null;
		}
		HashMap<Wine,InventoryItem> searched = new HashMap<Wine,InventoryItem>();
		for(Map.Entry<Wine,InventoryItem> temp : this.wines.entrySet()) {
			if(temp.getValue().getQuantityForYear(toSearch)>0) {
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
			if(toRequest.equals(temp.getKey().getName())) {
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
					System.out.println("Succesfully shipped wine: " + tempOrder.getOrderedWine().getName());
				} 
				else {
					System.out.println("Error with order: "+ tempOrder.getOrderedWine().getName()+"; Refill the warehouse first.");
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
			if (requester.equals(tempOrder.getOrderer())){
				orders.add(tempOrder);
			}
		}
		return orders;
	}
	
	public String getWinehouseData(Person loggedIn) {
		//verifico authorizer
		if(!this.sellers.contains(loggedIn)) {
			return "";
		}
		String returnedString;
		returnedString = "Winehouse: "+this.getName()+"\n";
		returnedString += "\nUSERS:\n";
		for (User printingUser: this.getMembers()) {
			returnedString += printingUser.toString();
		}
		returnedString += "\nSELLERS::\n";
		for (Seller printingAdmin: this.getSellers()) {
			returnedString += printingAdmin.toString();
		}
		returnedString += "\nWINES:\n";
		for (Map.Entry<Wine, InventoryItem> wineItem: this.getWines().entrySet()) {
			returnedString += wineItem.getKey().toString() + "\n" + wineItem.getValue().toString();
		}
		returnedString += "\nORDERS:\n";
		for (Order printingOrder: this.getOrders()) {
			returnedString += printingOrder.toString();
		}
		return returnedString;	
	}
	
	public String stringAllWines () {
		String returnedString = "";
		returnedString += "WINES:\n";
		for (Map.Entry<Wine, InventoryItem> wineItem: this.getWines().entrySet()) {
			returnedString += wineItem.getKey().toString() + "\n" + wineItem.getValue().toString();
		}
		return returnedString;
	}
}
