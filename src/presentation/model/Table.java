// model/Table.java
package presentation.model;

public class Table {
    private int id;
    private String tableNumber;
    private int capacity;
    private String status;
    private String location;

    // Constructor
    public Table(String tableNumber, int capacity, String status, String location) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = status;
        this.location = location;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTableNumber() { return tableNumber; }
    public int getCapacity() { return capacity; }
    public String getStatus() { return status; }
    public String getLocation() { return location; }
    
    public void setId(int id) { this.id = id; }
    public void setStatus(String status) { this.status = status; }
}