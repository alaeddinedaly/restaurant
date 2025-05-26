package dao;

import java.sql.*;


public class TableDao {
    
    public static boolean tableExists(int tableId) {
        String sql = "SELECT COUNT(*) FROM restaurant_tables WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tableId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static int getTablesNumber(){
        String sql = "SELECT COUNT(*) FROM restaurant_tables";
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

    public static void saveTable(int tableId, int capacity, String status) {
        String sql = "INSERT INTO restaurant_tables (id,table_number, capacity, status) VALUES (?, ?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tableId);
            stmt.setString(2, "T" + String.valueOf(tableId));
            stmt.setInt(3, capacity);
            stmt.setString(4, status);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[] getTable(int tableId) {
        String[] tableInfo = new String[3];
        String sql = "SELECT * FROM restaurant_tables WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tableId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tableInfo[0] = rs.getString("id");
                tableInfo[1] = String.valueOf(rs.getInt("capacity"));
                tableInfo[2] = rs.getString("status");
                return tableInfo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateTableStatus(int tableId, String status) {
        String sql = "UPDATE restaurant_tables SET status = ? WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, tableId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
