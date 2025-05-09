package restaurant;
import java.sql.*;
import java.math.BigDecimal;

public class Invoice {
    private int InvoiceID;
    private int OrderID;
    private BigDecimal Total;
    private java.sql.Timestamp InvoiceDate;

    public Invoice(int InvoiceID, int OrderID, BigDecimal Total, Timestamp InvoiceDate) {
        this.InvoiceID = InvoiceID;
        this.OrderID = OrderID;
        this.Total = Total;
        this.InvoiceDate = InvoiceDate;
    }

    public static int createInvoice(int OrderID, BigDecimal Total) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int generatedID = -1;

        try {
            conn = Connector.connect();

            String query = "INSERT INTO invoices (OrderID, Total, InvoiceDate) VALUES (?, ?, CURRENT_TIMESTAMP())";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, OrderID);
            ps.setBigDecimal(2, Total);
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
