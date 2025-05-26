// dao/MenuDao.java
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import presentation.model.MenuItem;

public class MenuDao {

    public int getRevenue() {
        String sql = "SELECT SUM(total) FROM orders";
        Connection conn = DatabaseConnection.getConnection();
        
        try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error getting revenue: " + e.getMessage());
        }
        return 0;
    } 

    public DefaultListModel<String> loadItemNames(){
        DefaultListModel<String> model = new DefaultListModel<>();
        String sql = "SELECT name FROM menu_items";
        Connection conn = DatabaseConnection.getConnection();

        try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                model.addElement(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Error loading item names: " + e.getMessage());
        }
        return model;
    }
    public DefaultListModel<String> loadItemCategories(){
        DefaultListModel<String> model = new DefaultListModel<>();
        String sql = "SELECT DISTINCT category FROM menu_items";
        Connection conn = DatabaseConnection.getConnection();
        try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                model.addElement(rs.getString("category"));
            }
        } catch (SQLException e) {
            System.err.println("Error loading item names: " + e.getMessage());
        }
        return model;
    }

    public boolean deleteMenuItem(String name) {
        String sql = "DELETE FROM menu_items WHERE name = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting menu item: " + e.getMessage());
            return false;
        }
    }
    public Boolean itemExists(String name) {
        String sql = "SELECT COUNT(*) FROM menu_items WHERE name = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking if menu item exists: " + e.getMessage());
        }
        return false;
    }
    public void updateMenuItem(MenuItem item) {
        String sql = "UPDATE menu_items SET description = ?, price = ?, category = ? WHERE name = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getDescription());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(4, item.getName());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating menu item: " + e.getMessage());
        }
    }
    public boolean saveMenuItem(MenuItem item) {
        String sql = "INSERT INTO menu_items (name, description, price, category) VALUES (?, ?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDescription());
            pstmt.setDouble(3, item.getPrice());
            pstmt.setString(4, item.getCategory().toLowerCase());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saving menu item: " + e.getMessage());
            return false;
        }
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";
        Connection conn = DatabaseConnection.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                items.add(new MenuItem(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading menu items: " + e.getMessage());
        }
        return items;
    }

    public DefaultListModel<String> loadMains() {
        DefaultListModel<String> model = new DefaultListModel<>();
        String sql = "SELECT name FROM menu_items WHERE category = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, "Mains");
                ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                model.addElement(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public DefaultListModel<String> loadStarters() {
        DefaultListModel<String> model = new DefaultListModel<>();
        String sql = "SELECT name FROM menu_items WHERE category = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, "Starters");
                ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                model.addElement(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public DefaultListModel<String> loadDesserts() {
        DefaultListModel<String> model = new DefaultListModel<>();
        String sql = "SELECT name FROM menu_items WHERE category = ?";
        Connection conn = DatabaseConnection.getConnection();
        try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, "Desserts");
                ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                model.addElement(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
}