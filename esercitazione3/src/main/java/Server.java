import org.graalvm.compiler.lir.LIRInstruction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class Server {

    private static final int SPORT = 4242;
    private static final int COREPOOL = 5;
    private static final int MAXPOOL = 100;
    private static final long IDLETIME = 5000;

    private ServerSocket socket;
    private ThreadPoolExecutor pool;

    /**
     * This constructor create a new socket
     * 
     * @throws IOException
     */
    
    public Server() throws IOException
    {
        this.socket = new ServerSocket(SPORT);
    }


    /**
     * This method provvide a connection between server and client
     * 
     * @throws IOException
     */
    public void run() throws IOException {
        this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        System.out.println("I'm running on port"+SPORT);
        this.socket.setSoTimeout(10000);
        try
        {
            while(true)
            {
                    Socket s = this.socket.accept();
                    this.pool.execute(new ServerThread(this, s));
            }
        }  catch (SocketTimeoutException ste){
            System.out.println("No requests received in last 10 secs, shutting down.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.pool.shutdown();
        }
    }

    /**
     * This method closes the socket
     */
    
    private void close()
    {
        try
        {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (final String[] args) throws IOException, InvalidVATException {
        Headquarter firstHQ = RandomDataGenerator.generateHeadquarter();

        Person p1_first = RandomDataGenerator.generatePerson(firstHQ);
        Person p2_first = RandomDataGenerator.generatePerson(firstHQ);
        Functionary f1_first = RandomDataGenerator.generateUser(firstHQ);
        Admin admin = new Admin(RandomDataGenerator.generatePerson(firstHQ), "filo", "12345");
        Functionary usedInDemo1 = new Functionary(RandomDataGenerator.generatePerson(firstHQ), "simo", "6789");
        Headquarter secondHQ = RandomDataGenerator.generateHeadquarter();
        Person p1_second = RandomDataGenerator.generatePerson(firstHQ);
        Person p2_second = RandomDataGenerator.generatePerson(firstHQ);
        Functionary f1_second = RandomDataGenerator.generateUser(firstHQ);
        Manager usedInDemo2 = new Manager(RandomDataGenerator.generatePerson(secondHQ), "marco", "botti");
        HashMap<String, Person> people = new HashMap<String, Person>();
        people.put(p1_first.getVat_number(), p1_first);
        people.put(p2_first.getVat_number(), p2_first);
        people.put(f1_first.getVat_number(), f1_first);
        people.put(usedInDemo1.getVat_number(), usedInDemo1);
        people.put(usedInDemo2.getVat_number(), usedInDemo2);
        people.put(admin.getVat_number(), admin);

        people.put(p1_second.getVat_number(), p1_second);
        people.put(p2_second.getVat_number(), p2_second);
        people.put(f1_second.getVat_number(), f1_second);

        HashMap<String, Headquarter> hqs = new HashMap<String, Headquarter>();
        hqs.put(firstHQ.getName(), firstHQ);
        hqs.put(secondHQ.getName(), secondHQ);
        CompanyManager.getInstance().setEmployees(people);
        CompanyManager.getInstance().setHeadquarters(hqs);
        new Server().run();
    }
}
