package it.unipr.BottiMontali;

public class Dashboard {
    private Person loggedIn;
    private Winehouse shop;

    public Dashboard () {
        this.loggedIn = new Person();
        this.shop = new Winehouse ();
    }
    public Dashboard (Person loggingIn, Winehouse shop){
        this.loggedIn = loggingIn;
        this.shop = shop;
    }
}
