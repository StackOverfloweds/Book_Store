package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDB() throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                Statement stmt = conn.createStatement();

                // Create "users" table if it doesn't exist
                stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "username VARCHAR(50) NOT NULL UNIQUE, " +
                        "password VARCHAR(255) NOT NULL" +
                        ");");

                // Create "books" table if it doesn't exist
                stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "title VARCHAR(255), " +
                        "author VARCHAR(255), " +
                        "category VARCHAR(100), " +
                        "isbn VARCHAR(100), " +
                        "stock INT, " +
                        "price DOUBLE" +
                        ");");

                // Create "sales" table if it doesn't exist
                stmt.execute("CREATE TABLE IF NOT EXISTS sales (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "seller_id INT, " +
                        "book_id INT, " +
                        "customer_id VARCHAR(100), " + // Change to VARCHAR to store customerId as a string without foreign key
                        "time DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                        "total DOUBLE, " +
                        "FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE, " +
                        "FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE" +
                        ");");

                System.out.println("Database initialized successfully.");
            } else {
                throw new SQLException("Database connection failed.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error initializing database: " + e.getMessage());
        }
    }
}
