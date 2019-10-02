package it.unipr.bottimontali;

/**
 * Hello world!
 *
 */
public class Club 
{
    private String name;
    private Person [] people;
    private Activity [] activities;

    public Club()
    {
        this.name = "";
        this.people = new Person [0];
        this.activities = new Activity [0];
    }

    public Club(final String name)
    {
        this.name = name;
        this.activities = new Activity[0];
        this.people = new Person[0];
    }

    public Club(final String name, final Activity []activities, final Person []people)
    {
        this.name = name;
        this.activities = new Activity[activities.length];
        this.people = new Person[people.length];
        System.arraycopy(activities, 0, this.activities, 0, activities.length);
        System.arraycopy(people, 0, this.people, 0, people.length);
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Person[] getPeople()
    {
        Person []returnedValue = new Person[this.people.length];
        System.arraycopy(this.people, 0, returnedValue, 0, this.people.length);
        return returnedValue;
    }
    public void setPeople(Person []inputPeople)
    {
        Person [] newArray = new Person[inputPeople.length];
        System.arraycopy(inputPeople, 0, newArray, 0, inputPeople.length);
        this.people = newArray;
    }

    public Activity[] getActivities()
    {
        Activity []returnedValue = new Activity[this.activities.length];
        System.arraycopy(this.activities, 0, returnedValue, 0, this.activities.length);
        return returnedValue;
    }
    public void setActivities(Activity []inputActivities)
    {
        Activity [] newActivities = new Activity[inputActivities.length];
        System.arraycopy(inputActivities, 0, newActivities, 0, inputActivities.length);
        this.activities = newActivities;
    }

    public void addMember(Admin authorizer, Person toSubscribe)
    {
        // Check if the admin is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        this.setPeople(Helpers.appendPerson(this.getPeople(), toSubscribe));
    }
    public void removeMember(Admin authorizer, Person toDelete)
    {
        // Check if the admin is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        this.setPeople(Helpers.popPerson(this.getPeople(), toDelete));
    }
    public void addActivity(Admin authorizer, Activity toAdd)
    {
        // Check if the admin is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        this.setActivities(Helpers.appendActivity(this.getActivities(), toAdd));
    }
    public void removeActivity(Admin authorizer, Activity toDelete)
    {
        // Check if the admin is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        this.setActivities(Helpers.popActivity(this.getActivities(), toDelete));
    }
    public void editActivity(Admin authorizer, Activity oldActivity, Activity newActivity)
    {
        // Check if the admin is an admin of this specific club
        if (!Helpers.elementExists(this.people, authorizer)) {
            return;
        }
        if (!Helpers.elementExists(this.activities, oldActivity)){
            return;
        }
        this.removeActivity(authorizer, oldActivity);
        this.addActivity(authorizer, newActivity);
    }
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
    public void subscribeToActivity (Person toSubscribe, Activity toSubscribeTo)
    {
        // Check if already subscribed
        if (Helpers.elementExists(toSubscribeTo.getRegistered(), toSubscribe))
            return;
        for (int i = 0; i<this.activities.length; i++){
            if  (this.activities[i].getName() == toSubscribeTo.getName()){
                this.activities[i].setRegistered(Helpers.appendPerson(this.activities[i].getRegistered(), toSubscribe));
            }
        }
    }
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
