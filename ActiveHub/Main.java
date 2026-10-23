package ActiveHub;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Main {

    // Stores all bookings created during the program.
static ArrayList<Booking> bookings = new ArrayList<>();

// Stores the available sports facilities.
static ArrayList<Facility> facilities = new ArrayList<>();

// Stores all rental transactions created by the system.
static ArrayList<RentalTransaction> transactions = new ArrayList<>();

// Stores the rental catalogue items.
static ArrayList<RentalItem> rentalCatalogue = new ArrayList<>();

// Manages all promotions available at ActiveHub.
static PromotionManager promotionManager = new PromotionManager();

// Stores all successful payments made by customers.
static ArrayList<Payment> payments = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Load the available facilities when the program starts.
        initializeFacilities();
initializeRentalCatalogue();

FileStorage.loadBookings(bookings, facilities);

FileStorage.loadTransactions(
        transactions,
        bookings,
        rentalCatalogue
);

FileStorage.loadPayments(payments);

        int choice;

        do {
            displayMenu();

            System.out.print("Enter your choice: ");

            // Validate that the user enters a number
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number from 1 to 7.");
                System.out.print("Enter your choice: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline

            switch (choice) {

                case 1:
                    facilityBookingModule(scanner);
                    break;

                case 2:
                    viewRentalCatalogue();
                    break;

                case 3:
                    createRentalTransaction(scanner);
                    break;

                case 4:
                    applyPromotion(scanner);
                    break;

                case 5:
                    makePayment(scanner);
                    break;

                case 6:
                    dailyReport();
                    break;

                case 7:
                    System.out.println("\nThank you for using ActiveHub Sports Centre!");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please select 1 to 7.");
            }

        } while (choice != 7);

        scanner.close();
    }

    // Creates the sports facilities available at ActiveHub.
public static void initializeFacilities() {

    // Clear the list first to avoid duplicate facilities.
    facilities.clear();

    // Add Badminton Court.
    facilities.add(
        new Facility(
            "F001",
            "Badminton Court 1",
            "Court",
            30.00
        )
    );

    // Add Basketball Court.
    facilities.add(
        new Facility(
            "F002",
            "Basketball Court 1",
            "Court",
            40.00
        )
    );

    // Add Tennis Court.
    facilities.add(
        new Facility(
            "F003",
            "Tennis Court 1",
            "Court",
            35.00
        )
    );
}

    // Creates the 8 rental items required by the assignment.
public static void initializeRentalCatalogue() {

    rentalCatalogue.clear();

    // 4 Equipment items
    rentalCatalogue.add(
        new Equipment("E001", "Badminton Racket",
                "Equipment", 10.00, "Yonex")
    );

    rentalCatalogue.add(
        new Equipment("E002", "Football",
                "Equipment", 15.00, "Adidas")
    );

    rentalCatalogue.add(
        new Equipment("E003", "Tennis Racket",
                "Equipment", 12.00, "Wilson")
    );

    rentalCatalogue.add(
        new Equipment("E004", "Basketball",
                "Equipment", 15.00, "Molten")
    );

    // 2 Facility/Court packages
    rentalCatalogue.add(
        new FacilityPackage("F001", "Badminton Court Package",
                "Facility", 30.00, 1)
    );

    rentalCatalogue.add(
        new FacilityPackage("F002", "Basketball Court Package",
                "Facility", 40.00, 1)
    );

    // 2 Accessories/Add-on services
    rentalCatalogue.add(
        new Accessory("A001", "Shuttlecock Set",
                "Accessory", 5.00, "Pack of shuttlecocks")
    );

    rentalCatalogue.add(
        new Accessory("A002", "Sports Towel",
                "Accessory", 3.00, "Sports centre towel")
    );
}

    // ==============================
    // MAIN MENU
    // ==============================

    public static void displayMenu() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("       ACTIVEHUB SPORTS CENTRE");
        System.out.println("========================================");
        System.out.println("1. Facility Booking Module");
        System.out.println("2. View Rental Catalogue");
        System.out.println("3. Create Rental Transaction");
        System.out.println("4. Apply Promotion");
        System.out.println("5. Make Payment");
        System.out.println("6. Daily Report");
        System.out.println("7. Exit");
        System.out.println("========================================");
    }

    // ==============================
    // MODULE 1
    // ==============================

    // Handles all facility booking functions.
