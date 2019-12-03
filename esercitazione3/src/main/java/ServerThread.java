import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ServerThread provides a multithread connection
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class ServerThread implements Runnable {
    private static int MAX = 100;
    private static final long SLEEPTIME = 200;

    private Server server;
    private Socket socket;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    /**
     * This constructor sets the Server and the Socket
     * @param Server s
     * @param Socket sck
     */
    
    public ServerThread(final Server s, final Socket sck) {
        this.server = s;
        this.socket = sck;
    }

    /**
     * This method estabilishes every case of communication between server and client
     * For ever new client it creates a new thread
     * It provides every kind of request.
     * The first thing is the LOGIN and it is necessary for all the successive requests
     * Then we have: GET_EMPLOYEE (standard request or searched by headquarter or job),
     * ADD, EDIT and DELETE EMPLOYEE
     * and finally ADD, GET, EDIT and DELETE HEADQUARTER
     * For every request it checks exceptions and verifies the requirements.
     * If all is good, it sends a message and does the requests.
     */
    @Override
    public void run() {
        try {
            if (this.inputStream == null)
                this.inputStream = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
            this.outputStream = null;
            while (true) {
                Object o = this.inputStream.readObject();
                if (o != null && o instanceof Message) {
                    Message inputMessage = (Message) o;
                    Thread.sleep(SLEEPTIME);
                    if (this.outputStream == null) {
                        this.outputStream = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
                    }
                    CompanyManager manager = CompanyManager.getInstance();
                    Reply replyMessage = null;
                    synchronized (manager) {
                        Person authorizer = manager.login(inputMessage.getUsername(), inputMessage.getPasswordHash());
                        try {
                            if (authorizer == null)
                                throw new UnauthorizedUserException();
                            switch (inputMessage.getRequestType()) {
                                case LOGIN:
                                    if (authorizer != null)
                                        replyMessage = new Reply(ReplyType.OK);
                                    break;

                                // Employee CRUD:
                                case GET_EMPLOYEE:
                                    replyMessage = new Reply(ReplyType.OK, manager.getEmployee(authorizer, inputMessage.getQuery()));
                                    break;
                                case GET_EMPLOYEES_BY_HQ:
                                    replyMessage = new Reply(ReplyType.OK, manager.getEmployeesByHQ(authorizer, inputMessage.getQuery()));
                                    break;
                                case GET_EMPLOYEES_BY_JOB:
                                    replyMessage = new Reply(ReplyType.OK, manager.getEmployeesByJob(authorizer, inputMessage.getQuery()));
                                    break;
                                case ADD_EMPLOYEE:
                                    if (manager.addEmployee(authorizer, (Person) inputMessage.getNewObject()))
                                        replyMessage = new Reply(ReplyType.OK);
                                    else
                                        replyMessage = new Reply(ReplyType.ALREADY_EXISTING);
                                    break;
                                case EDIT_EMPLOYEE:
                                    if (manager.editEmployee(authorizer, (Person) inputMessage.getNewObject()))
                                        replyMessage = new Reply(ReplyType.OK);
                                    else
                                        replyMessage = new Reply(ReplyType.NOT_FOUND);
                                    break;
                                case DELETE_EMPLOYEE:
                                    if (manager.deleteEmployee(authorizer, inputMessage.getQuery()))
                                        replyMessage = new Reply(ReplyType.OK);
                                    else
                                        replyMessage = new Reply(ReplyType.NOT_FOUND);
                                    break;

                                // HQ CRUD
                                case GET_HEADQUARTER:
                                    replyMessage = new Reply(ReplyType.OK, manager.getHeadquarter(authorizer, inputMessage.getQuery()));
                                    break;
                                case ADD_HEADQUARTER:
                                    if (manager.addHeadquarter(authorizer, (Headquarter) inputMessage.getNewObject()))
                                        replyMessage = new Reply(ReplyType.OK);
                                    else
                                        replyMessage = new Reply(ReplyType.ALREADY_EXISTING);
                                    break;
                                case EDIT_HEADQUARTER:
                                    if (manager.editHeadquarter(authorizer, (Headquarter) inputMessage.getNewObject()))
                                        replyMessage = new Reply(ReplyType.OK);
                                    else
                                        replyMessage = new Reply(ReplyType.NOT_FOUND);
                                    break;
                                case DELETE_HEADQUARTER:
                                    if (manager.deleteHeadquarter(authorizer, inputMessage.getQuery()))
                                        replyMessage = new Reply(ReplyType.OK);
                                    else
                                        replyMessage = new Reply(ReplyType.NOT_FOUND);
                                    break;
                                default:
                                    replyMessage = new Reply(ReplyType.ERROR);
                            }
                        } catch (UnauthorizedUserException uue) {
                            // If the user is not authorized, the reply must be empty
                            replyMessage = new Reply(ReplyType.NOT_AUTHORIZED);
                        } catch (InvalidVATException ive) {
                            // If the VAT's length isn't ok, we notify the user
                            replyMessage = new Reply(ReplyType.INVALID_VAT);
                        }

                    }
                    this.outputStream.writeObject(replyMessage);
                    this.outputStream.flush();
                    break;
                }
            }
            this.outputStream.close();
            this.inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
