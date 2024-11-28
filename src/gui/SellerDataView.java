package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import models.Session;
import controllers.BookController;
import controllers.SellerController;
import javax.swing.table.DefaultTableModel;

public class SellerDataView extends JFrame {
    private JTable sellerTable;
    private DefaultTableModel tableModel;
    private int selectedSellerId;
    private int selectedBookId;
    private String selectedCustomerId;

    public SellerDataView() {
        setTitle("Bookstore - Data Penjual");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set up table model for dynamic data
        String[] columns = {"ID Seller", "ID Buku", "ID Customer"};
        tableModel = new DefaultTableModel(columns, 0);
        sellerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(sellerTable);
        add(scrollPane, BorderLayout.CENTER);

        // Automatically fetch and populate data when the frame is created
        fetchData();

        // Add "Next" button to navigate to SalesSummaryView
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> navigateToSalesSummary());
        add(nextButton, BorderLayout.SOUTH);

        // Add MouseListener to detect row selection
        sellerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = sellerTable.getSelectedRow();
                if (row != -1) {
                    selectedSellerId = (int) tableModel.getValueAt(row, 0);
                    selectedBookId = (int) tableModel.getValueAt(row, 1);
                    selectedCustomerId = (String) tableModel.getValueAt(row, 2);
                }
            }
        });

        setVisible(true);
    }

    // Method to fetch seller data
    private void fetchData() {
        int adminId = Session.getLoggedInAdminId(); // Get the logged-in admin ID

        // Get all book IDs from BookController
        ArrayList<Integer> bookIds = BookController.getAllBookIds();

        // Generate random customer IDs (e.g., C1234, C5678, etc.)
        ArrayList<String> customerIds = generateRandomCustomerIds(5); // Generate 5 random customer IDs

        // Get all sellers from SellerController
        ArrayList<Integer> sellers = SellerController.getAllSellers();

        // Clear previous data in the table
        tableModel.setRowCount(0);

        // Populate the table with seller data
        for (Integer sellerId : sellers) {
            for (Integer bookId : bookIds) {
                for (String customerId : customerIds) {
                    // Add a row for each combination of seller, book, and customer
                    tableModel.addRow(new Object[]{sellerId, bookId, customerId});
                }
            }
        }
    }

    // Method to generate random customer IDs (e.g., C1234)
    private ArrayList<String> generateRandomCustomerIds(int count) {
        ArrayList<String> customerIds = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            String randomId = "C" + (rand.nextInt(9000) + 1000); // Generates random 4-digit number
            customerIds.add(randomId);
        }
        return customerIds;
    }

    // Method to navigate to the SalesSummaryView
    private void navigateToSalesSummary() {
        new SalesSummaryView(selectedSellerId, selectedBookId, selectedCustomerId); // Pass selected data
        dispose(); // Close the current frame
    }

    public static void main(String[] args) {
        new SellerDataView(); // The table will be populated automatically upon startup
    }
}
