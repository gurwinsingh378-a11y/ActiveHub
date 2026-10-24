package ActiveHub;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Main {

static ArrayList<Booking> bookings = new ArrayList<>();
static ArrayList<Facility> facilities = new ArrayList<>();
static ArrayList<RentalTransaction> transactions = new ArrayList<>();
static ArrayList<RentalItem> rentalCatalogue = new ArrayList<>();
static PromotionManager promotionManager = new PromotionManager();
static ArrayList<Payment> payments = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number from 1 to 7.");
                System.out.print("Enter your choice: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

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

public static void initializeFacilities() {

    facilities.clear();

    facilities.add(
        new Facility(
            "F001",
            "Badminton Court 1",
            "Court",
            30.00
        )
    );

    facilities.add(
        new Facility(
            "F002",
            "Basketball Court 1",
            "Court",
            40.00
        )
    );

    facilities.add(
        new Facility(
            "F003",
            "Tennis Court 1",
            "Court",
            35.00
        )
    );
}

public static void initializeRentalCatalogue() {

    rentalCatalogue.clear();

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

    rentalCatalogue.add(
        new FacilityPackage("F001", "Badminton Court Package",
                "Facility", 30.00, 1)
    );

    rentalCatalogue.add(
        new FacilityPackage("F002", "Basketball Court Package",
                "Facility", 40.00, 1)
    );

    rentalCatalogue.add(
        new Accessory("A001", "Shuttlecock Set",
                "Accessory", 5.00, "Pack of shuttlecocks")
    );

    rentalCatalogue.add(
        new Accessory("A002", "Sports Towel",
                "Accessory", 3.00, "Sports centre towel")
    );
}

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
    
public static void createBooking(Scanner scanner) {

    System.out.println();
    System.out.println("===== CREATE BOOKING =====");

    String bookingId = String.format(
        "B%03d",
        bookings.size() + 1
    );

    String customerId = String.format(
        "C%03d",
        bookings.size() + 1
    );

    System.out.print("Enter Customer Name: ");
    String name = scanner.nextLine();

    System.out.print("Enter Contact Number: ");
    String contactNumber = scanner.nextLine();

    System.out.print("Enter Customer Type: ");
    String customerType = scanner.nextLine();

    Customer customer = new Customer(
        customerId,
        name,
        contactNumber,
        customerType
    );

    System.out.println("\nAvailable Facilities:");

    for (Facility facility : facilities) {
        System.out.println(facility.getDetails());
    }

    System.out.print("\nEnter Facility ID: ");
    String facilityId = scanner.nextLine();

    Facility selectedFacility = findFacility(facilityId);

    if (selectedFacility == null) {
        System.out.println("Facility not found.");
        return;
    }

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

    if (participants <= 0) {
        System.out.println(
            "Number of participants must be greater than 0."
        );
        return;
    }
    
    Booking newBooking = new Booking(
        bookingId,
        customer,
        selectedFacility,
        bookingDate,
        bookingTime,
        participants
    );

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

bookings.add(newBooking);

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

public static void createRentalTransaction(Scanner scanner) {

    System.out.println("\n========================================");
    System.out.println("       CREATE RENTAL TRANSACTION");
    System.out.println("========================================");

    if (bookings.isEmpty()) {
        System.out.println("No bookings available.");
        System.out.println("Please create a facility booking first.");
        return;
    }

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

    System.out.print("\nEnter Booking ID: ");
    String bookingId = scanner.nextLine();

    Booking selectedBooking = null;

    for (Booking booking : bookings) {

        if (booking.getBookingId().equalsIgnoreCase(bookingId)
                && booking.getStatus().equals("CONFIRMED")) {

            selectedBooking = booking;
            break;
        }
    }

    if (selectedBooking == null) {
        System.out.println("Booking not found.");
        return;
    }

    String transactionId = String.format(
        "T%03d", transactions.size() + 1
    );

    RentalTransaction transaction =
        new RentalTransaction(
            transactionId,
            LocalDate.now(),
            selectedBooking.getCustomer(),
            selectedBooking
        );

    String continueAdding = "Y";

    while (continueAdding.equalsIgnoreCase("Y")) {

        System.out.println("\n===== RENTAL CATALOGUE =====");

        for (RentalItem item : rentalCatalogue) {
            System.out.println(item.getDetails());
        }

        System.out.print("\nEnter Item Code: ");
        String itemCode = scanner.nextLine();

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

    if (transaction.getRentalLines().isEmpty()) {
        System.out.println("\nNo items were added.");
        return;
    }

    transaction.calculateTotal();

    transactions.add(transaction);
    
FileStorage.saveTransaction(transaction);

    transaction.displayTransactionSummary();

    System.out.println("\nRental transaction created successfully!");
}

public static RentalItem findRentalItem(String itemCode) {

    for (RentalItem item : rentalCatalogue) {

        if (item.getItemCode().equalsIgnoreCase(itemCode)) {
            return item;
        }
    }

    return null;
}

public static Facility findFacility(String facilityId) {

    for (Facility facility : facilities) {

        if (facility.getFacilityId().equalsIgnoreCase(facilityId)) {

            return facility;
        }
    }

    return null;
}

public static void viewBookings() {

    System.out.println();
    System.out.println("===== ALL BOOKINGS =====");

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

    public static void viewRentalCatalogue() {

    ArrayList<RentalItem> catalogue = new ArrayList<>();

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

    catalogue.add(new FacilityPackage(
            "F001", "Badminton Court Package",
            "Facility", 30.00, 1));

    catalogue.add(new FacilityPackage(
            "F002", "Basketball Court Package",
            "Facility", 40.00, 1));

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

    static void applyPromotion(Scanner scanner) {

    System.out.println("\n===== APPLY PROMOTION =====");

    if (transactions.isEmpty()) {
        System.out.println("No rental transactions available.");
        pause(scanner);
        return;
    }
        
    System.out.println("\nAvailable Transactions:");

    for (RentalTransaction transaction : transactions) {
        System.out.println(
            transaction.getTransactionId()
            + " | Customer: "
            + transaction.getCustomer().getName()
        );
    }

    System.out.print("\nEnter Transaction ID: ");
    String transactionId = scanner.nextLine().trim();

    RentalTransaction selectedTransaction = null;

    for (RentalTransaction transaction : transactions) {

        if (transaction.getTransactionId()
                .equalsIgnoreCase(transactionId)) {

            selectedTransaction = transaction;
            break;
        }
    }

    if (selectedTransaction == null) {
        System.out.println("Transaction not found.");
        pause(scanner);
        return;
    }

    promotionManager.displayEligiblePromotions(
        selectedTransaction
    ); 
        
    promotionManager.applyBestPromotion(
        selectedTransaction
    );

    selectedTransaction.displayTransactionSummary();

    pause(scanner);
}

static void makePayment(Scanner scanner) {

    System.out.println("\n===== MAKE PAYMENT =====");

    if (transactions.isEmpty()) {
        System.out.println("No rental transactions available.");
        pause(scanner);
        return;
    }

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

    System.out.print("\nEnter Transaction ID: ");
    String transactionId = scanner.nextLine().trim();

    RentalTransaction selectedTransaction = null;
    
    for (RentalTransaction transaction : transactions) {

        if (transaction.getTransactionId()
                .equalsIgnoreCase(transactionId)) {

            selectedTransaction = transaction;
            break;
        }
    }

    if (selectedTransaction == null) {
        System.out.println("Transaction not found.");
        pause(scanner);
        return;
    }

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

promotionManager.applyBestPromotion(selectedTransaction);

    double amount =
            selectedTransaction.getFinalAmount();

    System.out.println("\nAmount to Pay: RM"
            + String.format("%.2f", amount));

    System.out.println("\n===== PAYMENT METHOD =====");
    System.out.println("1. Cash");
    System.out.println("2. Credit/Debit Card");
    System.out.println("3. E-Wallet");

    System.out.print("Enter payment method: ");

    String methodChoice = scanner.nextLine().trim();

    String paymentId =
            "P" + String.format("%03d", transactions.indexOf(
                    selectedTransaction) + 1);

    Payment payment = null;

payments.add(payment);

FileStorage.savePayment(payment);

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

    else {

        System.out.println("Invalid payment method.");
        pause(scanner);
        return;
    }

payments.add(payment);

System.out.println("\nPayment successful!");

payment.displayPayment();

pause(scanner);
}

public static void dailyReport() {

    DailyReport report =
            new DailyReport(LocalDate.now());

    report.generateReport(
            bookings,
            transactions
    );

    report.displayReport();

    System.out.println("\n===== PAYMENT METHOD SUMMARY =====");

    int cashCount = 0;
    int cardCount = 0;
    int eWalletCount = 0;

    for (Payment payment : payments) {

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

    public static void pause(Scanner scanner) {

        System.out.println();
        System.out.print("Press Enter to return to the main menu...");
        scanner.nextLine();
    }
}
