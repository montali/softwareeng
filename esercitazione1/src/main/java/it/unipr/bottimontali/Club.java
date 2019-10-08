package it.unipr.bottimontali;

/**
 * Club is in charge of saving a Club object with his properties.
 * Each Club has a name, a list of Person and a list of Activity.
 * 
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class Club 
{
    private String name;
    private Person [] people;
    private Activity [] activities;
    /**
     * This constructor generate a Club object without the initialization of the parameters. 
     * 
     * @since 1.0
     */
    public Club()
    {
        this.name = "";
        this.people = new Person [0];
        this.activities = new Activity [0];
    }
    /**
     * This constructor create a Club object and set the club's name to the String name.
     * 
     * @param final String name
     * 
     * @since 1.0
     */
    public Club(final String name)
    {
        this.name = name;
        this.activities = new Activity[0];
        this.people = new Person[0];
    }
    /**
     * This constructor creates a Club object and set the club's name, people and activities.
     * Activity and people are setting with System.arraycopy function.
     * 
     * @param final String name
     * @param final Activity[] activities
     * @param final Person[] people
     * 
     * @since 1.0
     */
    public Club(final String name, final Activity []activities, final Person []people)
    {
        this.name = name;
        this.activities = new Activity[activities.length];
        this.people = new Person[people.length];
        System.arraycopy(activities, 0, this.activities, 0, activities.length);
        System.arraycopy(people, 0, this.people, 0, people.length);
    }
    /**
     * This method returns the Club's name.
     * 
     * @return String the club's name
     * 
     * @since 1.0
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * This method sets the Club's name.
     * 
     * @param String name the club's new name
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * This method returns an array of Person that are subscribed to the Club.
     * 
     * @return Person[] the subscribed people
     * 
     * @since 1.0
     */
    public Person[] getPeople()
    {
        Person []returnedValue = new Person[this.people.length];
        System.arraycopy(this.people, 0, returnedValue, 0, this.people.length);
        return returnedValue;
    }
    /**
     * This method sets the array of Person that are subscribed to the Club.
     * 
     * @param Person[] inputPeople the new people array
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void setPeople(Person []inputPeople)
    {
        Person [] newArray = new Person[inputPeople.length];
        System.arraycopy(inputPeople, 0, newArray, 0, inputPeople.length);
        this.people = newArray;
    }
    /**
     * This method returns an array of Activities that are present in the Club.
     * 
     * @return Activity[] the activities
     * 
     * @since 1.0
     */
    public Activity[] getActivities()
    {
        Activity []returnedValue = new Activity[this.activities.length];
        System.arraycopy(this.activities, 0, returnedValue, 0, this.activities.length);
        return returnedValue;
    }
    /**
     * This method sets the array of Activities that are present in the Club.
     * 
     * @param Activity[] inputActivities the new activities
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void setActivities(Activity []inputActivities)
    {
        Activity [] newActivities = new Activity[inputActivities.length];
        System.arraycopy(inputActivities, 0, newActivities, 0, inputActivities.length);
        this.activities = newActivities;
    }
    /**
     * This method adds a member to the Club. It only works if the authorizer 
     * is a Club's Admin and the person isn't already subscribed. If true it appends 
     * the Person toSubscribe to the Club's array of subscribers.
     * 
     * @param Admin  authorizer  the admin
     * @param Person toSubscribe the new person
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void addMember(Admin authorizer, Person toSubscribe)
    {
        // Check if the authorizer is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        // Checks if the member is already subscribed
        if (Helpers.elementExists(this.people, toSubscribe)) {
        	return;
        }
        this.setPeople(Helpers.appendPerson(this.getPeople(), toSubscribe));
    }
    /**
     * This method removes a member from the Club. It only works if the authorizer
     * is a Club's Admin and the person is a club member. If true it removes the Person 
     * toDelete from the Club's array of subscribers.
     * 
     * @param Admin  authorizer the authorizing admin
     * @param Person toDelete   the person to delete
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void removeMember(Admin authorizer, Person toDelete)
    {
        // Check if the authorizer is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        if (!Helpers.elementExists(this.people, toDelete)) {
        	return;
        }
        this.setPeople(Helpers.popPerson(this.getPeople(), toDelete));
    }
    /**
     * This method adds an activity to the Club. It only works if the authorizer 
     * is a Club's Admin and the activity doesn't already exist. If true it appends
     * the Activity toAdd to the Club's array of Activities.
     * 
     * @param Admin    authorizer the authorizing admin
     * @param Activity toAdd	  the activity to add
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void addActivity(Admin authorizer, Activity toAdd)
    {
        // Check if the authorizer is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        // Check if the activity already exists
        if (Helpers.elementExists(this.activities, toAdd)) {
        	return;
        }
        this.setActivities(Helpers.appendActivity(this.getActivities(), toAdd));
    }
    /**
     * This method removes an Activity from the Club. It only works if the authorizer 
     * is a Club's Admin and the activity is present. If true it remove the Activity
     * toDelete from the Club's array of Activity.
     * 
     * @param Admin    authorizer the authorizing admin
     * @param Activity toDelete	  the activity we want to delete
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void removeActivity(Admin authorizer, Activity toDelete)
    {
        // Check if the authorizer is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        if (!Helpers.elementExists(this.activities, toDelete)) {
        	return;
        }
        this.setActivities(Helpers.popActivity(this.getActivities(), toDelete));
    }
    /**
     * This method replaces a Club's Activity with a new Activity. It only works if the 
     * authorizer is a Club's Admin and if true it remove the Activity oldActivity from 
     * the Club and add the Activity newActivity to the Club.
     * 
     * @param Admin    authorizer  the authorizing admin
     * @param Activity oldActivity the old activity
     * @param Activity newActivity the activity to replace with
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void editActivity(Admin authorizer, Activity oldActivity, Activity newActivity)
    {
        // Check if the authorizer is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        if (!Helpers.elementExists(this.activities, oldActivity)){
            return;
        }
        // If the new activity is already saved, we're just gonna delete the old one
        if (Helpers.elementExists(this.activities, newActivity)) {
        	this.removeActivity(authorizer, oldActivity);
        	return;
        }
        this.removeActivity(authorizer, oldActivity);
        this.addActivity(authorizer, newActivity);
    }
    /**
     * This method replaces a Club's subscriber with a new Person. It only works 
     * if the authorizer is a Club's Admin and if the Person oldPerson is in the Club's array of Person.
     * If true it remove the Person oldPerson from the Club and add the Person newPerson to the Club
     * 
     * @param Admin  authorizer the authorizing admin
     * @param Person oldPerson  the person to replace
     * @param Person newPerson  the person to replace with
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void editPerson(Admin authorizer, Person oldPerson, Person newPerson)
    {
        // Check if the admin is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        if (!Helpers.elementExists(this.people, oldPerson)){
            return;
        }
        // If the new person is already saved, we're just gonna delete the old one
        if (Helpers.elementExists(this.people, newPerson)) {
        	this.removeMember(authorizer, oldPerson);
        	return;
        }
        this.removeMember(authorizer, oldPerson);
        this.addMember(authorizer, newPerson);
    }
    /**
     * This method subscribes a Person to an Activity. It only works if 
     * the Person toSubscribe is not already subscribed to the Activity toSubscribeTo.
     * It then checks if the activity exists.
     * 
     * @param Person   toSubscribe   the person to subscribe
     * @param Activity toSubscribeTo the activity we want to subscribe the person to
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void subscribeToActivity (Person toSubscribe, Activity toSubscribeTo)
    {
        // Check if already subscribed
        if (Helpers.elementExists(toSubscribeTo.getRegistered(), toSubscribe))
            return;
        if (!Helpers.elementExists(this.activities, toSubscribeTo)) {
        	return;
        }
        for (int i = 0; i<this.activities.length; i++){
            if  (this.activities[i].getName() == toSubscribeTo.getName()){
                this.activities[i].setRegistered(Helpers.appendPerson(this.activities[i].getRegistered(), toSubscribe));
            }
        }
    }
    
    /**
     * This method unsubscribes a Person from an Activity. It only works if the Person toUnsubscribe 
     * is not already unsubscribed from the Activity toUnsubscribeFrom.
     * It checks if the activity exists.
     *       
     * @param Person   toUnsubscribe
     * @param Activity toUnsubscribeFrom
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void unSubscribeFromActivity (Person toUnsubscribe, Activity toUnsubscribeFrom)
    {
    	if (!Helpers.elementExists(this.activities, toUnsubscribeFrom)) {
    		return;
    	}
        // Check if already subscribed
        if (!Helpers.elementExists(toUnsubscribeFrom.getRegistered(), toUnsubscribe))
            return;
        for (int i = 0; i<this.activities.length; i++){
            if  (this.activities[i].getName() == toUnsubscribeFrom.getName()){
                this.activities[i].setRegistered(Helpers.popPerson(this.activities[i].getRegistered(), toUnsubscribe));
            }
        }
    }
    /**
     * This method's return value is a complete description for a Club object. 
     * It shows the Club's name and all of the Club's subscribers and activities.
     * 
     * @return String the description
     * 
     * @since 1.0
     */
    @Override
    public String toString() {
        String result = "CLUB: "+ this.getName() +"\nMembers:\n";
        for (Person member: this.people){
            result += (member.toString()+"\n");
        }
        result += "\nActivities:\n";
        for (Activity activity: this.activities){
            result += (activity.toString() + "\n");
        }
        return result;
    }
}
