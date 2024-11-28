package controllers;

import models.Account; // Import the Account class
import models.Session;
import utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountController {

    // Method for login
    public boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                // Check if a result is returned
                if (rs.next()) {
                    // Create an Account object based on the result
                    Account account = new Account(rs.getInt("id"), rs.getString("username"), rs.getString("password")) {
                        @Override
                        public String getUsername() {
                            return username;
                        }

                        @Override
                        public String getPassword() {
                            return password;
                        }
                    };

                    // Store the account info in session
                    Session.setLoggedInAdmin(account.getUsername());
                    Session.setLoggedInAdminId(account.getId());
                    return true; // Login successful
                }
            }
        } catch (Exception e) {
            System.err.println("Error authenticating user: " + e.getMessage());
        }
        return false; // Login failed
    }

    // Method for registering a new account
    public boolean registerAccount(String username, String password) {
        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the account was successfully registered
        } catch (Exception e) {
            System.err.println("Error registering account: " + e.getMessage());
            return false;
        }
    }
}
