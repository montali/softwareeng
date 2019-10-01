package it.unipr.bottimontali;

/**
 * Hello world!
 *
 */
public abstract class Person 
{   
    private String name;
    private String surname;
    private String email;
    private String password;

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

    public void subscribeToActivity(Activity desiredActivity)
    {
        desiredActivity.addPerson(this);
    }

    public void unsubscribeFromActivity(Activity unsubscribingActivity)
    {
        unsubscribingActivity.popPerson(this);
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
