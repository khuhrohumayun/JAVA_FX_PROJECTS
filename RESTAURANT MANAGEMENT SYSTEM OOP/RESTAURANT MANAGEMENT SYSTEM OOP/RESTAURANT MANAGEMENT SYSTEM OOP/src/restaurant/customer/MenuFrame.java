package restaurant.customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import restaurant.*;

public class MenuFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private JButton cartButton;
    private final List<Meals> cartItems;

    public MenuFrame(int userId) {
        super("Menu");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cartItems = new ArrayList<>();

        // Set background color for the frame
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(230, 230, 250)); // Light lavender background

        // Fetch meal categories from the database
        List<Category> categories = Category.fetchAllCategories();

        // Add categories as tabs
        for (Category category : categories) {
            tabbedPane.addTab(category.getCategoryName(), createCategoryPanel(category));
        }

        // Create cart button
        cartButton = new JButton("View Cart");
        cartButton.setBackground(new Color(173, 216, 230)); // Light blue button color
        cartButton.setForeground(Color.BLACK);

        // Add action listener to cart button
        cartButton.addActionListener((e) -> {
            Cart cartFrame = new Cart(cartItems, userId);
            cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cartFrame.setVisible(true);
        });

        // Create panel for cart button
        JPanel cartPanel = new JPanel();
        cartPanel.setBackground(new Color(240, 240, 240)); // Match the frame background
        cartPanel.add(cartButton);

        // Add the JTabbedPane and cart button panel to the JFrame
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(cartPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private JPanel createCategoryPanel(Category category) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(0, 2));
        categoryPanel.setBackground(new Color(245, 245, 245)); // Light gray background

        // Fetch meals within the category from the database
        List<Meals> meals = fetchMealsByCategory(category);

        // Add meals within the category
        for (Meals meal : meals) {
            categoryPanel.add(createMealPanel(category, meal));
        }

        return categoryPanel;
    }

    private List<Meals> fetchMealsByCategory(Category category) {
        List<Meals> meals = new ArrayList<>();
        try {
            Connection connection = Connector.connect();
            String sql = "SELECT * FROM Meals WHERE CategoryID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, category.getCategoryID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int mealID = resultSet.getInt("MealID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");
                String imageUrl = resultSet.getString("imageUrl");

                Meals meal = new Meals(mealID, name, description, price, imageUrl);
                meals.add(meal);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meals;
    }

    private JPanel createMealPanel(Category category, Meals meal) {
        JPanel mealPanel = new JPanel();
        mealPanel.setLayout(new GridBagLayout());
        mealPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        mealPanel.setBackground(new Color(255, 250, 240)); // Light cream background

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.setBackground(new Color(255, 250, 240)); // Match the mealPanel background

        ItemPanel item = new ItemPanel(meal.getName(), meal.getImageUrl(), meal.getPrice());
        item.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton addButton = new JButton("Add to Cart");
        addButton.setBackground(new Color(144, 238, 144)); // Light green button color
        addButton.setForeground(Color.BLACK);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartItems.add(meal);
                JOptionPane.showMessageDialog(MenuFrame.this, meal.getName() + " added to cart!");
            }
        });

        contentPanel.add(item);
        contentPanel.add(addButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 0, 10, 0);

        mealPanel.add(contentPanel, gbc);

        return mealPanel;
    }
}
