package it.unipr.BottiMontali;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class Login {

public static Dashboard logIntoWinehouse (String username, String password, Winehouse shop) {
    Person loggingIn = shop.login(username, password);
    if (loggingIn == null){
        return null;
    } 
    if (loggingIn instanceof User)
        return new UserDashboard(new User(loggingIn), shop);
    else if (loggingIn instanceof Seller)
        return new AdminDashboard(new Seller(loggingIn), shop);
    else 
        return null;
}
}