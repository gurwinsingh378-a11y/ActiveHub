package ActiveHub;
// This class represents Promotion C:
// Equipment Bundle Discount.

public class EquipmentBundleDiscount extends Promotion {

    // Fixed discount amount of RM20.
    private double discountAmount;

    // Constructor
    public EquipmentBundleDiscount() {
        super(
            "C",
            "Equipment Bundle Discount",
            "RM20 discount when a facility booking and at least 3 equipment units are included."
        );

        discountAmount = 20.00;
    }

    // Checks whether the transaction is eligible
    // for the Equipment Bundle Discount.
    @Override
    public boolean isEligible(RentalTransaction transaction) {

        // A transaction must have a booking.
        if (transaction.getBooking() == null) {
            return false;
        }

        // Count the total quantity of equipment rented.
        int equipmentQuantity = 0;

        for (RentalLine line : transaction.getRentalLines()) {

            // Check whether the rental item is Equipment.
            if (line.getItem() instanceof Equipment) {
                equipmentQuantity += line.getQuantity();
            }
        }

        // Promotion requires at least 3 equipment units.
        return equipmentQuantity >= 3;
    }

    // Calculates the RM20 discount.
    @Override
    public double calculateSaving(RentalTransaction transaction) {

        if (!isEligible(transaction)) {
            return 0.0;
        }

        // Return the fixed RM20 discount.
        return discountAmount;
    }
}