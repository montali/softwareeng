package it.unipr.bottimontali;

/**
 * Hello world!
 *
 */
public class Race extends Activity 
{
    public Race(String name, Person[] registered) {
        this.setName(name);
        this.setRegistered(registered);
    }
    public Race(String name) {
        this.setName(name);
        this.setRegistered(new Person[0]);
    }
    public Race() {
    }
}