package it.unipr.bottimontali;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public abstract class Activity 
{
    private String name;
    private Person [] registered;

    private Activity() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person[] getRegistered() {
        return this.registered;
    }

    public void setRegistered(Person[] registered) {
        this.registered = registered;
    }

    public void addPerson (Person person)
    {
        this.registered = Arrays.copyOf(this.registered, this.registered.length+1);
        this.registered [this.registered.length-1] = person;
    }

    public void popPerson (Person toDelete)
    {
        boolean foundPerson = false;
        for (int i=0; i<this.registered.length;i++){
            if (foundPerson)
                this.registered[i-1] = this.registered[i];
            if (this.registered[i].equals(toDelete))
                foundPerson = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Activity)) {
            return false;
        }
        Activity activity = (Activity) o;
        return (name == activity.name) && java.util.Arrays.equals(registered, activity.registered);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", registered='" + getRegistered().toString() + "'" +
            "}";
    }
    
}
