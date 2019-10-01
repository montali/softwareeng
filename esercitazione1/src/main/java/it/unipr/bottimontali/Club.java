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

    public Club(final String name, final Activity []activities, final Person []people)
    {
        this.name = name;
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
        System.arraycopy(inputPeople, 0, this.people, 0, inputPeople.length);
    }

    public Activity[] getActivities()
    {
        Activity []returnedValue = new Activity[this.activities.length];
        System.arraycopy(this.activities, 0, returnedValue, 0, this.activities.length);
        return returnedValue;
    }
    public void setActivities(Activity []inputActivities)
    {
        System.arraycopy(inputActivities, 0, this.activities, 0, inputActivities.length);
    }
}
