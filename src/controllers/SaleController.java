package controllers;

import models.Sale;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleController {

    // Method to save sale to the database
    public void saveSale(Sale sale) {
        String query = "INSERT INTO sales (seller_id, book_id, customer_id, time, total) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            // Set parameters from Sale object
            ps.setInt(1, sale.getSeller().getId()); // Assuming seller is a User object and we want to set its ID
            ps.setInt(2, sale.getBook().getId());   // Assuming book is a Book object and we want to set its ID
            ps.setString(3, sale.getCustomer()); // Assuming customer is a User object and we want to set its ID
            ps.setString(4, sale.getTime().toString()); // Convert LocalDateTime to String for SQL
            ps.setDouble(5, sale.getTotal()); // The total price of the sale
            
            ps.executeUpdate(); // Execute the insertion
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
        }
    }

    // Method to calculate the total sale price
    public double calculateTotal(Sale sale) {
        double total = 0.0;
        
        // Calculate the total price based on the book's price
        // and assuming the quantity sold is 1 (adjust if you track quantity)
        
        try (Connection conn = DBConnection.getConnection()) {
            // Query to fetch the book price from the books table
            String query = "SELECT price FROM books WHERE id = ?";
            
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, sale.getBook().getId()); // Use the Book ID from the Sale object
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    double bookPrice = rs.getDouble("price");
                    total = bookPrice * 1; // Assuming 1 book sold. Modify if quantity is tracked
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
        }
        
        return total;
    }
}
