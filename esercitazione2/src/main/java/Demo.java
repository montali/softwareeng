import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class Demo
{
    public static void main( String[] args )
    {
    
    	//Inizializzazione sistema, un utente, un venditore e tre vini
    	Seller Simone = new Seller ("Simone" , "1234");
    	User Filippo = new User ("Filippo" , "456");
    	User Marco = new User ("Marco", "1234");
    	User Luca = new User ("Luca", "3456");
    	Wine Lambrusco = new Wine("Lambrusco" , "Buono per i pasti" , "VignetoParma");
    	Wine Spumante = new Wine("Spumante" , "Eccezionale per i brindisi" , "VignetoParigi");
    	Wine Rosato = new Wine ("Rosato" , "Perfetto per l'aperitivo" , "VignetoReggio");
    	HashMap<Wine,InventoryItem> Wines = new HashMap<Wine, InventoryItem>();
    	Wines.put(Lambrusco, new InventoryItem(1998,4));
    	Wines.put(Spumante, new InventoryItem(2005,1));
    	Wines.put(Rosato,new InventoryItem(2010,2));
    	ArrayList<Seller> Sellers = new ArrayList<Seller> ();
    	Sellers.add(Simone);
    	ArrayList<User> Users = new ArrayList<User>();
    	Users.add(Filippo);
    	Users.add(Marco);
    	Users.add(Luca);
    	Winehouse CantinaUnipr = new Winehouse("CantinaUnipr", Wines, Sellers, Users);
    	System.out.println(CantinaUnipr.getWinehouseData(Simone));
    	
    	//Utente UX si registra e acquista alcune bottiglie di un vino UX
    	CantinaUnipr.login(Filippo.getUsername(), Filippo.getPassword());
    	Filippo.orderWine(Lambrusco, CantinaUnipr, 1998, 2);
    	Simone.shipOrders(CantinaUnipr);
    	System.out.println(CantinaUnipr.getWinehouseData(Simone));
    	
    	//Utente UY si registra e acquista tutte le bottiglie di un vino UY
    	CantinaUnipr.login(Marco.getUsername(), Marco.getPassword());
    	Marco.orderWine(Spumante, CantinaUnipr, 2005, 1);
    	Simone.shipOrders(CantinaUnipr);
    	System.out.println(CantinaUnipr.getWinehouseData(Simone));
    	
    	//Utente UZ si registra e vuole acquistare vino UY
    	CantinaUnipr.login(Luca.getUsername(), Luca.getPassword());
    	Luca.orderWine(Spumante, CantinaUnipr, 2005, 1);
    	Simone.shipOrders(CantinaUnipr);
    	
    	//Impiegato aggiunge vino Uy
    	Simone.addWines(Spumante, 2, 2005, CantinaUnipr);
    	Simone.shipOrders(CantinaUnipr);
    
    }
}
