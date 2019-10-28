package it.unipr.BottiMontali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class with methods to display a user UI.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class UserDashboard extends Dashboard{

    /**
     * This constructor generates a dashboard from the user and the shop
     * 
     * @param loggingIn
     * @param shop
     */
    public UserDashboard (User loggingIn, Winehouse shop){
        super(loggingIn, shop);
    }

    /**
     * This method prints the main menu
     */
    public void mainMenu(){
        System.out.println("Welcome to Zamma\n1) Search wine by name\n2) Search wine by year\n3) Print all wines");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int action = 0;
        try {
            action = Integer.valueOf(reader.readLine());
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        switch (action) {
            case 1:
                this.searchWineByName();
                break;
            case 2:
                this.searchWineByYear();
                break;
            case 3:
            	this.printAllWines();
            	break;
            default:
                this.mainMenu();
                break;
        }
    }

    /**
     * This method searches a wine by its name
     */
    private void searchWineByName (){
        System.out.print("Insert name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        HashMap<Wine,InventoryItem> foundWine = this.shop.findWinesName(this.loggedIn, input);
        if (foundWine == null) {
            System.out.println("Wine not found. Sorry.");
        } else {
        	for(Map.Entry<Wine, InventoryItem> wineEntry: foundWine.entrySet()) {
                System.out.println(wineEntry.getKey().toString());
                System.out.println(wineEntry.getValue().toString());
                System.out.println("Wanna buy it? Y/N:");
                try {
                    input = reader.readLine();
                } catch (IOException exc) {
                    System.out.println("IOEXception thrown. Exiting now.");
                    return;
                }
                if (input.equals("Y"))
                    this.orderWine(wineEntry.getKey());
            	}
        	}
        }

    /**
     * This method orders a wine
     * 
     * @param wine
     */
    private void orderWine(Wine wine){
        System.out.print("Year: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        Integer year = Integer.valueOf(input);
        System.out.print("How much: ");
        input = "";
        try {
            input = reader.readLine();
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        Integer quantity = Integer.valueOf(input);
        new User(this.loggedIn).orderWine(wine, this.shop, year, quantity);
    }

    /**
     * This method searches a wine by its year
     */
    private void searchWineByYear (){
        System.out.print("Insert year: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        ArrayList<Wine> foundWinesArray = new ArrayList();
        HashMap<Wine,InventoryItem> foundWines = this.shop.findWinesYear(new User(this.loggedIn), Integer.valueOf(input));
        if (foundWines == null) {
            System.out.println("Wine not found. Sorry.");
        } else {
            System.out.println("Found! ");
            for (Map.Entry<Wine,InventoryItem> foundWine : foundWines.entrySet()){
                System.out.println(foundWine.getKey().toString());
                System.out.println(foundWine.getValue().toString());
                foundWinesArray.add(foundWine.getKey());
            }
            System.out.println("Wanna buy one? [#/N]: ");
            try {
                input = reader.readLine();
            } catch (IOException exc) {
                System.out.println("IOEXception thrown. Exiting now.");
                return;
            }
            if (input.equals("N"))
                return;
            }
            Integer orderingWine = 0;
            try {
                orderingWine = Integer.valueOf(input);
            } catch (NumberFormatException nfex){
                System.out.println("Non-numeric value inserted.");
                return;
            }
            this.orderWine(foundWinesArray.get(orderingWine));
        }
    /**
     * This method prints all the wines
     */
    public void printAllWines() {
    	System.out.println(this.shop.stringAllWines());
    }
    }
    