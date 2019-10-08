package it.unipr.bottimontali;

import java.util.Arrays;

/**
 * Activity is in charge of saving a activity object with its properties.
 * Every activity has a name and an array of person registered for the activity.
 * 
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public abstract class Activity {
    private String name;
    private Person[] registered;
    /** 
     * This constructor generates an Activity object.
     * Since Activity will not be used as a class itself, we set it to protected.
     *
     * @return Activity the activity object
     * 
     * @since 1.0
     */
    protected Activity() {
    }
    /** 
     * This method gets the Activity name.
     *
     * @return String the Activity name
     * 
     * @since 1.0
     */
    public String getName() {
        return this.name;
    }
    /** 
     * This method sets the Activity name.
     *
     * @param String name the activity name
     * 
     * @return void 
     * 
     * @since 1.0
     */
    public void setName(String name) {
        this.name = name;
    }
    /** 
     * This method gets the Activities registered subscriptions.
     *
     * @return Person []
     * 
     * @since 1.0
     */
    public Person[] getRegistered() {
        return this.registered;
    }
    /** 
     * This method sets the Activities registered subscriptions.
     *
     * @param Person [] the new subscribers
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void setRegistered(Person[] registered) {
        this.registered = registered;
    }
    /** 
     * This method adds a new subscriber to the activity.
     * 
     * @param Person person the subscriber
     *
     * @return void
     * 
     * @since 1.0
     */
    public void addPerson(Person person) {
        this.registered = Arrays.copyOf(this.registered, this.registered.length + 1);
        this.registered[this.registered.length - 1] = person;
    }
    /** 
     * This method deletes a subscriber.
     * 
     * @param Person toDelete the subscriber we want to delete
     *
     * @return void
     * 
     * @since 1.0
     */
    public void popPerson(Person toDelete) {
        boolean foundPerson = false;
        for (int i = 0; i < this.registered.length; i++) {
            if (foundPerson)
                this.registered[i - 1] = this.registered[i];
            if (this.registered[i].equals(toDelete))
                foundPerson = true;
        }
        this.registered[this.registered.length - 1] = null;
    }
    /** 
     * This method compares two Activities
     * 
     * @param Object o an object we want to compare
     * 
     * @return boolean if the objects are equal or not
     * 
     * @since 1.0
     */
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
    /** 
     * This method returns a string describing the activity
     *
     * @return String the string
     * 
     * @since 1.0
     */
    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", registered no.='" + getRegistered().length + "'" + "}";
    }

}
