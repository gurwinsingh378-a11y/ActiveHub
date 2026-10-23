package ActiveHub;
// This class represents a cash payment
// made at ActiveHub Sports Centre.

public class CashPayment extends Payment {

    // Amount of cash received from the customer.
    private double receivedAmount;

    // Change returned to the customer.
    private double changeAmount;

    // Constructor
    public CashPayment(String paymentId,
                       double amount,
                       double receivedAmount) {

        super(paymentId, amount);

        this.receivedAmount = receivedAmount;

        // Calculate the customer's change.
        this.changeAmount = receivedAmount - amount;

        // Prevent negative change.
        if (this.changeAmount < 0) {
            this.changeAmount = 0.0;
        }
    }

    // Returns the amount of cash received.
    public double getReceivedAmount() {
        return receivedAmount;
    }

    // Returns the change amount.
    public double getChangeAmount() {
        return changeAmount;
    }

    // Provides the payment method details.
    @Override
    public String getPaymentDetails() {

        return "Cash | Received: RM"
                + String.format("%.2f", receivedAmount)
                + " | Change: RM"
                + String.format("%.2f", changeAmount);
    }
}