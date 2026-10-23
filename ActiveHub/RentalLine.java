package ActiveHub;
// This class represents one line/item
// inside a rental transaction.

public class RentalLine {

    // The rental item being rented.
    private RentalItem item;

    // Number of units rented.
    private int quantity;


    // Constructor
    // Creates a rental line with an item and quantity.
    public RentalLine(RentalItem item, int quantity) {

        this.item = item;
        this.quantity = quantity;
    }


    // Returns the rental item.
    public RentalItem getItem() {
        return item;
    }


    // Returns the quantity.
    public int getQuantity() {
        return quantity;
    }


    // Updates the quantity.
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    // Calculates the total price for this line.
    //
    // Example:
    // RM10 × 2 units = RM20
    public double getTotal() {

        return item.getRentalPrice() * quantity;
    }
}