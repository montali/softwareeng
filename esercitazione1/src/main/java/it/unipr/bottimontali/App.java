package it.unipr.bottimontali;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person p1 = new Person("Simon", "Montali", "sim.montali@gmail.com", "pass");
        Person p2 = new Person("Filippo", "Botti", "fil.botti@gmail.com", "poss");
        System.out.println("Created 2 users:\n"+p1.toString()+"\n"+p2.toString());

        Admin a1 = new Admin("Mario", "Rossi", "mario@example.com", "mariu");
        System.out.println("Created admin:\n"+a1.toString());
        Person[] initialMembers = {a1};
        Race carRace = new Race ("Gara di auto", new Person[0]);
        Activity[]initialActivities = {carRace};
        Club mariano = new Club("Mariano Tennis Club", initialActivities, initialMembers);
        System.out.println("Created club:\n"+mariano.toString());

        a1.addMemberToClub(mariano, p1);
        a1.addMemberToClub(mariano, p2);
        System.out.println("Added the users to the club.");        
        
        p1.subscribeToActivity(mariano, carRace);

        Course golfCourse = new Course("Corso di golf per principianti", new Person[0]);
        a1.addActivityToClub(mariano, golfCourse);
        p1.subscribeToActivity(mariano, golfCourse);

        System.out.println("Subscribed "+ p1.getName()+" to "+carRace.getName() + " and "+golfCourse.getName());

        p1.unsubscribeFromActivity(mariano, carRace);
        System.out.println("Unsubscribed "+ p1.getName()+" from "+carRace.getName());

        System.out.println(mariano.toString());
    }
}
