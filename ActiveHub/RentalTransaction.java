package ActiveHub;
import java.time.LocalDate;
import java.util.ArrayList;

public class RentalTransaction {

    private String transactionId;
    private LocalDate transactionDate;
    private Customer customer;
    private Booking booking;
    
    private ArrayList<RentalLine> rentalLines;
    
    private double subtotal;
    private double discount;
    private double serviceCharge;
    private double finalAmount;

    public RentalTransaction(String transactionId,
                             LocalDate transactionDate,
                             Customer customer,
                             Booking booking) {

        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.customer = customer;
        this.booking = booking;

        this.rentalLines = new ArrayList<>();

        this.subtotal = 0.0;
        this.discount = 0.0;
        this.serviceCharge = 0.0;
        this.finalAmount = 0.0;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Booking getBooking() {
        return booking;
    }
    
    public ArrayList<RentalLine> getRentalLines() {
        return rentalLines;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void addItem(RentalItem item, int quantity) {

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        for (RentalLine line : rentalLines) {

            if (line.getItem().getItemCode().equals(item.getItemCode())) {

                line.setQuantity(line.getQuantity() + quantity);
                return;
            }
        }

        RentalLine newLine = new RentalLine(item, quantity);
        rentalLines.add(newLine);
    }

    public boolean updateItemQuantity(String itemCode, int newQuantity) {

        for (RentalLine line : rentalLines) {

            if (line.getItem().getItemCode().equals(itemCode)) {

                if (newQuantity <= 0) {
                    rentalLines.remove(line);
                } else {
                    line.setQuantity(newQuantity);
                }

                return true;
            }
        }

        return false;
    }
    
public void calculateSubtotal() {

    subtotal = 0.0;

    if (booking != null) {
        subtotal += booking.getFacility().getRentalPrice();
    }

    for (RentalLine line : rentalLines) {
        subtotal += line.getTotal();
    }
}

    public void setDiscount(double discount) {

        if (discount < 0) {
            discount = 0;
        }

        this.discount = discount;
    }

    public void calculateServiceCharge() {

        double amountAfterDiscount = subtotal - discount;

        if (amountAfterDiscount < 0) {
            amountAfterDiscount = 0;
        }

        serviceCharge = amountAfterDiscount * 0.10;
    }

    public void calculateFinalAmount() {

        finalAmount = (subtotal - discount) + serviceCharge;

        if (finalAmount < 0) {
            finalAmount = 0;
        }
    }

    public void calculateTotal() {

        calculateSubtotal();
        calculateServiceCharge();
        calculateFinalAmount();
    }

    public void displayItems() {

        System.out.println("\n===== RENTAL ITEMS =====");

        if (rentalLines.isEmpty()) {
            System.out.println("No rental items added.");
            return;
        }

        for (RentalLine line : rentalLines) {

            System.out.println(
                line.getItem().getItemCode()
                + " | "
                + line.getItem().getItemName()
                + " | Qty: "
                + line.getQuantity()
                + " | RM"
                + String.format("%.2f", line.getTotal())
            );
        }
    }

    public void displayTransactionSummary() {

        calculateTotal();

        System.out.println("\n========================================");
        System.out.println("       TRANSACTION SUMMARY");
        System.out.println("========================================");
        System.out.println("Transaction ID : " + transactionId);
        System.out.println("Customer       : " + customer.getName());
        System.out.println("Date           : " + transactionDate);

        displayItems();

        System.out.println("----------------------------------------");
        System.out.println("Subtotal       : RM"
                + String.format("%.2f", subtotal));
        System.out.println("Discount       : RM"
                + String.format("%.2f", discount));
        System.out.println("Service Charge : RM"
                + String.format("%.2f", serviceCharge));
        System.out.println("Final Amount   : RM"
                + String.format("%.2f", finalAmount));
        System.out.println("========================================");
    }
}
