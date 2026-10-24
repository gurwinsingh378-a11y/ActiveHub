package ActiveHub;

public class EquipmentBundleDiscount extends Promotion {

    private double discountAmount;

    public EquipmentBundleDiscount() {
        super(
            "C",
            "Equipment Bundle Discount",
            "RM20 discount when a facility booking and at least 3 equipment units are included."
        );
        discountAmount = 20.00;
    }

    @Override
    public boolean isEligible(RentalTransaction transaction) {

        if (transaction.getBooking() == null) {
            return false;
        }

        int equipmentQuantity = 0;

        for (RentalLine line : transaction.getRentalLines()) {
            
            if (line.getItem() instanceof Equipment) {
                equipmentQuantity += line.getQuantity();
            }
        }
        
        return equipmentQuantity >= 3;
    }

    @Override
    public double calculateSaving(RentalTransaction transaction) {

        if (!isEligible(transaction)) {
            return 0.0;
        }
        
        return discountAmount;
    }
}
