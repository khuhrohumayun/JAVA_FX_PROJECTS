package restaurant;

import java.sql.*;
import restaurant.Connector;

public class Order {
    private int OrderID;
    private int UserID;
    private Timestamp OrderDate;

    public Order(int OrderID, int UserID, Timestamp OrderDate) {
        this.OrderID = OrderID;
        this.UserID = UserID;
        this.OrderDate = OrderDate;
    }

    public static int createOrder(int UserID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int generatedID = -1;

        try {
            conn = Connector.connect();

            String query = "INSERT INTO orders (UserID, OrderDate) VALUES (?, CURRENT_TIMESTAMP())";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, UserID);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedID = rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return generatedID;
    }
}
