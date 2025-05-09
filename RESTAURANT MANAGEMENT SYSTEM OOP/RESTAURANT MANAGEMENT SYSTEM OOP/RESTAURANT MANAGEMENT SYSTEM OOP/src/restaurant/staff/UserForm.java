package restaurant.staff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

import restaurant.Connector;

import java.awt.event.*;

public class UserForm extends JPanel {
    public UserForm(String firstName, String lastName, String email, int userID, Connection connection) {
        setLayout(new GridLayout(4, 2));

        // Add labels and text fields to update user information
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField(firstName);

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField(lastName);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(email);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform the update operation
                String updatedFirstName = firstNameField.getText();
                String updatedLastName = lastNameField.getText();
                String updatedEmail = emailField.getText();
                try (Connection connection = Connector.connect()) {
                    updateUser(userID, updatedFirstName, updatedLastName, updatedEmail, connection);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating user");
                }
            }
        });

        // Add components to the content pane
        add(firstNameLabel);
        add(firstNameField);
        add(lastNameLabel);
        add(lastNameField);
        add(emailLabel);
        add(emailField);
        add(updateButton);
    }

    public UserForm(Connection connection) {
        setLayout(new GridLayout(4, 2));

        // Add labels and text fields to create user information
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform the create operation
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                try (Connection connection = Connector.connect()) {
                    createUser(firstName, lastName, email, connection);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error creating user");
                }
            }
        });

        // Add components to the content pane
        add(firstNameLabel);
        add(firstNameField);
        add(lastNameLabel);
        add(lastNameField);
        add(emailLabel);
        add(emailField);
        add(createButton);
    }

    private void updateUser(int userID, String updatedFirstName, String updatedLastName, String updatedEmail,
            Connection connection) throws SQLException {
        // Execute the SQL update query
        try {
            String updateQuery = "UPDATE users SET FirstName = ?, LastName = ?, Email = ? WHERE UserID = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, updatedFirstName);
            updateStatement.setString(2, updatedLastName);
            updateStatement.setString(3, updatedEmail);
            updateStatement.setInt(4, userID);

            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "User updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update user");
            }

            updateStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating user");
        }
    }

    private void createUser(String firstName, String lastName, String email, Connection connection)
            throws SQLException {
        // Execute the SQL insert query
        try {
            String insertQuery = "INSERT INTO users (FirstName, LastName, Email) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, firstName);
            insertStatement.setString(2, lastName);
            insertStatement.setString(3, email);

            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "User created successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create user");
            }

            insertStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating user");
        }
    }
}
