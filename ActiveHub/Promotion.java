package ActiveHub;
// This abstract class represents a general promotion
// available at ActiveHub Sports Centre.

public abstract class Promotion {

    // Unique code for the promotion.
    private String promotionId;

    // Name of the promotion.
    private String promotionName;

    // Description of the promotion.
    private String description;

    // Constructor
    public Promotion(String promotionId,
                     String promotionName,
                     String description) {

        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.description = description;
    }

    // Returns the promotion ID.
    public String getPromotionId() {
        return promotionId;
    }

    // Returns the promotion name.
    public String getPromotionName() {
        return promotionName;
    }

    // Returns the promotion description.
    public String getDescription() {
        return description;
    }

    // This method will be implemented differently
    // by each specific promotion.
    public abstract boolean isEligible(RentalTransaction transaction);

    // This method calculates the discount amount
    // for the specific promotion.
    public abstract double calculateSaving(RentalTransaction transaction);
}