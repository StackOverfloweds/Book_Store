package gui;

import controllers.BookController;
import models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputBookView extends JFrame {
    private JTextField titleField, authorField, categoryField, isbnField, stockField, priceField;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private BookController bookController;

    public InputBookView() {
        setTitle("Bookstore - Input Book");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        bookController = new BookController();

        // Panel untuk Form Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Judul Buku:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Nama Penulis:"));
        authorField = new JTextField();
        inputPanel.add(authorField);

        inputPanel.add(new JLabel("Kategori:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        inputPanel.add(isbnField);

        inputPanel.add(new JLabel("Stok:"));
        stockField = new JTextField();
        inputPanel.add(stockField);

        inputPanel.add(new JLabel("Harga:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        // Panel untuk Tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Tambah");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Hapus");
        JButton nextButton = new JButton("Next");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(nextButton);

        // Tabel Buku
        tableModel = new DefaultTableModel(new String[]{"ID", "Judul", "Penulis", "Kategori", "ISBN", "Stok", "Harga"}, 0);
        bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);

        // Tambahkan Panel ke Frame
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Handling
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logika untuk tombol Next (jika ada fitur lain)
                new SellerDataView();
            }
        });

        // Add MouseListener to Table to display selected row data in input fields
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = bookTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Fill the input fields with the selected row data
                    titleField.setText(bookTable.getValueAt(selectedRow, 1).toString());
                    authorField.setText(bookTable.getValueAt(selectedRow, 2).toString());
                    categoryField.setText(bookTable.getValueAt(selectedRow, 3).toString());
                    isbnField.setText(bookTable.getValueAt(selectedRow, 4).toString());
                    stockField.setText(bookTable.getValueAt(selectedRow, 5).toString());
                    priceField.setText(bookTable.getValueAt(selectedRow, 6).toString());
                }
            }
        });

        setVisible(true);
        loadBookData();
    }

    // Menambahkan buku baru
    private void addBook() {
        try {
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            String isbn = isbnField.getText();
            int stock = Integer.parseInt(stockField.getText());
            double price = Double.parseDouble(priceField.getText());

            // Membuat objek Book dari input
            Book book = new Book(title, author, category, isbn, stock, price);

            // Memanggil BookController untuk menambahkan buku
            if (bookController.addBook(book)) {
                JOptionPane.showMessageDialog(this, "Buku berhasil ditambahkan!");
                loadBookData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan buku!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok dan Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Mengupdate buku yang sudah ada
    private void updateBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih buku yang ingin diupdate!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(bookTable.getValueAt(selectedRow, 0).toString());
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            String isbn = isbnField.getText();
            int stock = Integer.parseInt(stockField.getText());
            double price = Double.parseDouble(priceField.getText());

            // Membuat objek Book dengan ID dan data baru
            Book book = new Book(id, title, author, category, isbn, stock, price);

            // Memanggil BookController untuk update buku
            if (bookController.updateBook(book)) {
                JOptionPane.showMessageDialog(this, "Buku berhasil diupdate!");
                loadBookData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengupdate buku!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok dan Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Menghapus buku yang dipilih
    private void deleteBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih buku yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(bookTable.getValueAt(selectedRow, 0).toString());
        if (bookController.deleteBook(id)) {
            JOptionPane.showMessageDialog(this, "Buku berhasil dihapus!");
            loadBookData();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menghapus buku!");
        }
    }

    // Memuat data buku dari database ke tabel
    private void loadBookData() {
        tableModel.setRowCount(0); // Bersihkan tabel
        for (Book book : bookController.getBooks()) {
            tableModel.addRow(new Object[]{
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    book.getIsbn(),
                    book.getStock(),
                    book.getPrice()
            });
        }
    }

    // Menghapus isi form input setelah operasi
    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        categoryField.setText("");
        isbnField.setText("");
        stockField.setText("");
        priceField.setText("");
    }
}
