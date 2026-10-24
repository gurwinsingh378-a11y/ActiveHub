package ActiveHub;

public class TeamBookingReward extends Promotion {

    private double rewardPerParticipant;
    private double maximumDiscount;

    public TeamBookingReward() {
        super(
            "B",
            "Team Booking Reward",
            "RM5 discount per participant for bookings with at least 8 participants."
        );

        rewardPerParticipant = 5.00;
        maximumDiscount = 60.00;
    }

    @Override
    public boolean isEligible(RentalTransaction transaction) {

        if (transaction.getBooking() == null) {
            return false;
        }

        return transaction.getBooking().getParticipants() >= 8;
    }

    @Override
    public double calculateSaving(RentalTransaction transaction) {

        if (!isEligible(transaction)) {
            return 0.0;
        }

        int participants =
                transaction.getBooking().getParticipants();

        double saving = participants * rewardPerParticipant;

        if (saving > maximumDiscount) {
            saving = maximumDiscount;
        }

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
