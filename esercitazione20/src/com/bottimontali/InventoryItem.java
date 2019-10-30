package com.bottimontali;
import java.util.HashMap;
import java.util.Map;

/**
 * InventoryItem describes the inventory entries for a single wine.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class InventoryItem {
	
	private HashMap<Integer,Integer> year;

	/**
	 * This constructor generates an empty inventory.
	 */
	public InventoryItem() {
		this.year = new HashMap<Integer,Integer>();
	}
	
	/**
	 * This constructor generates an inventory item with just a year possessed.
	 * 
	 * @param year
	 * @param quantity
	 */
	public InventoryItem(Integer year, Integer quantity) {
		this.year = new HashMap<Integer,Integer>();
		this.year.put(Integer.valueOf(year),Integer.valueOf(quantity));
	}
	
	/**
	 * This constructor generates the InventoryItem from a HashMap.
	 * 
	 * @param year
	 */
	public InventoryItem(HashMap<Integer,Integer> year) {
		this.year = new HashMap<Integer,Integer>(year);
	}
	
	/**
	 * This method returns a quantity for a specific year.
	 * 
	 * @param year
	 * 
	 * @return the quantity
	 */
	public Integer getQuantityForYear(final Integer year) {
		return this.year.get(year);
	}
	
	/**
	 * This method adds/removes a quantity from the inventory.
	 * 
	 * @param year
	 * @param toSum
	 * 
	 * @return whether the sum was ok
	 */
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
	/**
	 * This method generates a string that describes the inventory for a wine.
	 */
	@Override
	public String toString() {
		String returnable = "Quantities:\n";
		for (Map.Entry<Integer, Integer> inventory: this.year.entrySet()) {
			returnable += (inventory.getKey()+": "+inventory.getValue())+"\n";
		}
		return returnable;
	}
}
