import java.util.*;

/**
 * Winehouse describes a winehouse and its properties.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class Winehouse {
	
	private String name;
	private HashMap<Wine,InventoryItem> wines;
	private ArrayList<Seller> sellers;
	private ArrayList<User> members;
	private ArrayList<Order> orders;
	private ArrayList<Request> requestedWines;
	
	/**
	 * This constructor generates an empty winehouse
	 */
	public Winehouse() {
		this.name = "";
		this.wines = new HashMap<Wine,InventoryItem>();
		this.sellers = new ArrayList<Seller>();
		this.members = new ArrayList<User>();
		this.orders= new ArrayList<Order>(); 
		this.requestedWines= new ArrayList<Request>();
	}
	
	/**
	 * This constructor generates a winehouse from its properties
	 * 
	 * @param name
	 * @param wines
	 * @param sellers
	 * @param members
	 */
	public Winehouse(final String name, final HashMap<Wine,InventoryItem> wines, final ArrayList<Seller> sellers, final ArrayList<User> members) {
		this.name = name;
		this.wines = new HashMap<Wine,InventoryItem>(wines);
		this.sellers = new ArrayList<Seller>(sellers);
		this.members = new ArrayList<User> (members);
		this.orders= new ArrayList<Order>(); 
		this.requestedWines= new ArrayList<Request>();
		//costruttori vuoti
	}
	
	/**
	 * This method gets the winehouse's name
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This method sets the winehouse's name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method gets the winehouse's wines
	 * 
	 * @return the wines
	 */
	public HashMap<Wine,InventoryItem> getWines(){
		return this.wines;
	}
	
	/**
	 * This method sets the winehouse's wines
	 * 
	 * @param wines
	 */
	public void setWines(HashMap<Wine,InventoryItem> wines) {
		this.wines = new HashMap<Wine,InventoryItem>(wines);
	}
	
	/**
	 * This method gets the winehouse's sellers
	 * 
	 * @return the sellers
	 */
	public ArrayList<Seller> getSellers(){
		return this.sellers;
	}
	
	/**
	 * This method sets the winehouse's sellers
	 * 
	 * @param Sellers
	 */
	public void setSellers(ArrayList<Seller> Sellers) {
		Collections.copy(this.sellers, sellers);
	}

	/**
	 * This method gets the winehouse's members
	 * 
	 * @return the members
	 */
	public ArrayList<User> getMembers(){
		return this.members;
	}
	
	/**
	 * This method sets the winehouse's requested wines
	 * 
	 * @param requestedWines
	 */
	public void setRequestedWines(ArrayList<Request> requestedWines) {
		Collections.copy(this.requestedWines, requestedWines);
	}

	/**
	 * This method gets the winehouse's requested wines
	 * 
	 * @return the requests
	 */
	public ArrayList<Request> getRequestedWines(){
		return this.requestedWines;
	}
	/**
	 * This method sets the winehouse's members
	 * 
	 * @param members
	 */
	public void setMembers(ArrayList<User> members) {
		Collections.copy(this.members, members);
	}
	
	/**
	 * This method gets the winehouse's orders
	 * 
	 * @return the orders
	 */
	public ArrayList<Order> getOrders() {
		return this.orders;
	}
	
	/**
	 * This method logs a person in the winehouse
	 * 
	 * @param username
	 * @param password
	 * @return the person object
	 */
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

	/**
	 * This method registers a new user to the winehouse
	 * 
	 * @param username
	 * @param password
	 */
	public void signUp (String username, String password) {
		for (User user: this.members) {
			if (username.equals(user.getUsername())) {
				System.out.println("Utente gia' esistente");
				return;
			}
		}
		this.members.add(new User(username, password));
	}

	/**
	 * This method looks for wines in the DB by their name
	 * 
	 * @param authorizer
	 * @param toSearch
	 * @return the results
	 */
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
	
	/**
	 * This method looks for wines in the DB by their year
	 * 
	 * @param authorizer
	 * @param toSearch
	 * @return the results
	 */
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
	
	/**
	 * This method adds a new single wine to the database
	 * 
	 * @param authorizer
	 * @param toAdd
	 * @param year
	 */
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
	/**
	 * This method adds multiple wines to the DB
	 * 
	 * @param authorizer
	 * @param toAdd
	 * @param year
	 * @param quantity
	 */
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
		// We can't use a foreach loop to remove elements, so let's use an iterator instead
		Iterator<Request> it = this.requestedWines.iterator();
		while(it.hasNext()){
			Request temp = it.next();
			if (temp.checkIfRequested(toAdd.getName())){
				it.remove();
			}
		}

		System.out.println("Vino aggiunto con successo: " + toAdd.getName() + " del " + year);
	}
	/**
	 * This method removes a single wine
	 * 
	 * @param authorizer
	 * @param toRemove
	 * @param year
	 * @return
	 */
	public boolean removeWine(Seller authorizer, Wine toRemove, Integer year) {
		//verifico authorizer
		if(this.wines.containsKey(toRemove) && this.sellers.contains(authorizer)) {
			return (this.wines.get(toRemove).sumQuantity(year, -1));
		}
		return false;
	}
	/**
	 * This method removes multiple wines
	 * 
	 * @param authorizer
	 * @param toRemove
	 * @param year
	 * @param quantity
	 * @return
	 */
	public boolean removeWine(Seller authorizer, Wine toRemove, Integer year, Integer quantity) {
		//verifico authorizer
		if(this.wines.containsKey(toRemove) && this.sellers.contains(authorizer)) {
			return (this.wines.get(toRemove).sumQuantity(year, quantity*-1));
		}
		return false;
	}
	/**
	 * This method adds a wine to the requests
	 * 
	 * @param requester
	 * @param toRequest
	 */
	public void requestWine(User requester, String toRequest) {
		for(Map.Entry<Wine,InventoryItem> temp : this.wines.entrySet()) {
			if(toRequest.equals(temp.getKey().getName())) {
				return;
			}
		}
		this.requestedWines.add(new Request(requester, toRequest));
	}

	/**
	 * This method orders a new wine
	 * 
	 * @param orderer
	 * @param wantedWine
	 * @param year
	 * @param quantity
	 */
	public void orderWine (User orderer, Wine wantedWine, Integer year, Integer quantity){
		this.orders.add(new Order(orderer, wantedWine, year, quantity, false));
	}

	/**
	 * This method ships the ordered wines
	 * 
	 * @param authorizer
	 */
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
	
	

	/**
	 * This method returns the orders a user has made
	 * 
	 * @param requester
	 * @return the orders
	 */
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
	
	/**
	 * This method returns all the winehouse data in a string
	 * 
	 * @param loggedIn
	 * @return the resulting string
	 */
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
	
	/**
	 * This method verify if the login is correct
	 * 
	 * @param username
	 * @param password
	 * @return true if username and password are correct
	 */
	public boolean isRegistered(String username, String password) {
		for(Person temp : this.members) {
			if(temp.getUsername().equals(username) && temp.getPassword().equals(password)) {
				return true;
			}
		}
		for(Person temp : this.sellers) {
			if(temp.getUsername().equals(username) && temp.getPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method returns a string with all the wines in inventory
	 * 
	 * @return the string
	 */
	public String stringAllWines () {
		String returnedString = "";
		returnedString += "WINES:\n";
		for (Map.Entry<Wine, InventoryItem> wineItem: this.getWines().entrySet()) {
			returnedString += wineItem.getKey().toString() + "\n" + wineItem.getValue().toString();
		}
		return returnedString;
	}
	

}
