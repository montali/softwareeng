package it.unipr.BottiMontali;


public class Request {
    private User requester;
    private String wineName;

    public Request (){
        this.requester = new User();
        this.wineName = "";
    }
    public Request (User requester, String wineName){
        this.requester = requester;
        this.wineName = wineName;
    }

    public User getRequester() {
        return this.requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public String getWineName() {
        return this.wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public boolean checkIfRequested (String addedWine) {
        if (this.wineName == addedWine) {
            this.requester.notifyWineAdded();
            return true;
        }
        return false;
    }
}
