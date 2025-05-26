package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import presentation.model.User;

public class UserDao {
    public User authenticate(String email, String password) {
        String sql = "SELECT email, role FROM users WHERE email = ? AND password = ?";
        Connection conn = DatabaseConnection.getConnection();
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password); 
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getString("email"),
                    rs.getString("role"),
                    null 

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getUsersNumber(){
        String sql = "SELECT COUNT(*) FROM users";
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

    public boolean addUser(String email, String name, String password, String role) {
        String sql = "INSERT INTO users (email, name, password, role) VALUES (?, ?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.setString(3, password); // In production, hash this password
            pstmt.setString(4, role);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Database error details:");
            System.err.println("SQL: " + sql);
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Connection conn = DatabaseConnection.getConnection();
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking email: " + e.getMessage());
        }
        return false;
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT email, role, created_at FROM users";
        Connection conn = DatabaseConnection.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

}