public static void facilityBookingModule(Scanner scanner) {

    int choice;

    do {

        System.out.println();
        System.out.println("========================================");
        System.out.println("       FACILITY BOOKING MODULE");
        System.out.println("========================================");
        System.out.println("1. Create Booking");
        System.out.println("2. View Bookings");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Back to Main Menu");
        System.out.println("========================================");

        System.out.print("Enter your choice: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print("Enter your choice: ");
            scanner.next();
        }

        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:
                createBooking(scanner);
                break;

            case 2:
                viewBookings();
                break;

            case 3:
                cancelBooking(scanner);
                break;

            case 4:
                System.out.println("Returning to main menu...");
                break;

            default:
                System.out.println("Invalid choice. Please select 1 to 4.");
        }

    } while (choice != 4);
}

// Creates a new facility booking.
public static void createBooking(Scanner scanner) {

    System.out.println();
    System.out.println("===== CREATE BOOKING =====");

    // Generate a booking ID.
    String bookingId = String.format(
        "B%03d",
        bookings.size() + 1
    );

    // Generate a customer ID.
    String customerId = String.format(
        "C%03d",
        bookings.size() + 1
    );

    // Get customer information.
    System.out.print("Enter Customer Name: ");
    String name = scanner.nextLine();

    System.out.print("Enter Contact Number: ");
    String contactNumber = scanner.nextLine();

    System.out.print("Enter Customer Type: ");
    String customerType = scanner.nextLine();

    // Create the customer object.
    Customer customer = new Customer(
        customerId,
        name,
        contactNumber,
        customerType
    );

    // Display available facilities.
    System.out.println("\nAvailable Facilities:");

    for (Facility facility : facilities) {
        System.out.println(facility.getDetails());
    }

    // Ask the user to select a facility.
    System.out.print("\nEnter Facility ID: ");
    String facilityId = scanner.nextLine();

    // Find the selected facility.
    Facility selectedFacility = findFacility(facilityId);

    // Check whether the facility exists.
    if (selectedFacility == null) {
        System.out.println("Facility not found.");
        return;
    }

    // Ask for booking date.
    System.out.print("Enter Booking Date (YYYY-MM-DD): ");
    String dateInput = scanner.nextLine();

    LocalDate bookingDate;

    try {
        bookingDate = LocalDate.parse(dateInput);
    } catch (DateTimeParseException e) {
        System.out.println(
            "Invalid date format. Please use YYYY-MM-DD."
        );
        return;
    }

    // Ask for booking time.
    System.out.print("Enter Booking Time (HH:MM): ");
    String timeInput = scanner.nextLine();

    LocalTime bookingTime;

    try {
        bookingTime = LocalTime.parse(timeInput);
    } catch (DateTimeParseException e) {
        System.out.println(
            "Invalid time format. Please use HH:MM."
        );
        return;
    }

    // Ask for number of participants.
    System.out.print("Enter Number of Participants: ");

    while (!scanner.hasNextInt()) {
        System.out.println(
            "Invalid input. Please enter a number."
        );
        System.out.print("Enter Number of Participants: ");
        scanner.next();
    }

    int participants = scanner.nextInt();
    scanner.nextLine();

    // Participants must be greater than zero.
    if (participants <= 0) {
        System.out.println(
            "Number of participants must be greater than 0."
        );
        return;
    }

    // Create the booking object.
    Booking newBooking = new Booking(
        bookingId,
        customer,
        selectedFacility,
        bookingDate,
        bookingTime,
        participants
    );

    // Check for duplicate booking.
    for (Booking existingBooking : bookings) {

        if (newBooking.conflictsWith(existingBooking)) {

            System.out.println(
                "\nBooking failed!"
            );

            System.out.println(
                "The selected facility is already booked "
                + "for this date and time."
            );

            return;
        }
    }

    // Add the booking to the list.
bookings.add(newBooking);

// Save the booking to a text file.
FileStorage.saveBooking(newBooking);

    System.out.println(
        "\nBooking created successfully!"
    );

    System.out.println(
        "Booking ID: " + bookingId
    );

    System.out.println(
        "Day: " + newBooking.getDayOfWeek()
    );
}

