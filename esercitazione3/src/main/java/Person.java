import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
* Person describes the generic employee of the headquarter
* 
* @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
* @author      Simone Montali <simone.montali1@studenti.unipr.it>
* 
* @version     1.0
* @since       1.0
*/

public class Person implements Serializable {
    private String name;
    private String surname;
    private String vat_number;

    private Headquarter headquarter;

    private Date startDate;
    private Date endDate;

    /**
     * This constructor sets all the Person's parameters and work only if the fiscal code has 16 characters
     * 
     * @param String      name
     * @param String      surname
     * @param String      vat_number
     * @param Headquarter headquarter
     * @param Date        startDate
     * @param Date        endDate
     * 
     * @throws InvalidVATException
     */
    
    public Person(String name, String surname, String vat_number, Headquarter headquarter, Date startDate, Date endDate) throws InvalidVATException {
        this.name = name;
        this.surname = surname;
        if (vat_number.length()!=16)
            throw new InvalidVATException();
        this.vat_number = vat_number;
        this.headquarter = headquarter;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * This constructor sets all the Person's parameters if the person is still working in the headquarter
     * It works only if the fiscal code has 16 characters
     * 
     * @param String      name
     * @param String      surname
     * @param String      vat_number
     * @param Headquarter headquarter
     * @param Date        startDate
     * 
     * @throws InvalidVATException
     */
    
    public Person(String name, String surname, String vat_number, Headquarter headquarter, Date startDate) throws InvalidVATException {
        this.name = name;
        this.surname = surname;
        this.vat_number = vat_number;
        if (vat_number.length()!=16)
            throw new InvalidVATException();
        this.headquarter = headquarter;
        this.startDate = startDate;
    }

    /**
     * This is the empty construtor
     */
    
    public Person() {
    }

    /**
     * This is the copy construtor
     *
     * @param Person toCopy
     */

    public Person(Person toCopy) throws InvalidVATException{
        this.name = toCopy.name;
        this.surname = toCopy.surname;
        if (toCopy.vat_number.length()!=16)
            throw new InvalidVATException();
        this.vat_number = toCopy.vat_number;
        this.headquarter = toCopy.headquarter;
        this.startDate = toCopy.startDate;
        this.endDate = toCopy.endDate;
    }

    /**
     * This method gets the Person's name
     * 
     * @return String name
     */
    
    public String getName() {
        return name;
    }

    /**
     * This method sets the Person's name
     * 
     * @param String name
     */
    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets the Person's surname
     * 
     * @return String surname
     */
    
    public String getSurname() {
        return surname;
    }

    /**
     * This method sets the Person's surname
     * 
     * @param String surname
     */
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     * This method gets the Person's fiscal code
     * 
     * @return String vat_number
     */
    
    public String getVat_number() {
        return vat_number;
    }

    /**
     * This method sets the Person's fiscal code
     * 
     * @param String vat_number
     */
    
    public void setVat_number(String vat_number) {
        this.vat_number = vat_number;
    }

    /**
     * This method gets the headquarter where the Person works
     * 
     * @return Headquarter headquarter
     */
    
    public Headquarter getHeadquarter() {
        return headquarter;
    }

    /**
     * This method sets the headquarter where the Person works
     * 
     * @param Headquarter headquarter
     */
    
    public void setHeadquarter(Headquarter headquarter) {
        this.headquarter = headquarter;
    }

    /**
     * This method gets the day when the Person has beginning to work in his headquarter
     * 
     * @return Date startDate
     */
    
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method sets the Person's first day of work in his headquarter
     * 
     * @param Date startDate
     */
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method gets the Person's last day of work in his headquarter
     * 
     * @return Calendar endDate
     */
    
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method sets the Person's last day of work in his headquarter
     *  
     * @param Date endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * This method prints a complete Person's description
     */
    
    @Override
    public String toString() {
    	String toReturn = this.getName() + " " + this.getSurname() + " " + this.getVat_number() + " " + this.getHeadquarter() +"\n";
    	return toReturn;
    }
}
