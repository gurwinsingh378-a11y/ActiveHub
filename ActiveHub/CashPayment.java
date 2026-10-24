package ActiveHub;

public class CashPayment extends Payment {

    private double receivedAmount;
    private double changeAmount;

    public CashPayment(String paymentId,
                       double amount,
                       double receivedAmount) {

        super(paymentId, amount);
        this.receivedAmount = receivedAmount;
        this.changeAmount = receivedAmount - amount;

        if (this.changeAmount < 0) {
            this.changeAmount = 0.0;
        }
    }

    public double getReceivedAmount() {
        return receivedAmount;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    @Override
    public String getPaymentDetails() {

        return "Cash | Received: RM"
                + String.format("%.2f", receivedAmount)
                + " | Change: RM"
                + String.format("%.2f", changeAmount);
    }
}
