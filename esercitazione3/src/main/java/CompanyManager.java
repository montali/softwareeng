import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * CompanyManager memorized all the headquarters and the employees that are managed by the server
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class CompanyManager {
    private HashMap <String, Headquarter> headquarters;
    private HashMap<String,Person> employees;
    private static CompanyManager instance;


    /**
     * Static instance getter for the singleton pattern.
     */
    public static synchronized CompanyManager getInstance()
    {
        if (instance == null) {
            instance = new CompanyManager();
        }
        return instance;
    }

    /**
     * This is the empty constructor 
     */
    private CompanyManager() {
        this.headquarters = new HashMap<String, Headquarter>();
        this.employees = new HashMap<String, Person>();
    }

    /**
     * This constructor sets all the CompanyManager's parameters
     * @param HashMap<String, Headquarter> headquarters
     * @param HashMap<String, Person>      employees 
     */
    
    private CompanyManager(HashMap<String, Headquarter> headquarters, HashMap<String, Person> employees) {
        this.headquarters = new HashMap<String, Headquarter>(headquarters);
        this.employees = new HashMap<String, Person>(employees);
    }

    /**
     * This method gets the CompanyManagr's headquarters
     * @return HashMap<String, Headquarter> headquarters
     */
    
    public HashMap<String, Headquarter> getHeadquarters() {
        return headquarters;
    }

    /**
     * This method sets the CompanyManager's headquarters
     * @param HashMap<String, Headquarter> headquarters
     */
    
    public void setHeadquarters(HashMap<String, Headquarter> headquarters) {
        this.headquarters = headquarters;
    }

    /**
     * This method gets the CompanyManager's employees
     * @return HashMap<String,Person> employes
     */
    
    public HashMap<String, Person> getEmployees() {
        return employees;
    }

    /**
     * This method gets the CompanyManager's employees
     * @param HashMap<String,Person> employees
     */
    
    public void setEmployees(HashMap<String,Person> employees) {
        this.employees = employees;
    }

    /**
     * This method adds new employ to the employees' HashMap
     * This method works only if the authorizer has an higher level than the newEmployee
     * Also, this method works only if the newEmployee is not already contained in the HashMap of the employees
     * 
     * @param Person authorizer
     * @param Person newEmployee
     * 
     * @return boolean true if it works, false if the parameters don't respect the rules
     * 
     * @throws UnauthorizedUserException
     * @throws InvalidVATException
     */
    
    public boolean addEmployee (Person authorizer, Person newEmployee) throws UnauthorizedUserException, InvalidVATException {
        // A low level employee cannot add higher level users. This would be a big security flaw
        if (!((authorizer instanceof Admin)
                || ((authorizer instanceof Manager)&&!(newEmployee instanceof Admin))
                || ((authorizer instanceof Functionary)&&!(newEmployee instanceof Functionary)))) {
            throw new UnauthorizedUserException();
        }
        if (newEmployee.getVat_number().length()!=16)
            throw new InvalidVATException();
        if (this.employees.containsKey(newEmployee.getVat_number())){
            return false;
        } else {
            this.employees.put(newEmployee.getVat_number(), newEmployee);
            return true;
        }
    }

    /**
     * This method edits an employee from the employees' HashMap
     * It works only if the employee exists in the employees' HashMap
     * Also it works if the authorizer has an higher level than the editetEmployee
     * 
     * @param Person authorizer
     * @param Person editedEmployee
     * 
     * @return boolean if it works, false if the parameters don't respect the rules
     * 
     * @throws UnauthorizedUserException
     */
    
    public boolean editEmployee(Person authorizer, Person editedEmployee) throws UnauthorizedUserException {
        Person toEdit = this.employees.get(editedEmployee.getVat_number());
        if (toEdit == null)
            return false;
        if ((authorizer instanceof Admin)
                || ((authorizer instanceof Manager)&&!(toEdit instanceof Admin))
                || ((authorizer instanceof Functionary)&&!(toEdit instanceof Functionary))){
           this.employees.put(editedEmployee.getVat_number(), editedEmployee);
           return true;
        } else {
            throw new UnauthorizedUserException();
        }
    }

    /**
     * This method returns the Functionary that try to login to the server
     * It verify if the Functionary is in the employees' HashMap
     * 
     * @param String username
     * @param String password
     * @return Functionary login
     */
    
    public Functionary login (String username, String password)
    {
        for (Person iterating: this.employees.values()){
            if (iterating instanceof Functionary){
                if (((Functionary) iterating).getUsername().equals(username)){
                    if(((Functionary) iterating).getPassword().equals(password))
                        return (Functionary)iterating;
                }
            }
        }
        return null;
    }

    /**
     * This method search and returns an employee
     * If the vat_number is an empty String this method will return all the employees
     * It works only if the authorizer has an higher level than the searched person 
     * 
     * @param Person authorizer
     * @param String vat_number
     * 
     * @return ArrayList<Serializable> results
     */
    
    public ArrayList<Serializable> getEmployee (Person authorizer, String vat_number)
    {
        ArrayList<Serializable> results = new ArrayList<Serializable>();

        if (vat_number.equals("")) {
            for (Person iterating: this.employees.values()){
                if ((authorizer instanceof Admin)
                        || ((authorizer instanceof Manager)&&!(iterating instanceof Admin))
                        || ((authorizer instanceof Functionary)&&!(iterating instanceof Functionary))) {
                    results.add(iterating);
                }
            }
        }
        else {
            Person foundPerson = this.employees.get(vat_number);
            if (foundPerson == null )
                return null;
            if ((authorizer instanceof Admin)
                    || ((authorizer instanceof Manager)&&!(foundPerson instanceof Admin))
                    || ((authorizer instanceof Functionary)&&!(foundPerson instanceof Functionary))) {
                results.add(foundPerson);
            }
        }
        return results;
    }

    /**
     * This method returns all the employees from a determinate headquarter
     * It works only if the authorizer is not a functionary
     * 
     * @param Person authorizer
     * @param String hqName
     * 
     * @return ArrayList<Serializable> results
     * 
     * @throws UnauthorizedUserException
     */
    
    public ArrayList<Serializable> getEmployeesByHQ (Person authorizer, String hqName) throws UnauthorizedUserException {
        ArrayList<Serializable> results = new ArrayList<Serializable>();
        if (!((authorizer instanceof Manager)||(authorizer instanceof Admin))) {
            throw new UnauthorizedUserException();
        }
        for (Person employee: this.employees.values()) {
            if (employee.getHeadquarter().getName().equals(hqName) && !(authorizer instanceof Manager && employee instanceof Admin)){ // Here, we don't want to display admin data to functionaries
                results.add(employee);
            }
        }
        return results;
    }
    /**
     * This method searches and returns the employees by their job
     * It works only if the authorizer has an higher level than the searched employees
     * 
     * @param Person authorizer
     * @param String jobName
     * 
     * @return ArrayList<Serializable> results
     * 
     * @throws UnauthorizedUserException
     */
    
    public ArrayList<Serializable> getEmployeesByJob (Person authorizer, String jobName) throws UnauthorizedUserException {
        ArrayList<Serializable> results = new ArrayList<Serializable>();
        for (Person employee: this.employees.values()) {
            if (employee.getClass().getSimpleName().equals(jobName) && // We first check if it's the job we're looking for
                    ((authorizer instanceof Admin) // Then, we check if the user is authorized
                    || (authorizer instanceof Manager && !(employee instanceof Admin))
                    || (authorizer instanceof Functionary && !(employee instanceof Functionary))
                    )
            ){
                results.add(employee);
            }
        }
        return results;
    }

    /**
     * This method deletes  an employee from the employees' HashMap
     * It works only if the authorizer has an higher level than the searched employee
     * And if the vat_number belongs to an existing employee
     * 
     * @param Person authorizer
     * @param String vat_number
     * 
     * @return boolean 
     * 
     * @throws UnauthorizedUserException
     */
    
    public boolean deleteEmployee(Person authorizer, String vat_number) throws UnauthorizedUserException {
        Person result = this.employees.get(vat_number);
        if (result == null)
            return false;
        if (!(
                (authorizer instanceof Admin)
                || (authorizer instanceof Manager && !(result instanceof Admin))
                || (authorizer instanceof Functionary && !(result instanceof Functionary))
        )) {
            throw new UnauthorizedUserException();
        }
        // If the user is authorized, we can proceed
        this.employees.remove(vat_number);
        return true;
    }
    
    /**
     * This method adds an headquarter from the headquarters' HashMap
     * It works only if the authorizer is not a functionary and if the new headquarter is not already existing
     * 
     * @param Person      authorizer
     * @param Headquarter newHeadquarter
     * 
     * @return boolean
     * 
     * @throws UnauthorizedUserException
     */

    public boolean addHeadquarter (Person authorizer, Headquarter newHeadquarter) throws UnauthorizedUserException {
        if (!(authorizer instanceof Admin || authorizer instanceof Manager))
            throw new UnauthorizedUserException();
        if (this.headquarters.containsKey(newHeadquarter.getName()))
            return false;
        this.headquarters.put(newHeadquarter.getName(), newHeadquarter);
        return true;
    }

    /**
     * This method deletes an headquarter from the Headquarters' HashMap
     * It works only if the authorizer is not a functionary 
     * 
     * @param Person authorizer
     * @param string toDelete
     * @return
     * @throws UnauthorizedUserException
     */
    
    public boolean deleteHeadquarter (Person authorizer, String toDelete) throws UnauthorizedUserException {
        if (!(authorizer instanceof Admin || authorizer instanceof Manager))
            throw new UnauthorizedUserException();
        if (this.headquarters.remove(toDelete) == null)
            return false;
        return true;
    }

    /**
     * This method returns an headquarter searched by its name
     * It works only if the authorizer is not a functionary
     * If the String name is empty it returns all of the headquarter form the headquarters' HashMap
     * 
     * @param Person authorizer
     * @param String name
     * 
     * @return ArrayList<Serializable> results
     * 
     * @throws UnauthorizedUserException
     */
    
    public ArrayList<Serializable> getHeadquarter (Person authorizer, String name) throws UnauthorizedUserException {
        if (!(authorizer instanceof Admin || authorizer instanceof Manager))
            throw new UnauthorizedUserException();
        if (name.equals("")) {
            return new ArrayList<Serializable>(this.headquarters.values());
        }
        ArrayList<Serializable> results = new ArrayList<Serializable>();
        Headquarter result = this.headquarters.get(name);
        if (result != null)
            results.add(result);
        return results;
    }

    /**
     * This method edit an headquarter from the headquarters' HashMap
     * It works only if the authorizer is not a functionary
     * 
     * @param String      authorizer
     * @param Headquarter editedHQ
     * 
     * @return boolean
     * 
     * @throws UnauthorizedUserException
     */
    
    public boolean editHeadquarter(Person authorizer, Headquarter editedHQ) throws UnauthorizedUserException {
        Headquarter toEdit = this.headquarters.get(editedHQ.getName());
        if (toEdit == null)
            return false;
        if ((authorizer instanceof Admin) || ((authorizer instanceof Manager))){
            this.headquarters.put(editedHQ.getName(), editedHQ);
            return true;
        } else {
            throw new UnauthorizedUserException();
        }
    }

}
