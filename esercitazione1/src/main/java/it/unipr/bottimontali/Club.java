package it.unipr.bottimontali;

/**
 * Club is in charge of saving a Club object with his properties.
 * Each Club has a name, a list of Person and a list of Activity.
 * 
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
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
     */
    public Club()
    {
        this.name = "";
        this.people = new Person [0];
        this.activities = new Activity [0];
    }
    /**
     * This constructor create a Club object and set the club's name to the String name.
     * @param final String name
     */
    public Club(final String name)
    {
        this.name = name;
        this.activities = new Activity[0];
        this.people = new Person[0];
    }
    /**
     * This constructor create a Club object and set the club's name, people and activities.
     * Activity and people are setting with System.arraycopy function.
     * @param final String name
     * @param final Activity[] activities
     * @param final Person[] people
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
     * This behavior returns the Club's name.
     * @return String this.namebscribesbscribes
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * This behavior set the Club's name.
     * @param String name for the Club
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * This behavior returns an array of Persons that are subscribe to the Club.
     * @return Person[] returnedValue
     */
    public Person[] getPeople()
    {
        Person []returnedValue = new Person[this.people.length];
        System.arraycopy(this.people, 0, returnedValue, 0, this.people.length);
        return returnedValue;
    }
    /**
     * This behavior set the array of Person that are subscribe to the Club.
     * @param Person[] inputPeople
     */
    public void setPeople(Person []inputPeople)
    {
        Person [] newArray = new Person[inputPeople.length];
        System.arraycopy(inputPeople, 0, newArray, 0, inputPeople.length);
        this.people = newArray;
    }
    /**
     * This behavior returns an array of Activities that are present in the Club.
     * @return Activity[] returnedValue
     */
    public Activity[] getActivities()
    {
        Activity []returnedValue = new Activity[this.activities.length];
        System.arraycopy(this.activities, 0, returnedValue, 0, this.activities.length);
        return returnedValue;
    }
    /**
     * This behavior set the array of Activities that are present in the Club.
     * @param Activity[] inputActivities
     */
    public void setActivities(Activity []inputActivities)
    {
        Activity [] newActivities = new Activity[inputActivities.length];
        System.arraycopy(inputActivities, 0, newActivities, 0, inputActivities.length);
        this.activities = newActivities;
    }
    /**
     * This behavior add a member to the Club. It works only if the authorizer is a Club's Admin and if true it append the Person toSubrscibe to the Club's array of subscribers.
     * @param Admin  authorizer
     * @param Person toSubscribe
     */
    public void addMember(Admin authorizer, Person toSubscribe)
    {
        // Check if the authorizer is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        this.setPeople(Helpers.appendPerson(this.getPeople(), toSubscribe));
    }
    /**
     * This behavior remove a member from the Club. It works only if the authorizer is a Club's Admin and if true it remove the Person toDelete from the Club's array of subscribers.
     * @param Admin  authorizer
     * @param Person toDelete
     */
    public void removeMember(Admin authorizer, Person toDelete)
    {
        // Check if the authorizer is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        this.setPeople(Helpers.popPerson(this.getPeople(), toDelete));
    }
    /**
     * This behavior add an activity to the Club. It works only if the authorizer is a Club's Admin and if true it append the Activity toAdd to the Club's array of Activities.
     * @param Admin    authorizer
     * @param Activity toAdd
     */
    public void addActivity(Admin authorizer, Activity toAdd)
    {
        // Check if the authorizer is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        this.setActivities(Helpers.appendActivity(this.getActivities(), toAdd));
    }
    /**
     * This behavior remove an Activity from the Club. It works only if the authorizer is a Club's Admin and if true it remove the Activity toDelete from the Club's array of Activity.
     * @param Admin    authorizer
     * @param Activity toDelete
     */
    public void removeActivity(Admin authorizer, Activity toDelete)
    {
        // Check if the authorizer is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        this.setActivities(Helpers.popActivity(this.getActivities(), toDelete));
    }
    /**
     * This behavior replace a Club's Activity with a new Activity. It works only if the authorizer is a Club's Admin and if true it remove the Activity oldActivity from the Club and add the Activity newActivity to the Club.
     * @param Admin    authorizer
     * @param Activity oldActivity
     * @param Activity newActivity
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
        this.removeActivity(authorizer, oldActivity);
        this.addActivity(authorizer, newActivity);
    }
    /**
     * This behavior replace a Club's subscriber with a new Person. It works only if the authorizer is a Club's Admin and if the Person oldPerson is in the CLub's array of Person.
     * If true it remove the Person oldPerson from the Club and add the Person newPerson to the Club
     * @param Admin  authorizer
     * @param Person oldPerson
     * @param Person newPerson
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
        this.removeMember(authorizer, oldPerson);
        this.addMember(authorizer, newPerson);
    }
    /**
     * This behavior subscribes a Person to an Activity. It works only if the Person toSubsribe is not already subscribe to the Activity toSubscribeTo.
     * If true it search the Activity to the Club's array and then is a wrapper to the Activity setRegistered behavior with the Helpers appendPerson.
     * @param Person   toSubscribe
     * @param Activity toSubscribeTo
     */
    public void subscribeToActivity (Person toSubscribe, Activity toSubscribeTo)
    {
        // Check if already subscribed
        if (Helpers.elementExists(toSubscribeTo.getRegistered(), toSubscribe))
            return;
        //CONTROLLO CHE ATTIVITA' ESISTA?
        for (int i = 0; i<this.activities.length; i++){
            if  (this.activities[i].getName() == toSubscribeTo.getName()){
                this.activities[i].setRegistered(Helpers.appendPerson(this.activities[i].getRegistered(), toSubscribe));
            }
        }
    }
    /**
     This behavior unsubscribes a Person from an Activity. It works only if the Person toUnsubscribe is not already unsubscribed from the Activity toUnsubscribeFrom.
     * If true it search the Activity to the Club's array and then is a wrapper to the Activity setRegistered behavior with the helpers popPerson.
     * @param Person   toUnsubscribe
     * @param Activity toUnsubscribeFrom
     */
    public void unSubscribeFromActivity (Person toUnsubscribe, Activity toUnsubscribeFrom)
    {
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
     * This behavior is a complete description for a Club object. It show the Club's name and all of the Club's subscribers and activities.
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
