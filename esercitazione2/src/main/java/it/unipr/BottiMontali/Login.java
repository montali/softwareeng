package it.unipr.BottiMontali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login {

private static Dashboard logIntoWinehouse (String username, String password, Winehouse shop) {
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

public static void loginPage(Winehouse shop) {
	System.out.println("Please insert username: ");
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String input = "";
    try {
        input = reader.readLine();
    } catch (IOException exc) {
        System.out.println("IOEXception thrown. Exiting now.");
        return;
    }
    System.out.println("Please insert password: ");
    String username = input;
    try {
        input = reader.readLine();
    } catch (IOException exc) {
        System.out.println("IOEXception thrown. Exiting now.");
        return;
    }
    String password = input;
    Dashboard db = Login.logIntoWinehouse(username, password, shop);
    db.mainMenu();
}
}