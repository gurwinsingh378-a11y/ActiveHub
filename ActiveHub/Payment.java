package ActiveHub;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Payment {

    private String paymentId;
    private double amount;
    private LocalDate paymentDate;
    private LocalTime paymentTime;

    public Payment(String paymentId, double amount) {

        this.paymentId = paymentId;
        this.amount = amount;

        this.paymentDate = LocalDate.now();
        this.paymentTime = LocalTime.now();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public LocalTime getPaymentTime() {
        return paymentTime;
    }

    public abstract String getPaymentDetails();

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
