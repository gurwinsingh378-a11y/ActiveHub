package ActiveHub;
import java.time.DayOfWeek;
import java.time.LocalTime;

// This class represents Promotion A:
// Off-Peak Saver.
public class OffPeakSaver extends Promotion {

    // Off-peak discount rate is 15%.
    private double discountRate;

    // Constructor
    public OffPeakSaver() {
        super(
            "A",
            "Off-Peak Saver",
            "15% discount on facility/court charges during off-peak hours."
        );

        discountRate = 0.15;
    }

    // Checks whether the transaction is eligible
    // for the Off-Peak Saver promotion.
    @Override
    public boolean isEligible(RentalTransaction transaction) {

        // A transaction must have a booking.
        if (transaction.getBooking() == null) {
            return false;
        }

        // Get the booking date and time.
        DayOfWeek day = transaction.getBooking().getDayOfWeek();
        LocalTime time = transaction.getBooking().getBookingTime();

        // Promotion applies from Monday to Friday.
        boolean weekday =
                day != DayOfWeek.SATURDAY
                && day != DayOfWeek.SUNDAY;

        // Promotion starts at 9:00 AM.
        // Promotion ends before 4:00 PM.
        boolean offPeakTime =
                !time.isBefore(LocalTime.of(9, 0))
                && time.isBefore(LocalTime.of(16, 0));

        return weekday && offPeakTime;
    }

    // Calculates the 15% discount on the facility charge.
    @Override
    public double calculateSaving(RentalTransaction transaction) {

        if (!isEligible(transaction)) {
            return 0.0;
        }

        // Get the facility rental price.
        double facilityCharge =
                transaction.getBooking()
                           .getFacility()
                           .getRentalPrice();

        // Calculate 15% saving.
        return facilityCharge * discountRate;
    }
}