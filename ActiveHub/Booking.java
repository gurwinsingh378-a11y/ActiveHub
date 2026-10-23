package ActiveHub;
// Import LocalDate to store the booking date.
import java.time.LocalDate;

// Import LocalTime to store the booking time.
import java.time.LocalTime;
// Import DayOfWeek to check which day the booking is on.
import java.time.DayOfWeek;


// This class represents a customer's booking
// for a sports facility.
public class Booking {

    // Unique ID for the booking.
    private String bookingId;

    // Customer who made the booking.
    private Customer customer;

    // Facility/court being booked.
    private Facility facility;

    // Date of the booking.
    private LocalDate bookingDate;

    // Starting time of the booking.
    private LocalTime bookingTime;

    // Number of participants.
    private int participants;

    // Current booking status.
    // Example: CONFIRMED or CANCELLED.
    private String status;


    // Constructor
    // Creates a Booking object with all required information.
    public Booking(String bookingId,
                   Customer customer,
                   Facility facility,
                   LocalDate bookingDate,
                   LocalTime bookingTime,
                   int participants) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.facility = facility;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.participants = participants;

        // New bookings are confirmed by default.
        this.status = "CONFIRMED";
    }


    // Returns the booking ID.
    public String getBookingId() {
        return bookingId;
    }


    // Returns the customer.
    public Customer getCustomer() {
        return customer;
    }


    // Returns the facility.
    public Facility getFacility() {
        return facility;
    }


    // Returns the booking date.
    public LocalDate getBookingDate() {
        return bookingDate;
    }


    // Returns the booking time.
    public LocalTime getBookingTime() {
        return bookingTime;
    }


    // Returns the number of participants.
    public int getParticipants() {
        return participants;
    }


    // Returns the booking status.
    public String getStatus() {
        return status;
    }


    // Cancels the booking.
    public void cancelBooking() {
        status = "CANCELLED";
    }


    // Returns the day of the week for the booking.
    // This can later be used by the Off-Peak promotion.
    public DayOfWeek getDayOfWeek() {
        return bookingDate.getDayOfWeek();
    }


    // Checks whether this booking uses
    // the same facility, date and time
    // as another booking.
    public boolean conflictsWith(Booking other) {

        return this.facility.getFacilityId()
                    .equals(other.facility.getFacilityId())
                && this.bookingDate.equals(other.bookingDate)
                && this.bookingTime.equals(other.bookingTime)
                && this.status.equals("CONFIRMED")
                && other.status.equals("CONFIRMED");
    }


    // Returns all important booking information.
    public String getBookingDetails() {

        return bookingId + " | "
                + customer.getName() + " | "
                + facility.getFacilityName() + " | "
                + bookingDate + " | "
                + bookingTime + " | "
                + participants + " participants | "
                + status;
    }
}