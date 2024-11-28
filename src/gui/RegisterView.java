package gui;

import controllers.AccountController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton, backButton;

    public RegisterView() {
        setTitle("Bookstore - Register Admin");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 30, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 30, 200, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 70, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 70, 200, 25);
        panel.add(passwordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(100, 110, 90, 25);
        panel.add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(210, 110, 90, 25);
        panel.add(backButton);

        add(panel);

        // Tambahkan event handling
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username dan Password tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AccountController account = new AccountController();
                boolean isRegistered = account.registerAccount(username, password);

                if (isRegistered) {
                    JOptionPane.showMessageDialog(null, "Admin berhasil didaftarkan!");
                    dispose(); // Tutup jendela registrasi
                    new LoginView(); // Kembali ke login
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal registrasi! Username mungkin sudah digunakan.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Tutup jendela registrasi
                new LoginView(); // Kembali ke login
            }
        });

        setVisible(true);
    }
}
