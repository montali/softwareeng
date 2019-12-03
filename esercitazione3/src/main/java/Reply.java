import java.io.Serializable;
import java.util.ArrayList;
	
/**
 * Reply describes the response from server to client.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class Reply implements Serializable {

    private ReplyType responseCode;
    private ArrayList<Serializable> results;
    private static final long serialVersionUID = 1L;

    /**
     * This is the empty constructor 
     */
    
    public Reply() {
        this.results = new ArrayList<Serializable>();
    }

    /**
     * This constructor sets all the Reply's parameters
     * @param ReplyType               responseCode
     * @param ArrayList<Serializable> results
     */ 
    public Reply(ReplyType responseCode, ArrayList<Serializable> results) {
        this.responseCode = responseCode;
        this.results = results;
    }
    
    /**
     * This constructor sets only the responseCode
     * @param ReplyType responseCode
     */

    public Reply(ReplyType responseCode) {
        this.responseCode = responseCode;
        this.results = new ArrayList<Serializable>();
    }

    /**
     * This method gets the responseCode
     * @return ReplyType responseCode
     */
    
    public ReplyType getResponseCode() {
        return responseCode;
    }

    /**
     * This method sets the responseCode
     * @param ReplyType responseCode
     */
    
    public void setResponseCode(ReplyType responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * This method gets the results
     * @return ArrayList<Serializable> reuslts
     */
    
    public ArrayList<Serializable> getResults() {
        return results;
    }
    
    /**
     * This method sets the results
     * @param ArrayList<Serializable> results
     */
    public void setResults(ArrayList<Serializable> results) {
        this.results = results;
    }

    /**
     * This method sets the responseCode to "OK", is used when the reply is received correctly
     * @return
     */
    public boolean wasOk() {
        return this.responseCode == ReplyType.OK;
    }
    
    /**
     * This method print the reply
     */
    
    @Override 
    public String toString() {
    	String toReturn = this.getResponseCode() + " " + this.getResults() + "\n";
    	return toReturn;
    }
}