// Creates a new rental transaction.
public static void createRentalTransaction(Scanner scanner) {

    System.out.println("\n========================================");
    System.out.println("       CREATE RENTAL TRANSACTION");
    System.out.println("========================================");

    // Check whether there are any bookings.
    if (bookings.isEmpty()) {
        System.out.println("No bookings available.");
        System.out.println("Please create a facility booking first.");
        return;
    }

    // Display confirmed bookings.
    System.out.println("\nAvailable Bookings:");

    for (Booking booking : bookings) {

        if (booking.getStatus().equals("CONFIRMED")) {

            System.out.println(
                booking.getBookingId()
                + " | "
                + booking.getCustomer().getName()
                + " | "
                + booking.getFacility().getFacilityName()
                + " | "
                + booking.getBookingDate()
                + " | "
                + booking.getBookingTime()
            );
        }
    }

    // Ask the user to select a booking.
    System.out.print("\nEnter Booking ID: ");
    String bookingId = scanner.nextLine();

    Booking selectedBooking = null;

    // Find the selected booking.
    for (Booking booking : bookings) {

        if (booking.getBookingId().equalsIgnoreCase(bookingId)
                && booking.getStatus().equals("CONFIRMED")) {

            selectedBooking = booking;
            break;
        }
    }

    // Stop if the booking does not exist.
    if (selectedBooking == null) {
        System.out.println("Booking not found.");
        return;
    }

    // Generate a transaction ID.
    String transactionId = String.format(
        "T%03d", transactions.size() + 1
    );

    // Create the transaction.
    RentalTransaction transaction =
        new RentalTransaction(
            transactionId,
            LocalDate.now(),
            selectedBooking.getCustomer(),
            selectedBooking
        );

    // Allow the user to add multiple items.
    String continueAdding = "Y";

    while (continueAdding.equalsIgnoreCase("Y")) {

        System.out.println("\n===== RENTAL CATALOGUE =====");

        for (RentalItem item : rentalCatalogue) {
            System.out.println(item.getDetails());
        }

        System.out.print("\nEnter Item Code: ");
        String itemCode = scanner.nextLine();

        // Find the selected item.
        RentalItem selectedItem = findRentalItem(itemCode);

        if (selectedItem == null) {
            System.out.println("Item not found.");
        } else {

            System.out.print("Enter Quantity: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid quantity.");
                System.out.print("Enter Quantity: ");
                scanner.next();
            }

            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.");
            } else {

                transaction.addItem(selectedItem, quantity);

                System.out.println(
                    "Item added successfully!"
                );
            }
        }

        System.out.print(
            "\nAdd another item? (Y/N): "
        );

        continueAdding = scanner.nextLine();
    }

    // Make sure at least one item was added.
    if (transaction.getRentalLines().isEmpty()) {
        System.out.println("\nNo items were added.");
        return;
    }

    // Calculate the transaction amount.
    transaction.calculateTotal();

    // Add transaction to the system.
    transactions.add(transaction);
    
    // Save the transaction and its rental items.
FileStorage.saveTransaction(transaction);

    // Display the transaction.
    transaction.displayTransactionSummary();

    System.out.println("\nRental transaction created successfully!");
}

// Finds a rental item using its item code.
public static RentalItem findRentalItem(String itemCode) {

    for (RentalItem item : rentalCatalogue) {

        if (item.getItemCode().equalsIgnoreCase(itemCode)) {
            return item;
        }
    }

    return null;
}

// Searches for a facility using its ID.
public static Facility findFacility(String facilityId) {

    for (Facility facility : facilities) {

        if (facility.getFacilityId().equalsIgnoreCase(facilityId)) {

            return facility;
        }
    }

    return null;
}

// Displays all bookings stored in the system.
public static void viewBookings() {

    System.out.println();
    System.out.println("===== ALL BOOKINGS =====");

    // Check whether there are any bookings.
    if (bookings.isEmpty()) {

        System.out.println("No bookings found.");
        return;
    }

    for (Booking booking : bookings) {

        System.out.println(
                booking.getBookingDetails()
        );
    }
}

