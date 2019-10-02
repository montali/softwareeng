package it.unipr.bottimontali;

/**
 * Hello world!
 *
 */
public class Course extends Activity 
{
    public Course(String name, Person[] registered) {
        this.setName(name);
        this.setRegistered(registered);
    }
    public Course(String name) {
        this.setName(name);
        this.setRegistered(new Person[0]);
    }
    public Course() {
    }
}