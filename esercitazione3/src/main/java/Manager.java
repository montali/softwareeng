import java.util.Date;


public class Manager extends Functionary {
	
	/**
	 * This constructor is the empty constructor
	 */
	
    public Manager() {
    }

    /**
     * This constructor sets all the Manager's parameters
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
    
    public Manager(String name, String surname, String vat_number, Headquarter headquarter, Date startDate, Date endDate, String username, String password) throws InvalidVATException {
        super(name, surname, vat_number, headquarter, startDate, endDate, username, password);
    }

    /**
     * This constructor generates a manager from a non-user Person
     * @param Person personData
     * @param String username
     * @param String password
     */
    public Manager(Person personData, String username, String password) throws InvalidVATException {
        super(personData, username, password);
    }

    /**
     * This constructor sets all the Manager's parameters if the manager is still working in the headquarter
     * @param String      name
     * @param String      surname
     * @param String      vat_number
     * @param Headquarter headquarter
     * @param Date    startDate
     * @param String      username
     * @param String      password
     * 
     * @throws InvalidVATException
     */
    
    public Manager(String name, String surname, String vat_number, Headquarter headquarter, Date startDate, String username, String password) throws InvalidVATException {
        super(name, surname, vat_number, headquarter, startDate, username, password);
    }

    /**
     * This constructor sets Manager's username and password
     * 
     * @param String username
     * @param String password
     */
    public Manager(String username, String password) {
        super(username, password);
    }
}
