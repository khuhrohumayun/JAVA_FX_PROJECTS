package RestaurantManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class LoginPage extends JFrame {
    private HashMap<String, String> users = new HashMap<>();
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;

    public LoginPage() {
        // Initialize users (two user accounts)
        users.put("Zaid", "123");
        users.put("Hamza", "123");

        // Set JFrame properties
        setTitle("Login");
        setSize(600, 500);  // Increase width and height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create custom JPanel with gradient background
        JPanel panel = new GradientPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Increase spacing between components

        // "Login Page" label at the top
        JLabel titleLabel = new JLabel("Login Page");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));  // Larger font for the title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Span the title across two columns
        gbc.insets = new Insets(20, 0, 40, 0);  // Space between title and form
        panel.add(titleLabel, gbc);

        // Labels and text fields
        gbc.gridwidth = 1;  // Reset gridwidth
        gbc.insets = new Insets(10, 10, 10, 10);  // Reset insets

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));  // Font for labels
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(userLabel, gbc);

        userField = new JTextField(20);  // Increased size of the text field
        userField.setFont(new Font("Arial", Font.PLAIN, 16));  // Updated font for the text field
        gbc.gridx = 1;
        panel.add(userField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.BOLD, 16));  // Font for labels
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        passField = new JPasswordField(20);  // Increased size of the password field
        passField.setFont(new Font("Arial", Font.PLAIN, 16));  // Updated font for the password field
        gbc.gridx = 1;
        panel.add(passField, gbc);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(70, 130, 180));  // Steel blue color
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));  // Updated font for the button
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(20, 0, 0, 0);  // Space above the button
        panel.add(loginButton, gbc);

        // Add hover effect for the login button
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(60, 179, 113));  // Light green on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(70, 130, 180));  // Revert back to original
            }
        });

        // Adding action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    Home homePage = new Home();  // Go to your HomePage class
                    homePage.setVisible(true);
                    dispose();  // Close the login window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials, please try again.");
                }
            }
        });

        // Add panel to the frame
        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);  // Center the frame
    }

    // Method to authenticate users
    private boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    // Main method to run the login page
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
            }
        });
    }

    // Custom JPanel class for gradient background
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            // Create gradient background from blue to light blue
            Color color1 = new Color(72, 61, 139);  // Dark slate blue
            Color color2 = new Color(135, 206, 250);  // Light sky blue
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);

            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }
}
