package com.bottimontali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class with methods to display an admin UI.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class AdminDashboard extends Dashboard{    

    /**
     * Constructor for the dashboard. Logs a user in.
     * 
     * @param loggingIn the user
     * @param shop 		the winehouse we're working on
     */
    public AdminDashboard (Seller loggingIn, Winehouse shop){
        super(loggingIn, shop);
    }
    
    /**
     * Prints the main menu and calls the right functions
     *
     */
    public void mainMenu(){
        System.out.println("Welcome back to work.\n1) Ship orders\n2) Add wine\n3) Check requests\n4) Print all data\n");
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
                this.shipWine();
                break;
            case 2:
                this.addWine();
                break;
            case 3:
                this.printRequests();
                break;
            case 4:
            	this.printWinehouse();
            	break;
            default:
                this.mainMenu();
                break;
        }
    }

    /**
     * UI to ship the ordered wines.
     * 
     */
    private void shipWine(){
        System.out.println("Doing my work!");
        new Seller(this.loggedIn).shipOrders(this.shop);
        System.out.println("Finished shipping orders. Go home and have a beer.");
        this.mainMenu();
    }

    /**
     * UI to add a new wine
     * 
     */
    private void addWine() {
        System.out.println("Is the item already in our database? [Y/N]");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        if (input.equals("Y")){
            this.refillWarehouse();
        } else if (input.equals("N")) {
            this.addNewWine();
        }else {
            System.out.println("Wrong input.");
            return;
        }
    }

    /**
     * UI to add wine quantities.
     * 
     */
    private void refillWarehouse (){
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
                System.out.println("Wanna refill it? Y/N:");
                try {
                    input = reader.readLine();
                } catch (IOException exc) {
                    System.out.println("IOEXception thrown. Exiting now.");
                    return;
                }
                if (input.equals("N"))
                    continue;
            	
        	
        	System.out.println("What year are we refilling? ");
            try {
                input = reader.readLine();
            } catch (IOException exc) {
                System.out.println("IOEXception thrown. Exiting now.");
                return;
            }
            Integer year;
            try{
                year = Integer.valueOf(input);
            } catch (NumberFormatException nfex) {
                System.out.println("Wrong value inserted.");
                return;                
            }
            System.out.println("How much? ");
            try {
                input = reader.readLine();
            } catch (IOException exc) {
                System.out.println("IOEXception thrown. Exiting now.");
                return;
            }
            Integer quantity;
            try{
                quantity = Integer.valueOf(input);
            } catch (NumberFormatException nfex) {
                System.out.println("Wrong value inserted.");
                return;
            }
            this.shop.addWine(new Seller(this.loggedIn), wineEntry.getKey(), year, quantity);
        }
        
    }
        this.mainMenu();
    }

    /**
     * UI to add a non-existent wine.
     * 
     */
    private void addNewWine () {
        System.out.print("Insert name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        String name = input;

        System.out.println("Insert vine: ");
        try {
            input = reader.readLine();
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        String vine = input;

        System.out.println("Insert notes: ");
        try {
            input = reader.readLine();
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        String notes = input;

        System.out.println("Insert quantity: ");
        try {
            input = reader.readLine();
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        Integer quantity;
        try{
            quantity = Integer.valueOf(input);
        } catch (NumberFormatException nfex) {
            System.out.println("Wrong value inserted.");
            return;
        }
        System.out.println("Insert year: ");
        try {
            input = reader.readLine();
        } catch (IOException exc) {
            System.out.println("IOEXception thrown. Exiting now.");
            return;
        }
        Integer year;
        try{
            year = Integer.valueOf(input);
        } catch (NumberFormatException nfex) {
            System.out.println("Wrong value inserted.");
            return;
        }
        this.shop.addWine(new Seller(this.loggedIn), new Wine(name, notes, vine), year, quantity);
        this.mainMenu();
    }

    /**
     * UI that prints all the requests made by users.
     * 
     */
    private void printRequests () {
        for (Request request: this.shop.getRequestedWines()){
            System.out.println("Request:\n" + request.getWineName()+"\nBy: "+request.getRequester().getUsername()+"\n");
        }
        this.mainMenu();
    }
    
    /**
     * UI that prints all the saved data.
     */
    private void printWinehouse() {
    	System.out.println(this.shop.getWinehouseData(this.loggedIn));
    }
}
