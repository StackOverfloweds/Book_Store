package gui;

import controllers.AccountController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginView() {
        setTitle("Bookstore - Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 110, 90, 25);
        panel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(210, 110, 90, 25);
        panel.add(registerButton);

        add(panel);

        // Tambahkan event handling untuk login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                AccountController account = new AccountController();
                if (account.authenticate(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login berhasil!");
                    dispose(); // Tutup jendela login
                    // Buka jendela berikutnya (misal, MainView)
                    new InputBookView();
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau password salah!", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Event handling untuk register
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Tutup jendela login
                new RegisterView(); // Buka jendela registrasi
            }
        });

        setVisible(true);
    }
}
