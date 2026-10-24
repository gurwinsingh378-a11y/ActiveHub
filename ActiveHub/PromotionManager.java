package ActiveHub;
import java.util.ArrayList;

public class PromotionManager {

    private ArrayList<Promotion> promotions;
    
    public PromotionManager() {

        promotions = new ArrayList<>();

        promotions.add(new OffPeakSaver());
        promotions.add(new TeamBookingReward());
        promotions.add(new EquipmentBundleDiscount());
    }

    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public Promotion findBestPromotion(
            RentalTransaction transaction) {

        Promotion bestPromotion = null;
        double greatestSaving = 0.0;
        
        for (Promotion promotion : promotions) {

            double saving =
                    promotion.calculateSaving(transaction);

            if (saving > greatestSaving) {
                greatestSaving = saving;
                bestPromotion = promotion;
            }
        }

        return bestPromotion;
    }

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

            transaction.setDiscount(0.0);
            System.out.println("\nNo eligible promotion.");
        }
        
        transaction.calculateTotal();
    }

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
