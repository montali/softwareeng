package it.unipr.BottiMontali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class AdminDashboard extends Dashboard{    
    private Seller loggedIn;
    private Winehouse shop;

    public AdminDashboard (Seller loggingIn, Winehouse shop){
        super(loggingIn, shop);
    }
    
    public void mainMenu(){
        System.out.println("Welcome back to work.\n1) Ship orders\n2) Add wine\n3) Check requests\n");
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
            default:
                this.mainMenu();
                break;
        }
    }

    private void shipWine(){
        System.out.println("Doing my work!");
        this.loggedIn.shipOrders(this.shop);
        System.out.println("Finished shipping orders. Go home and have a beer.");
    }

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
        if (input == "Y"){
            this.refillWarehouse();
        } else if (input == "N") {
            this.addNewWine();
        }else {
            System.out.println("Wrong input.");
            return;
        }
    }

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
        Map.Entry<Wine,InventoryItem> foundWine = this.shop.findWinesName(this.loggedIn, name);
        if (foundWine == null) {
            System.out.println("Wine not found. Sorry.");
        } else {
            System.out.println("Found! "+foundWine.getKey().getName()+"\n"+foundWine.getKey().getNotes());
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
            this.shop.addWine(this.loggedIn, foundWine.getKey(), year, quantity);
        }
    }

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

        System.out.println("Insert year: ");
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

        this.shop.addWine(this.loggedIn, new Wine(name, notes, vine), year, quantity);
    }

    private void printRequests () {
        for (Request request: this.shop.getRequestedWines()){
            System.out.println("Request:\n" + request.getWineName()+"\nBy: "+request.getRequester().getUsername()+"\n");
        }
    }
}