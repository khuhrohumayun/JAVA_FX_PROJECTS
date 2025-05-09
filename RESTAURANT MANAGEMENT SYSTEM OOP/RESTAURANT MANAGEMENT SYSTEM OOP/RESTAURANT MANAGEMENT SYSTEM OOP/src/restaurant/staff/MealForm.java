package restaurant.staff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

import restaurant.Connector;

import java.awt.event.*;

public class MealForm extends JPanel {
    public MealForm(String mealName, String mealDescription, double mealPrice, int mealID, String imageUrl,
            Connection connection) {
        setLayout(new GridLayout(5, 2));

        // Add labels and text fields to update meal information
        JLabel nameLabel = new JLabel("Meal Name:");
        JTextField nameField = new JTextField(mealName);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(mealDescription);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(String.valueOf(mealPrice));

        JLabel imageUrlLabel = new JLabel("Image Url:");
        JTextField imageUrlTextField = new JTextField(String.valueOf(imageUrl));

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform the update operation
                String updatedName = nameField.getText();
                String updatedDescription = descriptionField.getText();
                double updatedPrice = Double.parseDouble(priceField.getText());
                String updatedImageUrl = descriptionField.getText();
                try (Connection connection = Connector.connect()) {
                    updateMeal(mealID, updatedName, updatedDescription, updatedPrice, updatedImageUrl, connection);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating meal");
                }
            }
        });
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for confirmation before deleting the meal
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this meal?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try (Connection connection = Connector.connect()) {
                        deleteMeal(mealID, connection);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error deleting meal");
                    }
                }
            }
        });

        // Add components to the content pane
        add(nameLabel);
        add(nameField);
        add(descriptionLabel);
        add(descriptionField);
        add(imageUrlLabel);
        add(imageUrlTextField);
        add(priceLabel);
        add(priceField);
        add(updateButton);
        add(deleteButton);
    }

    public MealForm(Connection connection) {
        setLayout(new GridLayout(5, 2));

        // Add labels and text fields to update meal information
        JLabel nameLabel = new JLabel("Meal Name:");
        JTextField nameField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JLabel imageUrlLabel = new JLabel("Image Url:");
        JTextField imageUrlTextField = new JTextField();

        JButton createButton = new JButton("Createreate");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform the update operation
                String name = nameField.getText();
                String description = descriptionField.getText();
                double price = Double.parseDouble(priceField.getText());
                String imageUrl = imageUrlTextField.getText();

                // Execute the SQL update query
                try (Connection connection = Connector.connect()) {
                    createMeal(name, description, price, imageUrl, connection);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating meal");
                }
            }
        });

        // Add components to the content pane
        add(nameLabel);
        add(nameField);
        add(descriptionLabel);
        add(descriptionField);
        add(imageUrlLabel);
        add(imageUrlTextField);
        add(priceLabel);
        add(priceField);
        add(createButton);

    }

    private void updateMeal(int mealID, String updatedName, String updatedDescription, double updatedPrice,
            String updatedImageUrl,
            Connection connection) throws SQLException {
        // Execute the SQL update query
        try {
            String updateQuery = "UPDATE meals SET Name = ?, Description = ?, Price = ?, imageUrl=? WHERE MealID = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, updatedName);
            updateStatement.setString(2, updatedDescription);
            updateStatement.setDouble(3, updatedPrice);
            updateStatement.setString(4, updatedImageUrl);
            updateStatement.setInt(5, mealID);

            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Meal updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update meal");
            }

            updateStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating meal");
        }
    }

    private void createMeal(String mealName, String mealDescription, double mealPrice, String imageUrl,
            Connection connection)
            throws SQLException {
        // Execute the SQL insert query
        try {
            String insertQuery = "INSERT INTO meals (Name, Description, Price, imageUrl) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, mealName);
            insertStatement.setString(2, mealDescription);
            insertStatement.setDouble(3, mealPrice);
            insertStatement.setString(4, imageUrl);

            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Meal created successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create meal");
            }

            insertStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating meal");
        }
    }

    private void deleteMeal(int mealID, Connection connection) throws SQLException {
        try {
            String deleteQuery = "DELETE FROM meals WHERE MealID = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, mealID);

            int rowsAffected = deleteStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Meal deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete meal");
            }

            deleteStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting meal");
        }
    }
}
