import jdk.javadoc.internal.doclets.formats.html.markup.Head;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;



public class UserDashboard {

    private String username;
    private String password;
    private Client client;

    /**
     * This constructor initializes the client and check the login
     * 
     * @param String username
     * @param String password
     */
    
    public UserDashboard(String username, String password) {
        this.client = new Client();
        // We're gonna save the password only if it is correct.
        while (this.password == null) {
            if (this.client.send(new Message(username, password, MessageType.LOGIN, "")).wasOk()) {
                this.username = username;
                this.password = password;
            } else {
                System.out.println("Wrong login.");
            }
        }
    }

    /**
     * This method searches an employee by his fiscal code
     * If the String vat_number is empty this method show all the employees we can show to this specific user
     * @param String vat_number
     * @return ArrayList<Serializable> 
     */
    
    public ArrayList<Serializable> searchEmployee(String vat_number)
    {
        Reply reply = this.client.send(new Message(this.username, this.password, MessageType.GET_EMPLOYEE, vat_number));
        if (reply.wasOk()){
            System.out.println("Successfully fetched results.");
            return reply.getResults();
        } else {
            System.out.println("Error in getting results. "+" - ERROR:"+reply.getResponseCode().name());
            return new ArrayList<Serializable>();
        }
    }

    /**
     * This method searches employees that work in a specific headquarter
     * 
     * @param String hqName
     * @return ArrayList<Serializable> 
     */
    
    public ArrayList<Serializable> searchEmployeeByHQ(String hqName)
    {
        Reply reply = this.client.send(new Message(this.username, this.password, MessageType.GET_EMPLOYEES_BY_HQ, hqName));
        if (reply.wasOk()){
            System.out.println("Successfully fetched results.");
            return reply.getResults();
        } else {
            System.out.println("Error in getting results. "+" - ERROR:"+reply.getResponseCode().name());
            return new ArrayList<Serializable>();
        }
    }


    /**
     * This method searches employees by their job
     * 
     * @param String jobName
     * @return ArrayList<Serializable>
     */
    
    public ArrayList<Serializable> searchEmployeeByJob(String jobName)
    {
        Reply reply = this.client.send(new Message(this.username, this.password, MessageType.GET_EMPLOYEES_BY_JOB, jobName));
        if (reply.wasOk()){
            System.out.println("Successfully fetched results.");
            return reply.getResults();
        } else {
            System.out.println("Error in getting results. "+" - ERROR:"+reply.getResponseCode().name());
            return new ArrayList<Serializable>();
        }
    }
    
    /**
     * This method edits an employee
     * 
     * @param Person editingPerson
     */

    public void edit(Person editingPerson)
    {
        Reply reply = this.client.send(new Message(this.username, this.password, MessageType.EDIT_EMPLOYEE, "", editingPerson));
        if (reply.wasOk()){
            System.out.println("Successfully edited user "+editingPerson.getName());
        } else {
            System.out.println("Error in editing user "+editingPerson.getName()+" - ERROR:"+reply.getResponseCode().name());
        }
    }
    
    /**
     * This method edits an headquarter
     * 
     * @param Headquarter editingHQ
     */
    public void edit(Headquarter editingHQ)
    {
        Reply reply = this.client.send(new Message(this.username, this.password, MessageType.EDIT_HEADQUARTER, "", editingHQ));
        if (reply.wasOk()){
            System.out.println("Successfully edited HQ "+editingHQ.getName());
        } else {
            System.out.println("Error in editing HQ "+editingHQ.getName()+" - ERROR:"+reply.getResponseCode().name());
        }
    }

    /**
     * This method deletes an employee
     * 
     * @param String vat
     */
    
    public void deleteEmployee (String vat)
    {
        Reply reply = this.client.send(new Message(this.username, this.password, MessageType.DELETE_EMPLOYEE, vat));
        if (reply.wasOk()){
            System.out.println("Successfully deleted user " + vat);
        } else {
            System.out.println("Error in deleting user " + vat+" - ERROR:"+reply.getResponseCode().name());
        }
    }

    /**
     * This method adds a new employee
     * 
     * @param Person newPerson
     */
    
    public void add(Person newPerson)
    {
        Reply reply = this.client.send(new Message(this.username, this.password, MessageType.ADD_EMPLOYEE, "", newPerson));
        if (reply.wasOk()){
            System.out.println("Successfully added employee");
        } else {
            System.out.println("Cannot add employee. ERROR: "+reply.getResponseCode().name());
        }
    }
    
    /**
     * This method adds a new headquarter
     * 
     * @param Headquarter newHQ
     */
    
    public void add(Headquarter newHQ)
    {
        Reply reply = this.client.send(new Message(this.username, this.password, MessageType.ADD_HEADQUARTER, "", newHQ));
        if (reply.wasOk()){
            System.out.println("Successfully added HQ");
        } else {
            System.out.println("Cannot add HQ. ERROR: "+reply.getResponseCode().name());
        }
    }

    /**
     * This method gets headquarter by its name
     * 
     * @param String name
     * @return
     */
    
    public ArrayList<Serializable> getHeadquarter (String name)
    {
        Reply reply = this.client.send(new Message(this.username, this.password, MessageType.GET_HEADQUARTER, name));
        if (reply.wasOk()){
            System.out.println("Successfully fetched results.");
            return reply.getResults();
        } else {
            System.out.println("Error in getting results. "+" - ERROR:"+reply.getResponseCode().name());
            return new ArrayList<Serializable>();
        }
    }

    /**
     * This method deletes an headquarter
     * 
     * @param String toDelete
     */
    
    public void deleteHeadquarter (String toDelete)
    {
        Reply reply = this.client.send(new Message(this.username, this.password, MessageType.DELETE_HEADQUARTER, toDelete));
        if (reply.wasOk()){
            System.out.println("Successfully deleted HQ " + toDelete);
        } else {
            System.out.println("Error in deleting HQ " + toDelete+" - ERROR:"+reply.getResponseCode().name());
        }
    }

}
