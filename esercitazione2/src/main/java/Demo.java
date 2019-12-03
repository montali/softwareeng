import java.util.ArrayList;
import java.util.HashMap;


public class Demo
{
    public static void main( String[] args )
    {
    
    	//Inizializzazione sistema, un utente, un venditore e tre vini
    	Seller simone = new Seller ("Simone" , "1234");
    	User filippo = new User ("Filippo" , "456");
    	User marco = new User ("Marco", "1234");
    	User luca = new User ("Luca", "3456");
    	Wine lambrusco = new Wine("Lambrusco" , "Buono per i pasti" , "VignetoParma");
    	Wine spumante = new Wine("Spumante" , "Eccezionale per i brindisi" , "VignetoParigi");
    	Wine rosato = new Wine ("Rosato" , "Perfetto per l'aperitivo" , "VignetoReggio");
    	HashMap<Wine,InventoryItem> wines = new HashMap<Wine, InventoryItem>();
    	wines.put(lambrusco, new InventoryItem(1998,4));
    	wines.put(spumante, new InventoryItem(2005,1));
    	wines.put(rosato,new InventoryItem(2010,2));
    	ArrayList<Seller> sellers = new ArrayList<Seller> ();
    	sellers.add(simone);
    	ArrayList<User> users = new ArrayList<User>();
    	users.add(filippo);
    	users.add(marco);
    	users.add(luca);
    	Winehouse cantinaUnipr = new Winehouse("CantinaUnipr", wines, sellers, users);
    	System.out.println(cantinaUnipr.getWinehouseData(simone));
    	
    	//Utente UX si registra e acquista alcune bottiglie di un vino UX
    	cantinaUnipr.login(filippo.getUsername(), filippo.getPassword());
    	filippo.orderWine(lambrusco, cantinaUnipr, 1998, 2);
    	simone.shipOrders(cantinaUnipr);
    	System.out.println(cantinaUnipr.getWinehouseData(simone));
    	
    	//Utente UY si registra e acquista tutte le bottiglie di un vino UY
    	cantinaUnipr.login(marco.getUsername(), marco.getPassword());
    	marco.orderWine(spumante, cantinaUnipr, 2005, 1);
    	simone.shipOrders(cantinaUnipr);
    	System.out.println(cantinaUnipr.getWinehouseData(simone));
    	
    	//Utente UZ si registra e vuole acquistare vino UY
    	cantinaUnipr.login(luca.getUsername(), luca.getPassword());
    	luca.orderWine(spumante, cantinaUnipr, 2005, 1);
    	luca.requestWine(spumante.getName(),cantinaUnipr);
    	simone.shipOrders(cantinaUnipr);
    	
    	//Impiegato aggiunge vino Uy
    	simone.addWines(spumante, 2, 2005, cantinaUnipr);
    	simone.shipOrders(cantinaUnipr);
    
    }
}
