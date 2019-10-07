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
    /**
     * This behavior is a wrapper for the Club's addMember behavior.
     * @param Club   club
     * @param Person newMember
     */
    public void addMemberToClub (Club club, Person newMember)
    {
        club.addMember(this, newMember);
    }
    /**
     * This behavior is a wrapper for the Club's removeMember behavior.
     * @param Club   club
     * @param Person deleteMember
     */
    public void removeMemberFromClub (Club club, Person deleteMember)
    {
        club.removeMember(this, deleteMember);
    }
    /**
     * This behavior is a wrapper for the Club's editPerson behavior.
     * @param Club   club
     * @param Person oldPerson
     * @param Person newPerson
     */
    public void editMemberOfClub (Club club, Person oldPerson, Person newPerson)
    {
        club.editPerson(this, oldPerson, newPerson);
    }
    /**
     * This behavior is a wrapper for the Club's addActivity behavior.
     * @param Club     club
     * @param Activity newActivity
     */
    public void addActivityToClub (Club club, Activity newActivity)
    {
        club.addActivity(this, newActivity);
    }
    /**
     * This behavior is a wrapper for the Club's removeActivity behavior.
     * @param Club     club
     * @param Activity deleteActivity
     */
    public void removeActivityFromClub (Club club, Activity deleteActivity)
    {
        club.removeActivity(this, deleteActivity);
    }
    /**
     * This behavior is a wrapper to the Club's editActivity behavior.
     * @param Club     club
     * @param Activity oldActivity
     * @param Activity newActivity
     */
    public void editActivityOfClub (Club club, Activity oldActivity, Activity newActivity)
    {
        club.editActivity(this, oldActivity, newActivity);
    }
}
