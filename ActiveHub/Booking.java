package ActiveHub;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class Booking {

    private String bookingId;
    private Customer customer;
    private Facility facility;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private int participants;
    private String status;
    
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

        this.status = "CONFIRMED";
    }

    public String getBookingId() {
        return bookingId;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public Facility getFacility() {
        return facility;
    }
    
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public int getParticipants() {
        return participants;
    }

    public String getStatus() {
        return status;
    }

    public void cancelBooking() {
        status = "CANCELLED";
    }

    public DayOfWeek getDayOfWeek() {
        return bookingDate.getDayOfWeek();
    }

    public boolean conflictsWith(Booking other) {

        return this.facility.getFacilityId()
                    .equals(other.facility.getFacilityId())
                && this.bookingDate.equals(other.bookingDate)
                && this.bookingTime.equals(other.bookingTime)
                && this.status.equals("CONFIRMED")
                && other.status.equals("CONFIRMED");
    }

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
