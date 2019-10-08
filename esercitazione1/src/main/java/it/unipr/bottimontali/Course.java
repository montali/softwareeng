package it.unipr.bottimontali;

/**
 * Course is an Activity subclass which is in charge of saving a course with its properties.
 * Every course has two type of constructors.
 * 
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class Course extends Activity 
{
	/**
	 * This constructor generates a Course from its name
	 * and its subscribed people.
	 * 
	 * @param String   name       the course name
	 * @param Person[] registered the registered people
	 * 
	 * @since 1.0
	 */
    public Course(String name, Person[] registered) {
        this.setName(name);
        this.setRegistered(registered);
    }
    /**
     * This constructor generates a Course by its name only.
     * There will be no subscribers.
     * 
     * @param String name
     * 
     * @since 1.0
     */
    public Course(String name) {
        this.setName(name);
        this.setRegistered(new Person[0]);
    }
    /**
     * Empty constructor for a Course object
     * 
     * @since 1.0
     */
    public Course() {
    }
}