package ActiveHub;
// This class represents a sports facility or court
// that customers can book at ActiveHub Sports Centre.
public class Facility {

    // Unique ID for the facility
    private String facilityId;

    // Name of the facility
    private String facilityName;

    // Type of facility, for example "Court"
    private String facilityType;

    // Price for using/renting the facility
    private double rentalPrice;


    // Constructor
    // Used to create a Facility object with its details.
    public Facility(String facilityId, String facilityName,
                    String facilityType, double rentalPrice) {

        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.rentalPrice = rentalPrice;
    }


    // Returns the facility ID
    public String getFacilityId() {
        return facilityId;
    }


    // Returns the facility name
    public String getFacilityName() {
        return facilityName;
    }


    // Returns the type of facility
    public String getFacilityType() {
        return facilityType;
    }


    // Returns the rental price
    public double getRentalPrice() {
        return rentalPrice;
    }


    // Returns all important facility information
    // as one String.
    public String getDetails() {

        return facilityId + " | "
                + facilityName + " | "
                + facilityType + " | RM"
                + String.format("%.2f", rentalPrice);
    }
}