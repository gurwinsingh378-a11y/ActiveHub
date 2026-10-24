package ActiveHub;

public class RentalLine {

    private RentalItem item;
    private int quantity;

    public RentalLine(RentalItem item, int quantity) {

        this.item = item;
        this.quantity = quantity;
    }

    public RentalItem getItem() {
        return item;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getTotal() {

        return item.getRentalPrice() * quantity;
    }
}
