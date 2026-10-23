package ActiveHub;
public class Accessory extends RentalItem {

    private String description;

    public Accessory(String itemCode, String itemName,
                     String category, double rentalPrice,
                     String description) {

        super(itemCode, itemName, category, rentalPrice);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}