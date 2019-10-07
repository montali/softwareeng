package it.unipr.bottimontali;

/**
 * Admin is a subclass of person. It has some privileges more than Person.
 * 
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @version     1.0
 * @since       1.0
 */
public class Admin extends Person
{

    public Admin(String name, String surname, String email, String password) {
        super(name, surname, email, password);
    }

    public Admin() {
    }
    
    public void addMemberToClub (Club club, Person newMember)
    {
        club.addMember(this, newMember);
    }
    public void removeMemberFromClub (Club club, Person deleteMember)
    {
        club.removeMember(this, deleteMember);
    }
    public void editMemberOfClub (Club club, Person oldPerson, Person newPerson)
    {
        club.editPerson(this, oldPerson, newPerson);
    }
    public void addActivityToClub (Club club, Activity newActivity)
    {
        club.addActivity(this, newActivity);
    }
    public void removeActivityFromClub (Club club, Activity deleteActivity)
    {
        club.removeActivity(this, deleteActivity);
    }
    public void editActivityOfClub (Club club, Activity oldActivity, Activity newActivity)
    {
        club.editActivity(this, oldActivity, newActivity);
    }
}
