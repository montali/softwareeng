package it.unipr.BottiMontali;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       Seller simmi = new Seller("simmontali", "password");
       User bull = new User("bullbo", "passerinaLover98");
       Wine vinello = new Wine("Lambrusco", "Buonissimo con le lasagne", "BottiVini");
       Wine vinelloski = new Wine("Passerina", "Buonissimo con la salsiccia", "MontaliVini");
       Wine vinelleschi = new Wine("Soncino", "Buonissimo con la verdura", "ReggioVini");
       HashMap<Wine, InventoryItem> wines = new HashMap<Wine, InventoryItem>();
       wines.put(vinello, new InventoryItem(1990,5));
       wines.put(vinello, new InventoryItem(1991,2));
       wines.put(vinelloski, new InventoryItem(2000,3));
       ArrayList<Seller> sellers = new ArrayList<Seller> ();
       sellers.add(simmi);
       ArrayList<User> users = new ArrayList<User>();
       users.add(bull);
       Winehouse popa = new Winehouse("Popina", wines,sellers, users );
       //simmi.addWines(vinelleschi, 1900, popa);
       Login.loginPage(popa);
    }
}
