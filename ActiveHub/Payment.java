package ActiveHub;
import java.time.LocalDate;
import java.time.LocalTime;

// This abstract class represents a general payment
// made by a customer at ActiveHub Sports Centre.
public abstract class Payment {

    // Unique payment ID.
    private String paymentId;

    // Amount paid.
    private double amount;

    // Date of payment.
    private LocalDate paymentDate;

    // Time of payment.
    private LocalTime paymentTime;

    // Constructor
    public Payment(String paymentId, double amount) {

        this.paymentId = paymentId;
        this.amount = amount;

        // Automatically record the current date and time.
        this.paymentDate = LocalDate.now();
        this.paymentTime = LocalTime.now();
    }

    // Returns the payment ID.
    public String getPaymentId() {
        return paymentId;
    }

    // Returns the payment amount.
    public double getAmount() {
        return amount;
    }

    // Returns the payment date.
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    // Returns the payment time.
    public LocalTime getPaymentTime() {
        return paymentTime;
    }

    // Each payment method will provide
    // its own payment description.
    public abstract String getPaymentDetails();

    // Displays basic payment information.
    public void displayPayment() {

        System.out.println("\n===== PAYMENT DETAILS =====");

        System.out.println("Payment ID : " + paymentId);

        System.out.println(
            "Amount     : RM"
            + String.format("%.2f", amount)
        );

        System.out.println("Date       : " + paymentDate);
        System.out.println("Time       : " + paymentTime);

        System.out.println("Method     : " + getPaymentDetails());
    }
}