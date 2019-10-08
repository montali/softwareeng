package it.unipr.bottimontali;

/**
 * Admin is a subclass of person. It has some privileges more than Person.
 * 
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * 
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
     * This method is a wrapper for the Club's addMember method.
     * 
     * @param Club   club
     * @param Person newMember
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void addMemberToClub (Club club, Person newMember)
    {
        club.addMember(this, newMember);
    }
    /**
     * This method is a wrapper for the Club's removeMember method.
     * 
     * @param Club   club
     * @param Person deleteMember
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void removeMemberFromClub (Club club, Person deleteMember)
    {
        club.removeMember(this, deleteMember);
    }
    /**
     * This method is a wrapper for the Club's editPerson method.
     * 
     * @param Club   club
     * @param Person oldPerson
     * @param Person newPerson
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void editMemberOfClub (Club club, Person oldPerson, Person newPerson)
    {
        club.editPerson(this, oldPerson, newPerson);
    }
    /**
     * This method is a wrapper for the Club's addActivity method.
     * 
     * @param Club     club
     * @param Activity newActivity
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void addActivityToClub (Club club, Activity newActivity)
    {
        club.addActivity(this, newActivity);
    }
    /**
     * This method is a wrapper for the Club's removeActivity method.
     * 
     * @param Club     club
     * @param Activity deleteActivity
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void removeActivityFromClub (Club club, Activity deleteActivity)
    {
        club.removeActivity(this, deleteActivity);
    }
    /**
     * This method is a wrapper to the Club's editActivity method.
     * 
     * @param Club     club
     * @param Activity oldActivity
     * @param Activity newActivity
     * 
     * @return void
     * 
     * @since 1.0
     */
    public void editActivityOfClub (Club club, Activity oldActivity, Activity newActivity)
    {
        club.editActivity(this, oldActivity, newActivity);
    }
}
