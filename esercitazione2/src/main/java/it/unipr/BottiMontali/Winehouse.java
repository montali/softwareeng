package it.unipr.BottiMontali;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Winehouse {
	
	private String name;
	private ArrayList<Wine> wines;
	private ArrayList<Seller> sellers;
	private ArrayList<User> members;
	private ArrayList<Wine> soldWines;
	private ArrayList<Wine> request;
	
	public Winehouse() {
		this.name = "";
		this.wines = new ArrayList<Wine>();
		this.sellers = new ArrayList<Seller>();
		this.members = new ArrayList<User>();
		this.soldWines= new ArrayList<Wine>(); //vanno messi nel costruttore?
		this.request= new ArrayList<Wine>();
	}
	
	public Winehouse(final String name, final ArrayList<Wine> wines, final ArrayList<Seller> sellers, final ArrayList<User> members) {
		this.name = name;
		Collections.copy(this.wines,wines);
		Collections.copy(this.sellers, sellers);
		Collections.copy(this.members,members);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Wine> getWines(){
		return this.wines;
	}
	
	public void setWines(ArrayList<Wine> wines) {
		Collections.copy(this.wines, wines);
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
	
	//VERIFICO SE UN VINO E' IN LISTA
	public int isThere(Wine toSearch) {
		int index = 0;
		for(Wine temp : this.wines) {
			if(temp == toSearch) {
				return index;
			}
			index++;
		}
		return (-1);
	}
	
	//CERCO VINI PER NOME
	public ArrayList<Wine> foundWinesName(String toSearch){
		ArrayList<Wine> searched = new ArrayList<Wine>();
		for(Wine temp : this.wines) {
			if(temp.getName() == toSearch) {
				searched.add(temp);
			}
		}
		return searched;
	}
	
	//CERCO VINI PER ANNO
	public ArrayList<Wine> foundWinesYear(int toSearch){
		ArrayList<Wine> searched = new ArrayList<Wine>();
		for(Wine temp : this.wines) {
			if(temp.getYear() == toSearch) {
				searched.add(temp);
			}
		}
		return searched;
	}
	
	//AGGIUNGO VINI (DAL SELLER)
	public void addWine(Seller authorizer, Wine toAdd) {
		//guardo se authorizer è un seller
		if(!this.sellers.contains(authorizer)) {
			return;
		}
		int index = this.isThere(toAdd);
		if(index == -1) {
			this.wines.add(toAdd);
			//notificarlo alle richieste
		}
		else {
			toAdd.setQuantity(this.wines.get(index).getQuantity()+ toAdd.getQuantity());
			this.wines.set(index, toAdd);
		}
	}
	
	//RIMUOVO VINI (DAL SELLER)
	public void removeWine(Seller authorizer, Wine toRemove) {
		//verifico authorizer
		if(!this.sellers.contains(authorizer)) {
			return;
		}
		if(!this.wines.contains(toRemove)) {
			return;
		}
		//guardo quanti vini sono rimasti, se uno tolgo il vino
		int index = this.isThere(toRemove);
		if(this.wines.get(index).getQuantity()>1) {
			int quantity = this.wines.get(index).getQuantity()-1;
			this.wines.get(index).setQuantity(quantity);
		}
		else {
			this.wines.remove(index);
		}
	}
	//aggiungo vino alle richieste se non disponibile
	public void isRequested(Wine toRequest) {
		if(this.wines.contains(toRequest)) {
			return;
		}
		this.request.add(toRequest);
	}
	
}
