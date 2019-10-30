/**
 * Request is made from a user when he wants a specific wine.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class Request {
    private User requester;
    private String wineName;

    /**
     * This constructor generates an empty request
     */
    public Request (){
        this.requester = new User();
        this.wineName = "";
    }
    /**
     * This constructor generates a request from its user and the wine name
     * 
     * @param requester
     * @param wineName
     */
    public Request (User requester, String wineName){
        this.requester = requester;
        this.wineName = wineName;
    }

    /**
     * This method returns the requester
     * 
     * @return the requester
     */
    public User getRequester() {
        return this.requester;
    }

    /**
     * This method sets the wine requester
     * 
     * @param requester
     */
    public void setRequester(User requester) {
        this.requester = requester;
    }

    /**
     * This method gets the wine name
     * 
     * @return the name
     */
    public String getWineName() {
        return this.wineName;
    }

    /**
     * This method sets the wine name
     * 
     * @param wineName
     */
    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    /**
     * This method checks if a newly arrived wine was requested by someone. If it was, it notifies the user.
     * 
     * @param addedWine
     * @return whether the wine was requested once.
     */
    public boolean checkIfRequested (String addedWine) {
        if (addedWine.equals(this.wineName)) {
            this.requester.notifyWineAdded();
            return true;
        }
        return false;
    }
}
