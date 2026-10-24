package ActiveHub;

public class CardPayment extends Payment {

    private String cardType;
    private String last4Digits;
    
    public CardPayment(String paymentId,
                       double amount,
                       String cardType,
                       String last4Digits) {

        super(paymentId, amount);
        this.cardType = cardType;
        this.last4Digits = last4Digits;
    }

    public String getCardType() {
        return cardType;
    }

    public String getLast4Digits() {
        return last4Digits;
    }

    @Override
    public String getPaymentDetails() {

        return "Card (" + cardType + ")"
                + " | ****"
                + last4Digits;
    }
}
