package ActiveHub;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class OffPeakSaver extends Promotion {

    private double discountRate;

    public OffPeakSaver() {
        super(
            "A",
            "Off-Peak Saver",
            "15% discount on facility/court charges during off-peak hours."
        );

        discountRate = 0.15;
    }

    @Override
    public boolean isEligible(RentalTransaction transaction) {

        if (transaction.getBooking() == null) {
            return false;
        }

        DayOfWeek day = transaction.getBooking().getDayOfWeek();
        LocalTime time = transaction.getBooking().getBookingTime();

        boolean weekday =
                day != DayOfWeek.SATURDAY
                && day != DayOfWeek.SUNDAY;

        boolean offPeakTime =
                !time.isBefore(LocalTime.of(9, 0))
                && time.isBefore(LocalTime.of(16, 0));
        
        return weekday && offPeakTime;
    }

    @Override
    public double calculateSaving(RentalTransaction transaction) {

        if (!isEligible(transaction)) {
            return 0.0;
        }

        double facilityCharge =
                transaction.getBooking()
                           .getFacility()
                           .getRentalPrice();

        return facilityCharge * discountRate;
    }
}
