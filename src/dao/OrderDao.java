package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import presentation.model.Order;

public class OrderDao {

    public Object[][] getReportData() {
        String sql = "SELECT items, total, created_at FROM orders WHERE created_at = ?";
        Connection conn = DatabaseConnection.getConnection();
        try{
                
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, LocalDate.now().toString());
            ResultSet rs = pstmt.executeQuery();
            
            List<Object[]> rows = new ArrayList<>();
            while (rs.next()) {
                rows.add(new Object[]{
                    rs.getString("items"),
                    rs.getDouble("total"), 
                    rs.getDate("created_at")
                });
            }
            return rows.toArray(new Object[0][0]);
        } catch (SQLException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }
    public int getRevenue() {
        String sql = "SELECT SUM(total) FROM orders WHERE created_at = ?";
        Connection conn = DatabaseConnection.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, LocalDate.now().toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error getting revenue: " + e.getMessage());
        }
        return 0;
    } 
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders (table_id, status, items, total, guests, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getTableNumber());
            pstmt.setString(2, order.getStatus());
            pstmt.setString(3, order.getItems());
            pstmt.setDouble(4, order.getTotalPrice());
            pstmt.setString(5, order.getNumberOfGuests());
            pstmt.setString(6, order.getDate());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateOrderStatus(int id,String newStatus) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, id);
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean deleteOrder(String orderId) {
        String sql = "DELETE FROM orders WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Order> getChefOrders(){
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT id,status,items FROM orders";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                orders.add(new Order(
                    rs.getInt("id"),
                    rs.getString("status"),
                    rs.getString("items")
                ));
            }
            
            return orders;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        String sql = "SELECT id,table_id,status,items,guests,total,created_at FROM orders";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                orders.add(new Order(
                    rs.getInt("id"),
                    rs.getString("table_id"),
                    rs.getInt("guests"),
                    rs.getString("status"),
                    rs.getString("items"),
                    rs.getDouble("total"),
                    rs.getString("created_at")
                ));
            }
            
            return orders;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
            
    }

    public int getOrdersNumber(){
        String sql = "SELECT COUNT(*) FROM orders";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }

    public int getPendingOrdersNumber(){
        String sql = "SELECT COUNT(*) FROM orders WHERE status = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Pending");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getReadyOrdersNumber(){
        String sql = "SELECT COUNT(*) FROM orders WHERE status = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Ready");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
