import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

import static java.lang.Thread.sleep;

public class DemoThread implements Runnable {
    @Override
    public void run() {

        Random rand = new Random();
        // Random sleep to spice things up
        try {
            Thread.sleep(rand.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String username = "";
        String password = "";
        // Randomly decide which functionary we'll use
        switch (rand.nextInt(3)) {
            case 0:
                username = "filo";
                password = "12345";
            case 1:
                username = "simo";
                password = "6789";
            case 2:
                username = "marco";
                password = "botti";
        }

        UserDashboard u1 = new UserDashboard(username, password);
        Person toAdd = RandomDataGenerator.generatePerson(RandomDataGenerator.generateHeadquarter());
        u1.add(toAdd);
        // Now, we're gonna try adding it again.
        u1.add(toAdd);
        System.out.println("Thread: " + Long.toString(Thread.currentThread().getId())+" just tried to add a user twice!");
        // Random sleep to spice things up
        try {
            Thread.sleep(rand.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Let's add a hq and some people to create chaos
        Headquarter newHQ = RandomDataGenerator.generateHeadquarter();
        for (int i=0; i<10; i++){
            u1.add(RandomDataGenerator.generatePerson(newHQ));
        }
        System.out.println("Thread: " + Long.toString(Thread.currentThread().getId())+" just added some users and a hq!\nLet's print our data:");
        // Finally, let's print what we've done
        for (Serializable foundPerson:u1.searchEmployee("")){
            System.out.println(((Person)foundPerson).toString());
        }
    }
}
