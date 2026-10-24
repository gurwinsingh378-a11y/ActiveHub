package ActiveHub;

public class EWalletPayment extends Payment {

    private String walletType;
    private String referenceNumber;

    public EWalletPayment(String paymentId,
                          double amount,
                          String walletType,
                          String referenceNumber) {

        super(paymentId, amount);
        this.walletType = walletType;
        this.referenceNumber = referenceNumber;
    }

    public String getWalletType() {
        return walletType;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    @Override
    public String getPaymentDetails() {

        return "E-Wallet (" + walletType + ")"
                + " | Reference: "
                + referenceNumber;
    }
}
