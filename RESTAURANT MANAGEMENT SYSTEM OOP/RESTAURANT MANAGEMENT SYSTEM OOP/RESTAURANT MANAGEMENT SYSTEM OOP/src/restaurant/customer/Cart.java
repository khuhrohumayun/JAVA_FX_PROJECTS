package restaurant.customer;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import restaurant.Meals;
import java.util.List;
import restaurant.Invoice;
import restaurant.Order;
import restaurant.OrderDetail;

public class Cart extends JFrame {
    private JButton selectedButton;  // keeps track of the currently selected button
    private int userId;
    private int totalCost = 0; 

    public Cart(List<Meals> items, int userId) {
        this.userId = userId;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Set BoxLayout for the main frame

        // Items panel
        JPanel itemsPanel = new JPanel();
        for (Meals meal : items) {
            ItemPanel item = new ItemPanel(meal.getName(), meal.getImageUrl(), meal.getPrice());
            itemsPanel.add(item);
            totalCost += meal.getPrice();
        }
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        add(scrollPane);

        // Discount panel
        JPanel discountPanel = new JPanel();
        JLabel discountLabel = new JLabel("Discount code: ");
        JTextField discountField = new JTextField(15);
        discountPanel.add(discountLabel);
        discountPanel.add(discountField);
        add(discountPanel);

        // Payment methods panel
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Change BoxLayout to FlowLayout
        JButton[] paymentButtons = {
            createPaymentButton("Credit Card"),
            createPaymentButton("Debit Card"),
            createPaymentButton("Paypal"),
            createPaymentButton("Bitcoin"),
            createPaymentButton("Apple Pay"),
            createPaymentButton("Google Pay")
        };

        for (JButton button : paymentButtons) {
            paymentPanel.add(button);
        }

        add(paymentPanel);

        // Total cost label
        JLabel totalCostLabel = new JLabel("Total cost: " + totalCost);
        totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Make the font bigger
        add(totalCostLabel);

        // Pay button
        JButton payButton = new JButton("Pay");
        payButton.addActionListener(e -> {
            try {
                // Create the order
                int OrderID = Order.createOrder(userId);

                // Create the order details
                for (Meals meal : items) {
                    int MealID = meal.getMealID();
                    OrderDetail.createOrderDetail(OrderID, MealID);
                }

                // Create the invoice
                BigDecimal Total = new BigDecimal(totalCost);
                int InvoiceID = Invoice.createInvoice(OrderID, Total);

                System.out.println("Order ID: " + OrderID);
                System.out.println("Invoice ID: " + InvoiceID);
                JOptionPane.showMessageDialog(paymentPanel, "Succesfully created invoice for your order, you paid " + totalCost + "$ by " + selectedButton.getText() + "\n Your order ID is "+ OrderID + " and your invoice id is: "+ InvoiceID );

            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle exceptions appropriately for your application
            }
        });

        add(payButton);  // Put Pay button at the bottom
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createPaymentButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(e -> {
            if (selectedButton != null) {
                selectedButton.setEnabled(true);  // enable previously selected button
            }
            button.setEnabled(false);  // disable current button to indicate it's selected
            selectedButton = button;
        });
        return button;
    }
}
