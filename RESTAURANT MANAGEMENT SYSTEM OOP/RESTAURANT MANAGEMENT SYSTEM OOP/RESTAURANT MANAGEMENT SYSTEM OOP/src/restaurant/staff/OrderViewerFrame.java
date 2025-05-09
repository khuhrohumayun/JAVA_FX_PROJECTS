package restaurant.staff;

import javax.swing.*;

import restaurant.Connector;

import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class OrderViewerFrame extends JFrame {
    private JTable orderTable;
    private DefaultTableModel tableModel;

    public OrderViewerFrame() {
        setTitle("Orders Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        tableModel = new DefaultTableModel(new Object[] { "Order ID", "User Name", "Meal Name", "Quantity" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };
        orderTable = new JTable(tableModel);
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Connect to the database and retrieve order details with user names and meal
        // names
        try {
            Connection connection = Connector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT o.OrderID, u.FirstName, u.LastName, m.Name, COUNT(*) AS Quantity " +
                            "FROM orderdetails od " +
                            "JOIN orders o ON od.OrderID = o.OrderID " +
                            "JOIN users u ON o.UserID = u.UserID " +
                            "JOIN meals m ON od.MealID = m.MealID " +
                            "GROUP BY o.OrderID, m.Name, u.FirstName, u.LastName");

            while (resultSet.next()) {
                int orderID = resultSet.getInt("OrderID");
                String userName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
                String mealName = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");

                tableModel.addRow(new Object[] { orderID, userName, mealName, quantity });
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }

        JScrollPane scrollPane = new JScrollPane(orderTable);
        getContentPane().add(scrollPane);
    }
}
