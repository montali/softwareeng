import java.io.Serializable;

/**
 * Headquarter describes the workplaces that are managed by the server .
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class Headquarter implements Serializable {
    private String name;
    private String address;

    /**
     * This constructor is the empty constructor
     */
    
    public Headquarter() {
    }
    
    /**
     * This constructor sets the name and the address
     * @param String name
     * @param String address
     */
    
    public Headquarter(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * This method gets the Headquarters' name
     * @return String name
     */
    
    public String getName() {
        return name;
    }

    /**
     * This method sets the Headquarters' name
     * @param String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets the Headquarters' address
     * @return String address
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method sets the Headquarters' address
     * @param String address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * This method print Headquarters' description
     */
    @Override
    public String toString() {
    	String toReturn = this.getName() + " " + this.getAddress() + "\n";
    	return toReturn;
    }
}
