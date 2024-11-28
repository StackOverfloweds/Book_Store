package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Sale;
import models.User;
import models.Book;
import controllers.SaleController;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class SalesSummaryView extends JFrame {
    private JTable salesTable;
    private String[][] data;
    private int sellerId;
    private int bookId;
    private String customerId;
    private double total;

    public SalesSummaryView(int sellerId, int bookId, String customerId) {
        this.sellerId = sellerId;
        this.bookId = bookId;
        this.customerId = customerId;

        // Fetch the seller, book, and customer from the database (or however you're
        // managing them)
        User seller = getUserById(sellerId); // Retrieve the seller based on the sellerId
        Book book = getBookById(bookId); // Retrieve the book based on the bookId
        String customer = getCustomerId(customerId); // Retrieve the customer (ID from customerId)

        // Create Sale object to pass to the SaleController for total calculation
        Sale sale = new Sale(0, seller, book, customer, LocalDateTime.now(), 0); // Temporarily set total to 0
        SaleController saleController = new SaleController();
        this.total = saleController.calculateTotal(sale); // Pass the Sale object to the method

        setTitle("Bookstore - Sales Summary");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display Sales Details (receipt format)
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
        receiptPanel.add(new JLabel("Sales Receipt"));
        receiptPanel.add(new JLabel("Seller: " + seller.getUsername()));
        receiptPanel.add(new JLabel("Book: " + book.getTitle()));
        receiptPanel.add(new JLabel("Customer: " + customer));
        receiptPanel.add(new JLabel("Total: $" + total));

        // Get current time for the sale
        LocalDateTime time = LocalDateTime.now(); // Use LocalDateTime for the time
        receiptPanel.add(new JLabel("Time: " + time));

        // Confirm Sale Button
        JButton confirmButton = new JButton("Confirm Sale");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create sale record and save to database
                Sale sale = new Sale(0, seller, book, customer, time, total); // Now we pass actual objects and time

                // Create an instance of SaleController to call the instance method
                SaleController saleController = new SaleController();
                saleController.saveSale(sale); // Now calling the instance method

                JOptionPane.showMessageDialog(SalesSummaryView.this, "Sale Confirmed!");
                dispose(); // Close the SalesSummaryView after confirming
            }
        });

        receiptPanel.add(confirmButton);

        // Add receipt panel to the frame
        add(receiptPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Dummy method to simulate fetching a User by ID (replace with actual database
    // query logic)
    private User getUserById(int id) {
        // You need to implement this to fetch the actual User from your database or
        // data source
        return new User(id, "username_" + id, "password"); // Placeholder implementation
    }

    // Dummy method to simulate fetching a User by ID (replace with actual database
    // query logic)
    private String getCustomerId(String id) {
        // Simulasikan pengambilan data User dari database berdasarkan ID (String)
        return id; // Tidak perlu konversi, langsung gunakan id sebagai String
    }

    // Dummy method to simulate fetching a Book by ID (replace with actual database
    // query logic)
    private Book getBookById(int id) {
        // You need to implement this to fetch the actual Book from your database or
        // data source
        return new Book(id, "Book Title " + id, "Author Name", "Category", "ISBN", 10, 19.99); // Placeholder
                                                                                               // implementation
    }
}
