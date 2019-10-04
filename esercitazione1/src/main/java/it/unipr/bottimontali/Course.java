package it.unipr.bottimontali;

/**
 * Course is an Activity subclass which is in charge of saving a course with its properties.
 * Every course has two type of constructor.
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @version     1.0
 * @since       1.0
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