// Cancels an existing booking.
public static void cancelBooking(Scanner scanner) {

    System.out.println();
    System.out.println("===== CANCEL BOOKING =====");

    if (bookings.isEmpty()) {

        System.out.println("No bookings available.");
        return;
    }

    System.out.print("Enter Booking ID: ");
    String bookingId = scanner.nextLine();

    for (Booking booking : bookings) {

        if (booking.getBookingId().equalsIgnoreCase(bookingId)) {

            // Check whether the booking is already cancelled.
            if (booking.getStatus().equals("CANCELLED")) {

                System.out.println(
                        "This booking has already been cancelled."
                );

                return;
            }

            booking.cancelBooking();

            FileStorage.updateSavedBookings(bookings);

            System.out.println(
                    "Booking cancelled successfully."
            );

            return;
        }
    }

    System.out.println("Booking ID not found.");
}

    // ==============================
    // MODULE 2
    // ==============================

    public static void viewRentalCatalogue() {

    ArrayList<RentalItem> catalogue = new ArrayList<>();

    // 4 Equipment items
    catalogue.add(new Equipment(
            "E001", "Badminton Racket",
            "Equipment", 10.00, "Yonex"));

    catalogue.add(new Equipment(
            "E002", "Football",
            "Equipment", 15.00, "Adidas"));

    catalogue.add(new Equipment(
            "E003", "Tennis Racket",
            "Equipment", 12.00, "Wilson"));

    catalogue.add(new Equipment(
            "E004", "Basketball",
            "Equipment", 15.00, "Molten"));

    // 2 Facility/Court packages
    catalogue.add(new FacilityPackage(
            "F001", "Badminton Court Package",
            "Facility", 30.00, 1));

    catalogue.add(new FacilityPackage(
            "F002", "Basketball Court Package",
            "Facility", 40.00, 1));

    // 2 Accessories/Add-on services
    catalogue.add(new Accessory(
            "A001", "Shuttlecock Set",
            "Accessory", 5.00, "Standard shuttlecock set"));

    catalogue.add(new Accessory(
            "A002", "Sports Towel",
            "Accessory", 3.00, "Sports centre towel"));

    System.out.println();
    System.out.println("==============================================================");
    System.out.println("                    RENTAL CATALOGUE");
    System.out.println("==============================================================");
    System.out.printf("%-8s %-28s %-15s %10s%n",
            "Code", "Item Name", "Category", "Price");
    System.out.println("--------------------------------------------------------------");

    for (RentalItem item : catalogue) {
        System.out.printf("%-8s %-28s %-15s RM%8.2f%n",
                item.getItemCode(),
                item.getItemName(),
                item.getCategory(),
                item.getRentalPrice());
    }

    System.out.println("==============================================================");
    System.out.println();
}

    // ==============================
    // MODULE 4
    // ==============================

    // Handles the promotion module.
    static void applyPromotion(Scanner scanner) {

    System.out.println("\n===== APPLY PROMOTION =====");

    // Check whether there are any transactions.
    if (transactions.isEmpty()) {
        System.out.println("No rental transactions available.");
        pause(scanner);
        return;
    }

    // Display all existing transactions.
    System.out.println("\nAvailable Transactions:");

    for (RentalTransaction transaction : transactions) {
        System.out.println(
            transaction.getTransactionId()
            + " | Customer: "
            + transaction.getCustomer().getName()
        );
    }

    // Ask the user which transaction should receive
    // the promotion.
    System.out.print("\nEnter Transaction ID: ");
    String transactionId = scanner.nextLine().trim();

    RentalTransaction selectedTransaction = null;

    // Search for the transaction.
    for (RentalTransaction transaction : transactions) {

        if (transaction.getTransactionId()
                .equalsIgnoreCase(transactionId)) {

            selectedTransaction = transaction;
            break;
        }
    }

    // Check whether the transaction exists.
    if (selectedTransaction == null) {
        System.out.println("Transaction not found.");
        pause(scanner);
        return;
    }

    // Display all promotions that are eligible.
    promotionManager.displayEligiblePromotions(
        selectedTransaction
    );

    // Automatically select the promotion
    // with the greatest saving.
    promotionManager.applyBestPromotion(
        selectedTransaction
    );

    // Display the updated transaction.
    selectedTransaction.displayTransactionSummary();

    pause(scanner);
}

    // ==============================
    // MODULE 5
    // ==============================

    // Handles the payment module.
