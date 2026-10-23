package ActiveHub;
import java.util.ArrayList;

// This class manages all promotions available
// at ActiveHub Sports Centre.
public class PromotionManager {

    // Stores all available promotions.
    private ArrayList<Promotion> promotions;

    // Constructor
    public PromotionManager() {

        promotions = new ArrayList<>();

        // Add all three promotions.
        promotions.add(new OffPeakSaver());
        promotions.add(new TeamBookingReward());
        promotions.add(new EquipmentBundleDiscount());
    }

    // Returns the list of promotions.
    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    // Finds the promotion that gives the greatest saving.
    public Promotion findBestPromotion(
            RentalTransaction transaction) {

        Promotion bestPromotion = null;
        double greatestSaving = 0.0;

        // Check every promotion.
        for (Promotion promotion : promotions) {

            // Calculate the saving for this promotion.
            double saving =
                    promotion.calculateSaving(transaction);

            // Select this promotion if its saving
            // is greater than the current best saving.
            //
            // We use '>' instead of '>=' so that when
            // there is a tie, the earlier promotion
            // remains selected.
            //
            // Our promotion order is:
            // A -> B -> C
            if (saving > greatestSaving) {
                greatestSaving = saving;
                bestPromotion = promotion;
            }
        }

        return bestPromotion;
    }

    // Applies the best promotion to the transaction.
    public void applyBestPromotion(
            RentalTransaction transaction) {

        Promotion bestPromotion =
                findBestPromotion(transaction);

        if (bestPromotion != null) {

            double saving =
                    bestPromotion.calculateSaving(transaction);

            transaction.setDiscount(saving);

            System.out.println("\n===== PROMOTION APPLIED =====");
            System.out.println(
                "Promotion : "
                + bestPromotion.getPromotionId()
                + " - "
                + bestPromotion.getPromotionName()
            );

            System.out.println(
                "Discount  : RM"
                + String.format("%.2f", saving)
            );

        } else {

            // No eligible promotion.
            transaction.setDiscount(0.0);

            System.out.println("\nNo eligible promotion.");
        }

        // Recalculate the transaction total
        // after applying the promotion.
        transaction.calculateTotal();
    }

    // Displays all eligible promotions and
    // their possible savings.
    public void displayEligiblePromotions(
            RentalTransaction transaction) {

        System.out.println("\n===== ELIGIBLE PROMOTIONS =====");

        boolean foundPromotion = false;

        for (Promotion promotion : promotions) {

            if (promotion.isEligible(transaction)) {

                double saving =
                        promotion.calculateSaving(transaction);

                System.out.println(
                    promotion.getPromotionId()
                    + " - "
                    + promotion.getPromotionName()
                    + " | Saving: RM"
                    + String.format("%.2f", saving)
                );

                foundPromotion = true;
            }
        }

        if (!foundPromotion) {
            System.out.println("No promotions are eligible.");
        }
    }
}