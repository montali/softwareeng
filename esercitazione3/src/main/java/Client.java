import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class Client {

    final static private int SPORT = 4242;
    final static private String SHOST = "localhost";
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    /**
     * This method provvide a connection between server and client.
     * It sets the socket, input and output stream from client and server and catching the exception
     * @param Message m
     * @return Reply reply
     */

    public Reply send(Message m) {
        try
        {
            Socket clientSocket = new Socket(SHOST, SPORT);
            this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            while(true){
                this.outputStream.writeObject(m);
                this.outputStream.flush();
                this.inputStream = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                Object reply = null;
                try {
                    reply = this.inputStream.readObject();
                } catch (EOFException e){
                    reply = null;
                }
                if ((reply instanceof Reply)){
                    this.outputStream.close();
                    this.inputStream.close();
                    return (Reply) reply;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            // TODO: return an error reply
            try {
                this.outputStream.close();
                this.inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                this.outputStream.close();
                this.inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // TODO: return an error reply
            return null;
        } catch (Exception e){
            e.printStackTrace();
            try {
                this.outputStream.close();
                this.inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // TODO: return an error reply
            return null;
        }
    }
}

