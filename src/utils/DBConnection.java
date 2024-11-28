package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "book_store";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        // Pertama, mencoba untuk menghubungkan ke MySQL tanpa menentukan database
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        // Membuat database jika belum ada
        createDatabaseIfNotExists(connection);

        // Kembali menghubungkan ke database yang telah dibuat
        return DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
    }

    private static void createDatabaseIfNotExists(Connection connection) throws SQLException {
        // Membuat statement untuk mengeksekusi query
        Statement statement = connection.createStatement();

        // SQL query untuk membuat database jika belum ada
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;

        // Menjalankan query untuk membuat database
        statement.executeUpdate(createDatabaseSQL);

        // Menutup statement setelah digunakan
        statement.close();
    }
}
