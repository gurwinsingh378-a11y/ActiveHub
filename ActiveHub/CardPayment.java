package ActiveHub;
// This class represents a credit/debit card payment
// made at ActiveHub Sports Centre.

public class CardPayment extends Payment {

    // Type of card used.
    private String cardType;

    // Last four digits of the card.
    // We do not store the full card number.
    private String last4Digits;

    // Constructor
    public CardPayment(String paymentId,
                       double amount,
                       String cardType,
                       String last4Digits) {

        super(paymentId, amount);

        this.cardType = cardType;
        this.last4Digits = last4Digits;
    }

    // Returns the card type.
    public String getCardType() {
        return cardType;
    }

    // Returns the last four digits.
    public String getLast4Digits() {
        return last4Digits;
    }

    // Provides the payment method details.
    @Override
    public String getPaymentDetails() {

        return "Card (" + cardType + ")"
                + " | ****"
                + last4Digits;
    }
}