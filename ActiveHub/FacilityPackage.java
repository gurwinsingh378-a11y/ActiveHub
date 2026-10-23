package ActiveHub;
public class FacilityPackage extends RentalItem {

    private int durationHours;

    public FacilityPackage(String itemCode, String itemName,
                           String category, double rentalPrice,
                           int durationHours) {

        super(itemCode, itemName, category, rentalPrice);
        this.durationHours = durationHours;
    }

    public int getDurationHours() {
        return durationHours;
    }
}