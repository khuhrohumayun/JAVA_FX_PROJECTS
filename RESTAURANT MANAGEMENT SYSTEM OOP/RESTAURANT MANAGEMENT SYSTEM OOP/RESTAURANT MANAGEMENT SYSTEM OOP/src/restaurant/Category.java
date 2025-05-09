/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author theco
 */
public class Category {
    private int categoryID;
    private String categoryName;

    public Category(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static List<Category> fetchAllCategories() {
        List<Category> mealCategories = new ArrayList<>();

        try {
            Connection connection = Connector.connect();
            // Prepare the SQL statement
            String sql = "SELECT * FROM categories";
            Statement statement = connection.createStatement();

            // Execute the SQL statement and retrieve the result set
            ResultSet resultSet = statement.executeQuery(sql);

            // Iterate through the result set and create MealCategory objects
            while (resultSet.next()) {
                int categoryID = resultSet.getInt("CategoryID");
                String categoryName = resultSet.getString("CategoryName");

                Category mealCategory = new Category(categoryID, categoryName);
                mealCategories.add(mealCategory);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealCategories;
    }
}
