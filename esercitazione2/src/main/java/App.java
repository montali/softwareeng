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
    
        Seller Simone = new Seller ("Simone" , "1234");
    	User Filippo = new User ("Filippo" , "456");
    	Wine Lambrusco = new Wine("Lambrusco" , "Buono per i pasti" , "VignetoParma");
    	Wine Spumante = new Wine("Spumante" , "Eccezionale per i brindisi" , "VignetoParigi");
    	Wine Rosato = new Wine ("Rosato" , "Perfetto per l'aperitivo" , "VignetoReggio");
    	HashMap<Wine,InventoryItem> Wines = new HashMap<Wine, InventoryItem>();
    	Wines.put(Lambrusco, new InventoryItem(1998,4));
    	Wines.put(Spumante, new InventoryItem(2005,1));
    	Wines.get(Lambrusco).sumQuantity(2002, 4);
    	Wines.put(Rosato,new InventoryItem(2010,2));
    	ArrayList<Seller> Sellers = new ArrayList<Seller> ();
    	Sellers.add(Simone);
    	ArrayList<User> Users = new ArrayList<User>();
    	Users.add(Filippo);
    	Winehouse CantinaUnipr = new Winehouse("CantinaUnipr", Wines, Sellers, Users);
     
        Login.loginPage(CantinaUnipr);
     
    }
}
