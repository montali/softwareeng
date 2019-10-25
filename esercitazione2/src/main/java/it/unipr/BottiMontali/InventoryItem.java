package it.unipr.BottiMontali;
import java.util.HashMap;
import java.util.Map;

public class InventoryItem {
	
	private HashMap<Integer,Integer> year;

	public InventoryItem() {
		this.year = new HashMap<Integer,Integer>();
	}
	
	public InventoryItem(Integer year, Integer quantity) {
		this.year = new HashMap<Integer,Integer>();
		this.year.put(Integer.valueOf(year),Integer.valueOf(quantity));
	}
	
	public InventoryItem(HashMap<Integer,Integer> year) {
		this.year = new HashMap<Integer,Integer>(year);
	}
	
	public Integer getQuantityForYear(final Integer year) {
		return this.year.get(year);
	}
	
	public boolean sumQuantity(Integer year, Integer toSum) {
		if (! this.year.containsKey(year)) {
			this.year.put(year, 0);
		}
		if((this.year.get(year)+toSum)<0) {
			return false;
		}
		this.year.put(year, toSum + this.year.get(year));
		return true;
	}
	@Override
	public String toString() {
		String returnable = "Quantities:\n";
		for (Map.Entry<Integer, Integer> inventory: this.year.entrySet()) {
			returnable += (inventory.getKey()+": "+inventory.getValue())+"\n";
		}
		return returnable;
	}
}
