package restaurant.staff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

import restaurant.Connector;

import java.awt.event.*;

public class OrderForm extends JPanel {
    public OrderForm(int orderID, int userID, int mealID, int quantity, Connection connection) {
        setLayout(new GridLayout(4, 2));

        // Add labels and text fields to update order information
        JLabel userIDLabel = new JLabel("User ID:");
        JTextField userIDField = new JTextField(String.valueOf(userID));

        JLabel mealIDLabel = new JLabel("Meal ID:");
        JTextField mealIDField = new JTextField(String.valueOf(mealID));

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(String.valueOf(quantity));

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform the update operation
                int updatedUserID = Integer.parseInt(userIDField.getText());
                int updatedMealID = Integer.parseInt(mealIDField.getText());
                int updatedQuantity = Integer.parseInt(quantityField.getText());
                try (Connection connection = Connector.connect()) {
                    updateOrder(orderID, updatedUserID, updatedMealID, updatedQuantity, connection);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating order");
                }
            }
        });

        // Add components to the content pane
        add(userIDLabel);
        add(userIDField);
        add(mealIDLabel);
        add(mealIDField);
        add(quantityLabel);
        add(quantityField);
        add(updateButton);
    }

    public OrderForm(Connection connection) {
        setLayout(new GridLayout(4, 2));

        // Add labels and text fields to create order information
        JLabel userIDLabel = new JLabel("User ID:");
        JTextField userIDField = new JTextField();

        JLabel mealIDLabel = new JLabel("Meal ID:");
        JTextField mealIDField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform the create operation
                int userID = Integer.parseInt(userIDField.getText());
                int mealID = Integer.parseInt(mealIDField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                try (Connection connection = Connector.connect()) {
                    createOrder(userID, mealID, quantity, connection);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error creating order");
                }
            }
        });

        // Add components to the content pane
        add(userIDLabel);
        add(userIDField);
        add(mealIDLabel);
        add(mealIDField);
        add(quantityLabel);
        add(quantityField);
        add(createButton);
    }

    private void updateOrder(int orderID, int updatedUserID, int updatedMealID, int updatedQuantity,
            Connection connection) throws SQLException {
        // Execute the SQL update query
        try {
            String updateQuery = "UPDATE orders SET UserID = ?, MealID = ?, Quantity = ? WHERE OrderID = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, updatedUserID);
            updateStatement.setInt(2, updatedMealID);
            updateStatement.setInt(3, updatedQuantity);
            updateStatement.setInt(4, orderID);

            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Order updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update order");
            }

            updateStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating order");
        }
    }

    private void createOrder(int userID, int mealID, int quantity, Connection connection) throws SQLException {
        // Execute the SQL insert query
        try {
            String insertQuery = "INSERT INTO orders (UserID, MealID, Quantity) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, userID);
            insertStatement.setInt(2, mealID);
            insertStatement.setInt(3, quantity);

            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Order created successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create order");
            }

            insertStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating order");
        }
    }
}
