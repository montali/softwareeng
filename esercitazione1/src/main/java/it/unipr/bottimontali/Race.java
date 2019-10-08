package it.unipr.bottimontali;

/**
 * Race is an Activity subclass which is in charge of saving a race with its properties.
 * Every race has two type of constructors.
 * 
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class Race extends Activity 
{
	/**
	 * This constructor generates a Race from its name
	 * and its subscribed people.
	 * 
	 * @param String   name       the race name
	 * @param Person[] registered the registered people
	 * 
	 * @since 1.0
	 */
    public Race(String name, Person[] registered) {
        this.setName(name);
        this.setRegistered(registered);
    }
    /**
     * This constructor generates a Race by its name only.
     * There will be no subscribers.
     * 
     * @param String name
     * 
     * @since 1.0
     */
    public Race(String name) {
        this.setName(name);
        this.setRegistered(new Person[0]);
    }
    /**
     * Empty constructor for a Race object
     * 
     * @since 1.0
     */
    public Race() {
    }
}