package it.unipr.BottiMontali;
import java.util.HashMap;

public class InventoryItem {
	
	private HashMap<Integer,Integer> year;
	
	public InventoryItem() {
		this.year = new HashMap<Integer,Integer>();
	}
	
	public InventoryItem(Integer year, Integer quantity) {
		this.year = new HashMap<Integer,Integer>(Integer.valueOf(year),Integer.valueOf(quantity));
	}
	
	public InventoryItem(HashMap<Integer,Integer> year) {
		this.year = new HashMap<Integer,Integer>(year);
	}
	
	public Integer getQuantity(final Integer year) {
		return this.year.get(year);
	}
	
	public boolean sumQuantity(Integer year, Integer toSum) {
		if((this.year.get(year)+toSum)<0) {
			return false;
		}
		this.year.put(year, toSum + this.year.get(year));
		return true;
	}
}
