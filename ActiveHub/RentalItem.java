package ActiveHub;

// This abstract class represents a general rental item
// available at ActiveHub Sports Centre.
public abstract class RentalItem {

    // Unique code used to identify each rental item.
    private String itemCode;

    // Name of the rental item.
    private String itemName;

    // Category of the item, such as Equipment,
    // Facility or Accessory.
    private String category;

    // Price charged for renting the item.
    private double rentalPrice;


    // Constructor.
    // Creates a rental item with its basic information.
    public RentalItem(String itemCode, String itemName,
                      String category, double rentalPrice) {

        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.rentalPrice = rentalPrice;
    }


    // Returns the unique item code.
    public String getItemCode() {
        return itemCode;
    }


    // Returns the name of the rental item.
    public String getItemName() {
        return itemName;
    }


    // Returns the category of the rental item.
    public String getCategory() {
        return category;
    }


    // Returns the rental price of the item.
    public double getRentalPrice() {
        return rentalPrice;
    }


    // Returns the item's information as a readable String.
    public String getDetails() {

        return itemCode + " | " + itemName + " | "
                + category + " | RM"
                + String.format("%.2f", rentalPrice);
    }
}