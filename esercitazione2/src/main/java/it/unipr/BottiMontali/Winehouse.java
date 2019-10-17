package it.unipr.BottiMontali;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class Winehouse {
	
	private String name;
	private HashMap<Wine,InventoryItem> wines;
	private ArrayList<Seller> sellers;
	private ArrayList<User> members;
	private ArrayList<Wine> soldWines;
	private ArrayList<Wine> request;
	
	public Winehouse() {
		this.name = "";
		this.wines = new HashMap<Wine,InventoryItem>();
		this.sellers = new ArrayList<Seller>();
		this.members = new ArrayList<User>();
		this.soldWines= new ArrayList<Wine>(); //vanno messi nel costruttore?
		this.request= new ArrayList<Wine>();
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
	
	public ArrayList<Wine> getSoldWines() {
		return this.soldWines;
	}
	
	//CERCO VINI PER NOME
	public Map.Entry<Wine,InventoryItem> foundWinesName(String toSearch){
		for(Map.Entry<Wine,InventoryItem> temp : this.wines.entrySet()) {
			if(temp.getKey().getName() == toSearch) {
				return temp;
			}
		}
		return null;
	}
	
	//CERCO VINI PER ANNO
	public HashMap<Wine,InventoryItem> foundWinesYear(Integer toSearch){
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
		System.out.println("Vino aggiunto con successo: " + toAdd.getName() + " del " + year);
	}
	
	//RIMUOVO VINI (DAL SELLER)
	public void removeWine(Seller authorizer, Wine toRemove, Integer year) {
		//verifico authorizer
		if(this.wines.containsKey(toRemove) && this.sellers.contains(authorizer)) {
			if(this.wines.get(toRemove).sumQuantity(year, -1)) {
				System.out.println("Vino rimosso con successo: " + toRemove.getName()+ " del " + year);
			}
			else {
				System.out.println("Errore rimozione vino: controlla i parametri");
			}
		}
		return;
	}
	//aggiungo vino alle richieste se non disponibile
	public void findRequested(Wine toRequest) {
		if(this.wines.contains(toRequest)) {
			return;
		}
		this.request.add(toRequest);
	}
	
}
