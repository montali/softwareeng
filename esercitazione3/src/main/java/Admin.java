import java.util.Calendar;
import java.util.Date;

/**
 * Admin describes the admin of the headquarter
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class Admin extends Functionary {
	
	/**
	 * This is the empty constructor
	 */
	
    public Admin() {
    }
    /**
     * This constructor generates an admin from a non-user Person
     * @param Person personData
     * @param String username
     * @param String password
     */
    public Admin(Person personData, String username, String password) throws InvalidVATException {
        super(personData, username, password);
    }

    /**
     * This constructor calls Person constructor and sets all the Admin's parameters 
     * 
     * @param String      name
     * @param String      surname
     * @param String      vat_number
     * @param Headquarter headquarter
     * @param Date        startDate
     * @param Date        endDate
     * @param String      username
     * @param String      password
     * 
     * @throws InvalidVATException
     */
    
    public Admin(String name, String surname, String vat_number, Headquarter headquarter, Date startDate, Date endDate, String username, String password) throws InvalidVATException {
        super(name, surname, vat_number, headquarter, startDate, endDate, username, password);
    }

    /**
     * This constructor calls Person constructor and sets all the Admin's parameters if the Admin is still working in the headquarter
     * 
     * @param String      name
     * @param String      surname
     * @param String      vat_number
     * @param Headquarter headquarter
     * @param Date        startDate
     * @param Date        endDate
     * @param String      username
     * @param String      password
     * 
     * @throws InvalidVATException
     */
    
    public Admin(String name, String surname, String vat_number, Headquarter headquarter, Date startDate, String username, String password) throws InvalidVATException {
        super(name, surname, vat_number, headquarter, startDate, username, password);
    }

    /**
     * This constructor sets Admin's username and password
     * @param String username
     * @param String password
     */
    public Admin(String username, String password) {
        super(username, password);
    }
}
