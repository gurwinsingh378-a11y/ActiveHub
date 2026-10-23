import java.util.Date;

public class Order {

    private Date orderDate;
    public String status;

    // Constructor
    public Order() {
        orderDate = new Date();
        status = "New";
    }

    // Add order
    public void add() {
        status = "Added";
        System.out.println("Order added.");
    }

    // Modify order
    public void modify() {
        status = "Modified";
        System.out.println("Order modified.");
    }

    // Cancel order
    public void cancel() {
        status = "Cancelled";
        System.out.println("Order cancelled.");
    }

    // Confirm order
    public void confirm() {
        status = "Confirmed";
        sendOrder();
    }

    // Get order status
    public String getStatus() {
        return status;
    }

    // Private implementation
    private void sendOrder() {
        System.out.println("Order sent.");
    }
}