package restaurant;
import java.sql.*;
import restaurant.Connector;

public class OrderDetail {
    private int OrderDetailID;
    private int OrderID;
    private int MealID;
    private int Quantity;

    public OrderDetail(int OrderDetailID, int OrderID, int MealID) {
        this.OrderDetailID = OrderDetailID;
        this.OrderID = OrderID;
        this.MealID = MealID;
        this.Quantity = Quantity;
    }

    public static int createOrderDetail(int OrderID, int MealID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int generatedID = -1;

        try {
            conn = Connector.connect();

            String query = "INSERT INTO orderdetails (OrderID, MealID) VALUES (?, ?)";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, OrderID);
            ps.setInt(2, MealID);
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
