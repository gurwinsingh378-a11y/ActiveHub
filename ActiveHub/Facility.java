package ActiveHub;

public class Facility {

    private String facilityId;
    private String facilityName;
    private String facilityType;
    private double rentalPrice;
    
    public Facility(String facilityId, String facilityName,
                    String facilityType, double rentalPrice) {

        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.rentalPrice = rentalPrice;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public String getDetails() {

        return facilityId + " | "
                + facilityName + " | "
                + facilityType + " | RM"
                + String.format("%.2f", rentalPrice);
    }
}
