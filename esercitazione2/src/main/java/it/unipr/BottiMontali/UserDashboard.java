package it.unipr.BottiMontali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDashboard extends Dashboard{
    private User loggedIn;
    private Winehouse shop;

    public UserDashboard (User loggingIn, Winehouse shop){
        super(loggingIn, shop);
    }

    public void mainMenu(){
        System.out.println("Welcome to Zamma\n1) Search wine by name\n2) Search wine by year\n");
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
            default:
                this.mainMenu();
                break;
        }
    }

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
        Map.Entry<Wine,InventoryItem> foundWine = this.shop.findWinesName(this.loggedIn, name);
        if (foundWine == null) {
            System.out.println("Wine not found. Sorry.");
        } else {
            System.out.println("Found! "+foundWine.getKey().getName()+"\n"+foundWine.getKey().getNotes());
            System.out.println("Wanna buy it? Y/N:");
            try {
                input = reader.readLine();
            } catch (IOException exc) {
                System.out.println("IOEXception thrown. Exiting now.");
                return;
            }
            if (input == "Y")
                    this.orderWine(foundWine.getKey());
            }
        }

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
        this.loggedIn.orderWine(wine, this.shop, year, quantity);
    }

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
        HashMap<Wine,InventoryItem> foundWines = this.shop.findWinesYear(this.loggedIn, Integer.valueOf(input));
        if (foundWines == null) {
            System.out.println("Wine not found. Sorry.");
        } else {
            System.out.println("Found! ");
            for (Map.Entry<Wine,InventoryItem> foundWine : foundWines.entrySet()){
                System.out.println(foundWine.getKey().getName()+"\n"+foundWine.getKey().getNotes()+"\n\n");
                foundWinesArray.add(foundWine.getKey());
            }
            System.out.println("Wanna buy one? [#/N]: ");
            try {
                input = reader.readLine();
            } catch (IOException exc) {
                System.out.println("IOEXception thrown. Exiting now.");
                return;
            }
            if (input == "N")
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
    }
    