import java.io.Serializable;

/**
 * Message describes the message from client to the server.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class Message implements Serializable {
    private String username;
    private String passwordHash;
    private MessageType requestType;
    private String query;
    private Serializable newObject;


    private static final long serialVersionUID = 1L;

    /**
     * This constructor is the empty constructor for the Message
     */
    
    public Message() {
    }

    /**
     * This constructor sets every parameters of the message
     *  
     * @param String       username
     * @param String       passwordHash
     * @param MessageType  requestType
     * @param String       query
     * @param Serializable newObject
     */
    
    public Message(String username, String passwordHash, MessageType requestType, String query, Serializable newObject) {
        this.username = username;
        // TODO: Hash the password
        this.passwordHash = passwordHash;
        this.requestType = requestType;
        this.query = query;
        this.newObject = newObject;
    }
    
    /**
     * This constructor sets username, password, request and query of the message, but not the object
	 *
     * @param String       username
     * @param String       passwordHash
     * @param MessageType  requestType
     * @param String       query
     */
    
    public Message(String username, String passwordHash, MessageType requestType, String query) {
        this.username = username;
        // TODO: Hash the password
        this.passwordHash = passwordHash;
        this.requestType = requestType;
        this.query = query;
    }

    /**
     * This method returns the Message's username
     * @return String username
     */
    
    public String getUsername() {
        return username;
    }

    /**
     * This method sets the Message's username
     * @param String username
     */
    
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method gets the Message's password
     * @return String passwordHash
     */
    
    public String getPasswordHash() {
        return passwordHash;
    }
    
    /**
     * This method sets the Message's password
     * @param String password
     */
    
    public void setPasswordHash(String password) {
        // TODO: Hash the password
        this.passwordHash = password;
    }

    /**
     * This method gets the Message's RequestType
     * @return MessageType requestType
     */
    
    public MessageType getRequestType() {
        return requestType;
    }

    /**
     * This method sets the Message's requestType
     * @param MessageType requestType
     */
    
    public void setRequestType(MessageType requestType) {
        this.requestType = requestType;
    }

    /**
     * This method gets the Message query
     * @return String query
     */
    
    public String getQuery() {
        return query;
    }

    /**
     * This method sets the Message's query
     * @param String query
     */
    
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * This method gets the Message's object
     * @return Serializable newObject
     */
    
    public Serializable getNewObject() {
        return newObject;
    }
    
    /**
     * This method sets the Message's object
     * @param Serializable newObject
     */
    
    public void setNewObject(Serializable newObject) {
        this.newObject = newObject;
    }
    
    /**
     * This method gets the Message's serialVersionUID
     * @return long serialVersionUID
     */
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    /**
     * This method print the Message
     */
    
    @Override
    public String toString() {
    	String toReturn = this.getRequestType() + " " + this.getQuery() + "\n";
    	return toReturn;
    }

}

