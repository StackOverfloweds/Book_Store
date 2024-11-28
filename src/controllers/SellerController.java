package controllers;

import java.util.ArrayList;
import models.Session;

public class SellerController {

    // Method to get the logged-in user's ID and add it to a list
    public static ArrayList<Integer> getAllSellers() {
        ArrayList<Integer> users = new ArrayList<>();
        int loggedInUserId = Session.getLoggedInAdminId();  // Get the currently logged-in user's ID
        
        // Simply add the logged-in user's ID to the list
        users.add(loggedInUserId);
        
        // For demonstration, print the users list
        System.out.println("Logged-in User ID: " + loggedInUserId);

        return users;
    }
}
