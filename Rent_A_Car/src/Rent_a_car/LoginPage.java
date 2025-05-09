package Rent_a_car;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class LoginPage {

    JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Launch the login page.
     */
    public static void main(String[] args) {
        LoginPage window = new LoginPage();
        window.frame.setVisible(true);
    }

    /**
     * Create the login page.
     */
    public LoginPage() {
        initialize();
    }

    /**
     * Initialize the contents of the login page.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Car Reservation System - Login");
        frame.setBounds(100, 100, 600, 400); // Increased width and height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Custom panel for gradient background
        GradientPanel panel = new GradientPanel();
        panel.setBounds(0, 0, 600, 400);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Login to Rent A Car");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22)); // Larger font size
        titleLabel.setForeground(new Color(255, 255, 255)); // White text
        titleLabel.setBounds(170, 40, 300, 30);
        panel.add(titleLabel);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); // Larger font size
        usernameLabel.setForeground(Color.WHITE); // White text
        usernameLabel.setBounds(100, 120, 100, 30);
        panel.add(usernameLabel);

        // Username Text Field
        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameField.setBounds(220, 120, 250, 30); // Increased width
        panel.add(usernameField);
        usernameField.setColumns(10);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); // Larger font size
        passwordLabel.setForeground(Color.WHITE); // White text
        passwordLabel.setBounds(100, 180, 100, 30);
        panel.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setBounds(220, 180, 250, 30); // Increased width
        panel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        loginButton.setBackground(new Color(51, 153, 255)); // Blue background for button
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setBounds(250, 250, 120, 40); // Larger button
        panel.add(loginButton);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        // Key listener for detecting "Enter" key press
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });
    }

    // Method to handle login logic
    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Dummy check for username and password
        if (username.equals("Sania") && password.equals("1234")) {
            JOptionPane.showMessageDialog(frame, "Login successful!");

            // Close login page and open home page
            frame.dispose(); // close login page

            // Open the Home page
            Home homePage = new Home();
            homePage.frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid credentials, please try again.");
        }
    }

    // Custom JPanel with gradient background
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            
            // Create gradient from top to bottom (blue to green)
            GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 153, 255), 0, height, new Color(0, 255, 128));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);
        }
    }
}
