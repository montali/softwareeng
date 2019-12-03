import java.util.Date;

/**
 * Functionary describes the functionary of the headquarter
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class Functionary extends Person {
    private String username;
    private String password;

    /**
     * This constructor is the empty constructor  
     */
    
    public Functionary() {
    }

    /**
     * This constructor sets all the Functionary's parameters
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
    
    public Functionary(String name, String surname, String vat_number, Headquarter headquarter, Date startDate, Date endDate, String username, String password) throws InvalidVATException {
        super(name, surname, vat_number, headquarter, startDate, endDate);
        this.username = username;
        this.password = password;
    }

    /**
     * This constructor sets all the Functionary's parameters if the Functionary is still working in the headquarter
     * @param String      name
     * @param String      surname
     * @param String      vat_number
     * @param Headquarter headquarter
     * @param Date        startDate
     * @param String      username
     * @param String      password
     * 
     * @throws InvalidVATException
     */
    
    public Functionary(String name, String surname, String vat_number, Headquarter headquarter, Date startDate, String username, String password) throws InvalidVATException {
        super(name, surname, vat_number, headquarter, startDate);
        this.username = username;
        this.password = password;
    }
    /**
     * This constructor generates a functionary from a non-user Person
     * @param Person personData
     * @param String username
     * @param String password
     */
    public Functionary(Person personData, String username, String password) throws InvalidVATException{
        super(personData);
        this.username = username;
        this.password = password;
    }

    /**
     * This constructor sets Functionary's username and password
     * @param String username
     * @param String password
     */
    
    public Functionary(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * This method gets the Functionary's username
     * @return
     */
    
    public String getUsername() {
        return username;
    }

    /**
     * This method sets the Functionarys username 
     * @param String username
     */
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * This method gets the Functionary's password
     * @return String password
     */
    
    public String getPassword() {
        return password;
    }
    
    /**
     * This method sets the Functionary's password
     * @param String password
     */
    
    public void setPassword(String password) {
        this.password = password;
    }
}
