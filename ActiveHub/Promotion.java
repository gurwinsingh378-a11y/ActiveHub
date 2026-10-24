package ActiveHub;

public abstract class Promotion {

    private String promotionId;
    private String promotionName;
    private String description;

    public Promotion(String promotionId,
                     String promotionName,
                     String description) {

        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.description = description;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean isEligible(RentalTransaction transaction);
    public abstract double calculateSaving(RentalTransaction transaction);
}
