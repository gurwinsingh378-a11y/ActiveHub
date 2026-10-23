package ActiveHub;
// This class represents an e-wallet payment
// made at ActiveHub Sports Centre.

public class EWalletPayment extends Payment {

    // Name of the e-wallet used.
    private String walletType;

    // Unique reference number for the payment.
    private String referenceNumber;

    // Constructor
    public EWalletPayment(String paymentId,
                          double amount,
                          String walletType,
                          String referenceNumber) {

        super(paymentId, amount);

        this.walletType = walletType;
        this.referenceNumber = referenceNumber;
    }

    // Returns the e-wallet type.
    public String getWalletType() {
        return walletType;
    }

    // Returns the payment reference number.
    public String getReferenceNumber() {
        return referenceNumber;
    }

    // Provides the payment method details.
    @Override
    public String getPaymentDetails() {

        return "E-Wallet (" + walletType + ")"
                + " | Reference: "
                + referenceNumber;
    }
}