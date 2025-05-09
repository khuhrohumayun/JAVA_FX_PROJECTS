package restaurant.staff;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import restaurant.Connector;

public class ReportsFrame extends JFrame {

    public ReportsFrame() {
        setTitle("Reports");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);

        JButton mealsReportButton = new JButton("Generate Meals Report");
        mealsReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateMealsReport();
            }
        });

        JButton ordersReportButton = new JButton("Generate Orders Report");
        ordersReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateOrdersReport();
            }
        });

        JButton salesReportButton = new JButton("Generate Sales Report");
        salesReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateSalesForMonth();
            }
        });

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(mealsReportButton);
        contentPane.add(ordersReportButton);
        contentPane.add(salesReportButton);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void generateMealsReport() {
        try (Connection connection = Connector.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM meals");

            FileWriter fileWriter = new FileWriter("meals_report.csv");

            // Write the header
            fileWriter.append("Meal ID,Name,Description,Price\n");

            // Write the data rows
            while (resultSet.next()) {
                int mealID = resultSet.getInt("MealID");
                String mealName = resultSet.getString("Name");
                String mealDescription = resultSet.getString("Description");
                double mealPrice = resultSet.getDouble("Price");

                fileWriter.append(String.valueOf(mealID));
                fileWriter.append(",");
                fileWriter.append(mealName);
                fileWriter.append(",");
                fileWriter.append(mealDescription);
                fileWriter.append(",");
                fileWriter.append(String.valueOf(mealPrice));
                fileWriter.append("\n");
            }

            fileWriter.flush();
            fileWriter.close();

            JOptionPane.showMessageDialog(this, "Meals report generated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating meals report");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error writing meals report file");
        }
    }

    private void generateOrdersReport() {
        try (Connection connection = Connector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT o.OrderID, CONCAT(u.FirstName, ' ', u.LastName) as UserName, m.Name " +
                                "FROM orders o " +
                                "JOIN orderdetails od ON o.OrderID = od.OrderID " +
                                "JOIN users u ON u.UserID = o.UserID " +
                                "JOIN meals m ON od.MealID = m.MealID");
                FileWriter fileWriter = new FileWriter("orders_report.csv")) {

            // Write the header
            fileWriter.append("Order ID,User Name,Meal Name\n");

            // Write the data rows
            while (resultSet.next()) {
                int orderID = resultSet.getInt("OrderID");
                String userName = resultSet.getString("UserName");
                String mealName = resultSet.getString("Name");

                fileWriter.append(String.valueOf(orderID));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(userName));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(mealName));
                fileWriter.append("\n");
            }

            JOptionPane.showMessageDialog(this, "Orders report generated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating orders report");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error writing orders report file");
        }
    }

    private void generateSalesForMonth() {
        String month = JOptionPane.showInputDialog(this, "Enter the month (MM):");

        if (month != null && !month.isEmpty()) {
            try (Connection connection = Connector.connect();
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(
                            "SELECT m.Name, COUNT(*) AS TotalSales " +
                                    "FROM orders o " +
                                    "JOIN orderdetails od ON o.OrderID = od.OrderID " +
                                    "JOIN meals m ON od.MealID = m.MealID " +
                                    "WHERE MONTH(o.OrderDate) = '" + month + "' " +
                                    "GROUP BY m.Name " +
                                    "ORDER BY TotalSales DESC");
                    FileWriter fileWriter = new FileWriter("sales_report_" + month + ".csv")) {

                // Write the header
                fileWriter.append("Meal Name,Total Sales\n");

                // Write the data rows
                while (resultSet.next()) {
                    String mealName = resultSet.getString("Name");
                    int totalSales = resultSet.getInt("TotalSales");

                    fileWriter.append(mealName);
                    fileWriter.append(",");
                    fileWriter.append(String.valueOf(totalSales));
                    fileWriter.append("\n");
                }

                JOptionPane.showMessageDialog(this, "Sales report for month " + month + " generated successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error generating sales report");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error writing sales report file");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid month. Please enter a valid month (MM).");
        }
    }

}
