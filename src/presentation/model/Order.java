package presentation.model;


public class Order {

    private int id;
    private String tableNumber;
    private String status;
    private String items;
    private double totalPrice;
    private int numberOfGuests;
    private String createdDate;

    public Order(int id,String status,String items){
        this.id = id;
        this.status = status;
        this.items = items;
    }

    public Order(int id,String tableNumber,int numberOfGuests, String status, String items, double totalPrice, String createdDate) {

        this.id = id;
        this.tableNumber = tableNumber;
        this.status = status;
        this.items = items;
        this.totalPrice = totalPrice;
        this.numberOfGuests = numberOfGuests;
        this.createdDate = createdDate;
    }

    public String getDate(){
        return createdDate;
    }

    public int getId() {
        return id;
    }

    public String getNumberOfGuests() {
        return String.valueOf(numberOfGuests);
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getStatus() {
        return status;
    }
    
    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    

}
