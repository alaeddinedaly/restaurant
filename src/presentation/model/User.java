
package presentation.model;

public class User {
    private String email;
    private String role; 
    private String createdAt;
    
    public User(String email, String role, String createdAt) {
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;

    }
    
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getCreatedAt() { return createdAt; }


}