static void makePayment(Scanner scanner) {

    System.out.println("\n===== MAKE PAYMENT =====");

    // Check whether there are any transactions.
    if (transactions.isEmpty()) {
        System.out.println("No rental transactions available.");
        pause(scanner);
        return;
    }

    // Display available transactions.
    System.out.println("\nAvailable Transactions:");

    for (RentalTransaction transaction : transactions) {

        System.out.println(
            transaction.getTransactionId()
            + " | Customer: "
            + transaction.getCustomer().getName()
            + " | Amount: RM"
            + String.format("%.2f",
                transaction.getFinalAmount())
        );
    }

    // Ask the user to select a transaction.
    System.out.print("\nEnter Transaction ID: ");
    String transactionId = scanner.nextLine().trim();

    RentalTransaction selectedTransaction = null;

    // Search for the selected transaction.
    for (RentalTransaction transaction : transactions) {

        if (transaction.getTransactionId()
                .equalsIgnoreCase(transactionId)) {

            selectedTransaction = transaction;
            break;
        }
    }

    // Check whether the transaction exists.
    if (selectedTransaction == null) {
        System.out.println("Transaction not found.");
        pause(scanner);
        return;
    }

    // Check whether this transaction has already been paid.
int transactionIndex = transactions.indexOf(selectedTransaction);

String existingPaymentId =
        "P" + String.format("%03d", transactionIndex + 1);

for (Payment existingPayment : payments) {

    if (existingPayment.getPaymentId()
            .equals(existingPaymentId)) {

        System.out.println(
            "This transaction has already been paid."
        );

        pause(scanner);
        return;
    }
}

    // Automatically apply the best eligible promotion.
promotionManager.applyBestPromotion(selectedTransaction);

    double amount =
            selectedTransaction.getFinalAmount();

    System.out.println("\nAmount to Pay: RM"
            + String.format("%.2f", amount));

    // Display payment methods.
    System.out.println("\n===== PAYMENT METHOD =====");
    System.out.println("1. Cash");
    System.out.println("2. Credit/Debit Card");
    System.out.println("3. E-Wallet");

    System.out.print("Enter payment method: ");

    String methodChoice = scanner.nextLine().trim();

    // Generate a simple payment ID.
    String paymentId =
            "P" + String.format("%03d", transactions.indexOf(
                    selectedTransaction) + 1);

    Payment payment = null;

    // Store the successful payment.
payments.add(payment);

// Save the successful payment to a text file.
FileStorage.savePayment(payment);

    // -------------------------
    // CASH PAYMENT
    // -------------------------
    if (methodChoice.equals("1")) {

        System.out.print("Enter cash received: RM");

        double receivedAmount;

        try {
            receivedAmount =
                    Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {

            System.out.println("Invalid amount.");
            pause(scanner);
            return;
        }

        // Check whether enough cash was received.
        if (receivedAmount < amount) {

            System.out.println(
                "Insufficient cash. Payment cancelled."
            );

            pause(scanner);
            return;
        }

        payment = new CashPayment(
                paymentId,
                amount,
                receivedAmount
        );
    }

    // -------------------------
    // CARD PAYMENT
    // -------------------------
    else if (methodChoice.equals("2")) {

        System.out.print(
            "Enter card type (Credit/Debit): "
        );

        String cardType = scanner.nextLine();

        System.out.print(
            "Enter last 4 digits of card: "
        );

        String last4Digits = scanner.nextLine();

        payment = new CardPayment(
                paymentId,
                amount,
                cardType,
                last4Digits
        );
    }

    // -------------------------
    // E-WALLET PAYMENT
    // -------------------------
    else if (methodChoice.equals("3")) {

        System.out.print(
            "Enter e-wallet type: "
        );

        String walletType = scanner.nextLine();

        System.out.print(
            "Enter reference number: "
        );

        String referenceNumber = scanner.nextLine();

        payment = new EWalletPayment(
                paymentId,
                amount,
                walletType,
                referenceNumber
        );
    }

    // Invalid payment method.
    else {

        System.out.println("Invalid payment method.");
        pause(scanner);
        return;
    }

    // Store the successful payment.
payments.add(payment);

// Display the completed payment.
System.out.println("\nPayment successful!");

payment.displayPayment();

pause(scanner);
}

    // ==============================
    // MODULE 6
    // ==============================

    // Handles the daily report module.
public static void dailyReport() {

    // Create a report for today's date.
    DailyReport report =
            new DailyReport(LocalDate.now());

    // Generate the report using the stored
    // bookings and rental transactions.
    report.generateReport(
            bookings,
            transactions
    );

    // Display the main report information.
    report.displayReport();

    // Display payment method summary.
    System.out.println("\n===== PAYMENT METHOD SUMMARY =====");

    // Count each type of successful payment.
    int cashCount = 0;
    int cardCount = 0;
    int eWalletCount = 0;

    for (Payment payment : payments) {

        // Check the actual payment object type.
        if (payment instanceof CashPayment) {

            cashCount++;

        } else if (payment instanceof CardPayment) {

            cardCount++;

        } else if (payment instanceof EWalletPayment) {

            eWalletCount++;
        }
    }

    System.out.println("Cash Payments       : " + cashCount);
    System.out.println("Card Payments       : " + cardCount);
    System.out.println("E-Wallet Payments   : " + eWalletCount);

    System.out.println("========================================");
}

    // ==============================
    // PAUSE
    // ==============================

    public static void pause(Scanner scanner) {

        System.out.println();
        System.out.print("Press Enter to return to the main menu...");
        scanner.nextLine();
    }
}