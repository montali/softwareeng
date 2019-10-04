package it.unipr.bottimontali;

/**
 * Person is in charge of saving a person object with its properties.
 * Every person has a name, a surname, an email and a password.
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void subscribeToActivity(Club club, Activity desiredActivity)
    {
        club.subscribeToActivity(this, desiredActivity);
    }

    public void unsubscribeFromActivity(Club club, Activity unsubscribingActivity)
    {
        club.unSubscribeFromActivity(this, unsubscribingActivity);
    }

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
