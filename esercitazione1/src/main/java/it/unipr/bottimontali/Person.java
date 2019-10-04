package it.unipr.bottimontali;

/**
 * Person is in charge of saving a person object with its properties.
 * Each person has a name, a surname, an email and a password.
 *  
 * TODO: Encrypt the password
 * 
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @version     1.0
 * @since       1.0
 */
public class Person 
{   
    private String name;
    private String surname;
    private String email;
    private String password;
    /** 
     * This constructor generates a Person object.
     *
     * @param String name     the person name
     * @param String surname  the person surname
     * @param String email    the person email 
     * @param String password the person password
     * @return void
     * @since           1.0
     */
    public Person(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
    /** 
     * This behavior gets the Person's name.
     *
     * @return String the Person's name.
     * @since           1.0
     */
    public String getName() {
        return this.name;
    }
    /** 
     * This behavior sets the Person's name.
     * @param String name for the Person.
     * @since           1.0
     */
    public void setName(String name) {
        this.name = name;
    }
    /** 
     * This behavior gets the Person's surname.
     *
     * @return String the Person's surname.
     * @since           1.0
     */
    public String getSurname() {
        return this.surname;
    }
    /** 
     * This behavior sets the Person's surname.
     * @param String surname for the Person.
     * @since           1.0
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /** 
     * This behavior gets the Person's email.
     *
     * @return String the Person's email.
     * @since           1.0
     */
    public String getEmail() {
        return this.email;
    }
    /** 
     * This behavior sets the Person's email.
     * @param String email for the Person.
     * @since           1.0
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /** 
     * This behavior gets the Person's password.
     *
     * @return String the Person's password.
     * @since           1.0
     */
    public String getPassword() {
        return this.password;
    }
    /** 
     * This behavior sets the Person's password.
     * @param String password for the Person.
     * @since           1.0
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * This behavior subscribes a Person to an activity.
     * This behavior is a wrapper for the Club's subscription behavior.
     * @param Club club
     * @param Activity desiredActivity
     */
    public void subscribeToActivity(Club club, Activity desiredActivity)
    {
        club.subscribeToActivity(this, desiredActivity);
    }
    /**
     * This behavior deletes a Person to an activity.
     * This behavior is a wrapper for the Club's unsubscription behavior.
     * @param Club club
     * @param Activity unsubscribingActivity
     */
    public void unsubscribeFromActivity(Club club, Activity unsubscribingActivity)
    {
        club.unSubscribeFromActivity(this, unsubscribingActivity);
    }
    /**
     * Behavior that verifies the equality of two person.
     * @param Object o
     * @return boolean 
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return (name == person.name) && (surname == person.surname) && (email == person.email) && (password == person.password);
    }
    /**
     * This behavior print a complete person's description.
     * 
     */
    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }


    public Person() {
    }

}
