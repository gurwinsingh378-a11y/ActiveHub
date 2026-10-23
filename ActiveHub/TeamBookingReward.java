package ActiveHub;
// This class represents Promotion B:
// Team Booking Reward.

public class TeamBookingReward extends Promotion {

    // RM5 discount for each participant.
    private double rewardPerParticipant;

    // Maximum discount allowed is RM60.
    private double maximumDiscount;

    // Constructor
    public TeamBookingReward() {
        super(
            "B",
            "Team Booking Reward",
            "RM5 discount per participant for bookings with at least 8 participants."
        );

        rewardPerParticipant = 5.00;
        maximumDiscount = 60.00;
    }

    // Checks whether the transaction is eligible
    // for the Team Booking Reward.
    @Override
    public boolean isEligible(RentalTransaction transaction) {

        // A transaction must have a booking.
        if (transaction.getBooking() == null) {
            return false;
        }

        // The booking must have at least 8 participants.
        return transaction.getBooking().getParticipants() >= 8;
    }

    // Calculates the discount amount.
    @Override
    public double calculateSaving(RentalTransaction transaction) {

        if (!isEligible(transaction)) {
            return 0.0;
        }

        // Get the number of participants.
        int participants =
                transaction.getBooking().getParticipants();

        // Calculate the discount.
        double saving = participants * rewardPerParticipant;

        // Discount cannot exceed RM60.
        if (saving > maximumDiscount) {
            saving = maximumDiscount;
        }

        // The discount cannot reduce the facility
        // charge below RM0.
        double facilityCharge =
                transaction.getBooking()
                           .getFacility()
                           .getRentalPrice();

        if (saving > facilityCharge) {
            saving = facilityCharge;
        }

        return saving;
    }
}