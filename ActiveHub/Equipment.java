package ActiveHub;
public class Equipment extends RentalItem {

    private String brand;
    
    public Equipment(String itemCode, String itemName,
                     String category, double rentalPrice,
                     String brand) {

        super(itemCode, itemName, category, rentalPrice);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}