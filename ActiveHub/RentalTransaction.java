package ActiveHub;
import java.time.LocalDate;
import java.util.ArrayList;

// This class represents a complete rental transaction
// made by a customer at ActiveHub Sports Centre.
public class RentalTransaction {

    // Unique ID for the transaction.
    private String transactionId;

    // Date when the transaction was created.
    private LocalDate transactionDate;

    // Customer who made the transaction.
    private Customer customer;

    // Booking connected to this transaction.
    private Booking booking;

    // List of rental items in the transaction.
    private ArrayList<RentalLine> rentalLines;

    // Amount before discount.
    private double subtotal;

    // Promotion discount amount.
    private double discount;

    // 10% service charge.
    private double serviceCharge;

    // Final amount that the customer needs to pay.
    private double finalAmount;

    // Constructor.
    public RentalTransaction(String transactionId,
                             LocalDate transactionDate,
                             Customer customer,
                             Booking booking) {

        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.customer = customer;
        this.booking = booking;

        // Create an empty list for rental items.
        this.rentalLines = new ArrayList<>();

        // Initial values.
        this.subtotal = 0.0;
        this.discount = 0.0;
        this.serviceCharge = 0.0;
        this.finalAmount = 0.0;
    }

    // Returns the transaction ID.
    public String getTransactionId() {
        return transactionId;
    }

    // Returns the transaction date.
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    // Returns the customer.
    public Customer getCustomer() {
        return customer;
    }

    // Returns the booking connected to this transaction.
    public Booking getBooking() {
        return booking;
    }

    // Returns all rental lines.
    public ArrayList<RentalLine> getRentalLines() {
        return rentalLines;
    }

    // Returns the subtotal.
    public double getSubtotal() {
        return subtotal;
    }

    // Returns the discount.
    public double getDiscount() {
        return discount;
    }

    // Returns the service charge.
    public double getServiceCharge() {
        return serviceCharge;
    }

    // Returns the final amount.
    public double getFinalAmount() {
        return finalAmount;
    }

    // Adds a rental item to the transaction.
    public void addItem(RentalItem item, int quantity) {

        // Check that quantity is valid.
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        // Check whether the item already exists.
        for (RentalLine line : rentalLines) {

            if (line.getItem().getItemCode().equals(item.getItemCode())) {

                // If it already exists, increase the quantity.
                line.setQuantity(line.getQuantity() + quantity);
                return;
            }
        }

        // If the item does not exist, create a new rental line.
        RentalLine newLine = new RentalLine(item, quantity);
        rentalLines.add(newLine);
    }

    // Updates the quantity of an existing rental item.
    public boolean updateItemQuantity(String itemCode, int newQuantity) {

        for (RentalLine line : rentalLines) {

            if (line.getItem().getItemCode().equals(itemCode)) {

                if (newQuantity <= 0) {
                    // Remove the item if quantity is 0 or less.
                    rentalLines.remove(line);
                } else {
                    // Otherwise update the quantity.
                    line.setQuantity(newQuantity);
                }

                return true;
            }
        }

        return false;
    }

    // Calculates the subtotal of all rental items.
    
public void calculateSubtotal() {

    // Reset the subtotal before recalculating.
    subtotal = 0.0;

    // Add the separate facility booking charge.
    if (booking != null) {
        subtotal += booking.getFacility().getRentalPrice();
    }

    // Add all rental catalogue items, including
    // facility packages, equipment and accessories.
    for (RentalLine line : rentalLines) {
        subtotal += line.getTotal();
    }
}

    // Sets the promotion discount.
    public void setDiscount(double discount) {

        // Prevent negative discounts.
        if (discount < 0) {
            discount = 0;
        }

        this.discount = discount;
    }

    // Calculates the 10% service charge.
    public void calculateServiceCharge() {

        // Service charge is calculated after the discount.
        double amountAfterDiscount = subtotal - discount;

        // Prevent a negative amount.
        if (amountAfterDiscount < 0) {
            amountAfterDiscount = 0;
        }

        serviceCharge = amountAfterDiscount * 0.10;
    }

    // Calculates the final amount.
    public void calculateFinalAmount() {

        finalAmount = (subtotal - discount) + serviceCharge;

        // Prevent a negative final amount.
        if (finalAmount < 0) {
            finalAmount = 0;
        }
    }

    // Recalculates all transaction amounts.
    public void calculateTotal() {

        calculateSubtotal();
        calculateServiceCharge();
        calculateFinalAmount();
    }

    // Displays the items inside the transaction.
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

    // Displays the transaction summary.
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