package restaurant.staff;

import javax.swing.*;
import restaurant.Connector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class UsersViewerFrame extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UsersViewerFrame() {
        setTitle("Users Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        tableModel = new DefaultTableModel(new Object[] { "User ID", "First Name", "Last Name", "Email" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };
        userTable = new JTable(tableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Connect to the database and retrieve users data
        try {
            Connection connection = Connector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");

                tableModel.addRow(new Object[] { userID, firstName, lastName, email });
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }

        // Add double-click listener to the table
        JButton createUserButton = new JButton("Create User");
        createUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCreateUserFrame();
            }
        });
        userTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = userTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int userID = (int) tableModel.getValueAt(selectedRow, 0);
                        openUpdateUserFrame(userID);
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(userTable);
        getContentPane().add(createUserButton, BorderLayout.SOUTH);
        getContentPane().add(scrollPane);
    }

    private void openUpdateUserFrame(int userID) {
        try {
            Connection connection = Connector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE UserID = " + userID);

            if (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");

                JFrame updateFrame = new JFrame("Update User - " + firstName + " " + lastName);
                updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                updateFrame.setSize(400, 300);

                UserForm contentPane = new UserForm(firstName, lastName, email, userID, connection);
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

    private void openCreateUserFrame() {
        try {
            Connection connection = Connector.connect();

            JFrame createFrame = new JFrame("Create User");
            createFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            createFrame.setSize(400, 300);

            UserForm contentPane = new UserForm(connection);
            createFrame.setContentPane(contentPane);
            createFrame.setVisible(true);

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }
    }
}
