package restaurant.staff;

import javax.swing.*;

import restaurant.Connector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class MealsViewerFrame extends JFrame {
    private JTable mealTable;
    private DefaultTableModel tableModel;

    public MealsViewerFrame() {
        setTitle("Meals Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        tableModel = new DefaultTableModel(new Object[] { "Meal ID", "Name", "Description", "Price" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };
        mealTable = new JTable(tableModel);
        mealTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Connect to the database and retrieve meals data
        try {
            Connection connection = Connector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM meals");

            while (resultSet.next()) {
                int mealID = resultSet.getInt("MealID");
                String mealName = resultSet.getString("Name");
                String mealDescription = resultSet.getString("Description");
                double mealPrice = resultSet.getDouble("Price");

                tableModel.addRow(new Object[] { mealID, mealName, mealDescription, mealPrice });
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }

        // Add double-click listener to the table
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCreateMealFrame();
            }
        });
        mealTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = mealTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int mealID = (int) tableModel.getValueAt(selectedRow, 0);
                        openUpdateMealFrame(mealID);
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(mealTable);
        getContentPane().add(createButton, BorderLayout.SOUTH);
        getContentPane().add(scrollPane);
    }

    private void openUpdateMealFrame(int mealID) {
        try {
            Connection connection = Connector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM meals WHERE MealID = " + mealID);

            if (resultSet.next()) {
                String mealName = resultSet.getString("Name");
                String mealDescription = resultSet.getString("Description");
                String imageUrl = resultSet.getString("imageUrl");
                double mealPrice = resultSet.getDouble("Price");

                JFrame updateFrame = new JFrame("Update Meal - " + mealName);
                updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                updateFrame.setSize(400, 300);

                MealForm contentPane = new MealForm(mealName, mealDescription, mealPrice, mealID, imageUrl, connection);
                updateFrame.setContentPane(contentPane);
                updateFrame.setVisible(true);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }
    }

    private void openCreateMealFrame() {
        try {
            Connection connection = Connector.connect();

            JFrame createFrame = new JFrame("Create Meal");
            createFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            createFrame.setSize(400, 300);

            MealForm contentPane = new MealForm(connection);
            createFrame.setContentPane(contentPane);
            createFrame.setVisible(true);

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }
    }
}
