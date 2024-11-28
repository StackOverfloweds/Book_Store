package controllers;

import models.Book;
import utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;

public class BookController {

    // Menambahkan buku baru
    public boolean addBook(Book book) {
        String query = "INSERT INTO books (title, author, category, isbn, stock, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, book.getIsbn());
            stmt.setInt(5, book.getStock());
            stmt.setDouble(6, book.getPrice());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error adding book: " + e.getMessage());
            return false;
        }
    }

    // Mengupdate data buku berdasarkan ID
    public boolean updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, category = ?, isbn = ?, stock = ?, price = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, book.getIsbn());
            stmt.setInt(5, book.getStock());
            stmt.setDouble(6, book.getPrice());
            stmt.setInt(7, book.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error updating book: " + e.getMessage());
            return false;
        }
    }

    // Menghapus buku berdasarkan ID
    public boolean deleteBook(int id) {
        String query = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error deleting book: " + e.getMessage());
            return false;
        }
    }

    // Mendapatkan semua buku
    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("isbn"),
                        rs.getInt("stock"),
                        rs.getDouble("price")
                );
                books.add(book);
            }
        } catch (Exception e) {
            System.err.println("Error fetching books: " + e.getMessage());
        }
        return books;
    }

    // Mendapatkan semua ID buku
    public static ArrayList<Integer> getAllBookIds() {
        ArrayList<Integer> bookIds = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT id FROM books";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookIds.add(rs.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookIds;
    }
}
