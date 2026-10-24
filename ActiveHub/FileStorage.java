package ActiveHub;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileStorage {

    private static final String DATA_FOLDER = "data";

    /**
     * Creates the data folder if it does not 
     **/
    public static void createDataFolder() {
        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Appends one record to a text file.
     */
    public static void appendRecord(String fileName, String record) {
        createDataFolder();

        File file = new File(DATA_FOLDER, fileName);

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(record + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Reads and displays all records in a text file.
     */
    public static void displayRecords(String fileName) {
        File file = new File(DATA_FOLDER, fileName);

        if (!file.exists()) {
            System.out.println("No saved records found.");
            return;
        }

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

public static void saveBooking(Booking booking) {
    String record =
            booking.getBookingId() + "|"
            + booking.getCustomer().getCustomerId() + "|"
            + booking.getCustomer().getName() + "|"
            + booking.getCustomer().getContactNumber() + "|"
            + booking.getCustomer().getCustomerType() + "|"
            + booking.getFacility().getFacilityId() + "|"
            + booking.getBookingDate() + "|"
            + booking.getBookingTime() + "|"
            + booking.getParticipants() + "|"
            + booking.getStatus();

    appendRecord("bookings.txt", record);
}

public static void updateSavedBookings(ArrayList<Booking> bookings) {
    createDataFolder();

    File file = new File(DATA_FOLDER, "bookings.txt");

    try (FileWriter writer = new FileWriter(file, false)) {
        for (Booking booking : bookings) {
            String record =
                    booking.getBookingId() + "|"
                    + booking.getCustomer().getCustomerId() + "|"
                    + booking.getCustomer().getName() + "|"
                    + booking.getCustomer().getContactNumber() + "|"
                    + booking.getCustomer().getCustomerType() + "|"
                    + booking.getFacility().getFacilityId() + "|"
                    + booking.getBookingDate() + "|"
                    + booking.getBookingTime() + "|"
                    + booking.getParticipants() + "|"
                    + booking.getStatus();

            writer.write(record + System.lineSeparator());
        }

    } catch (IOException e) {
        System.out.println(
                "Error updating bookings: " + e.getMessage()
        );
    }
}

public static void loadBookings(
        ArrayList<Booking> bookings,
        ArrayList<Facility> facilities) {

    File file = new File(DATA_FOLDER, "bookings.txt");

    if (!file.exists()) {
        return;
    }

    try (Scanner reader = new Scanner(file)) {

        while (reader.hasNextLine()) {

            String line = reader.nextLine();
            String[] data = line.split("\\|", -1);

            if (data.length != 10) {
                System.out.println("Skipping invalid booking record.");
                continue;
            }

            String bookingId = data[0];
            String customerId = data[1];
            String name = data[2];
            String contactNumber = data[3];
            String customerType = data[4];
            String facilityId = data[5];

            LocalDate bookingDate = LocalDate.parse(data[6]);
            LocalTime bookingTime = LocalTime.parse(data[7]);
            int participants = Integer.parseInt(data[8]);
            String status = data[9];

            Facility selectedFacility = null;

            for (Facility facility : facilities) {
                if (facility.getFacilityId().equals(facilityId)) {
                    selectedFacility = facility;
                    break;
                }
            }

            if (selectedFacility == null) {
                System.out.println(
                        "Skipping booking: facility not found."
                );
                continue;
            }

            Customer customer = new Customer(
                    customerId,
                    name,
                    contactNumber,
                    customerType
            );

            Booking booking = new Booking(
                    bookingId,
                    customer,
                    selectedFacility,
                    bookingDate,
                    bookingTime,
                    participants
            );

            if (status.equals("CANCELLED")) {
                booking.cancelBooking();
            }

            bookings.add(booking);
        }

    } catch (IOException | IllegalArgumentException e) {
        System.out.println(
                "Error loading bookings: " + e.getMessage()
        );
    }
}

public static void saveTransaction(RentalTransaction transaction) {

    String transactionRecord =
            transaction.getTransactionId() + "|"
            + transaction.getTransactionDate() + "|"
            + transaction.getBooking().getBookingId() + "|"
            + transaction.getDiscount();

    appendRecord("transactions.txt", transactionRecord);

    for (RentalLine line : transaction.getRentalLines()) {

        String itemRecord =
                transaction.getTransactionId() + "|"
                + line.getItem().getItemCode() + "|"
                + line.getQuantity();

        appendRecord("rental_lines.txt", itemRecord);
    }
}

public static void loadTransactions(
        ArrayList<RentalTransaction> transactions,
        ArrayList<Booking> bookings,
        ArrayList<RentalItem> catalogue) {

    File transactionFile = new File(DATA_FOLDER, "transactions.txt");
    File linesFile = new File(DATA_FOLDER, "rental_lines.txt");

    if (!transactionFile.exists()) {
        return;
    }

    try (Scanner reader = new Scanner(transactionFile)) {

        while (reader.hasNextLine()) {

            String[] data = reader.nextLine().split("\\|", -1);

            if (data.length != 4) {
                System.out.println("Skipping invalid transaction record.");
                continue;
            }

            String transactionId = data[0];
            LocalDate transactionDate = LocalDate.parse(data[1]);
            String bookingId = data[2];
            double discount = Double.parseDouble(data[3]);

            Booking selectedBooking = null;

            for (Booking booking : bookings) {
                if (booking.getBookingId().equals(bookingId)) {
                    selectedBooking = booking;
                    break;
                }
            }

            if (selectedBooking == null) {
                System.out.println(
                        "Skipping transaction: booking not found."
                );
                continue;
            }

            RentalTransaction transaction = new RentalTransaction(
                    transactionId,
                    transactionDate,
                    selectedBooking.getCustomer(),
                    selectedBooking
            );

            if (linesFile.exists()) {
                try (Scanner lineReader = new Scanner(linesFile)) {

                    while (lineReader.hasNextLine()) {

                        String[] itemData =
                                lineReader.nextLine().split("\\|", -1);

                        if (itemData.length != 3
                                || !itemData[0].equals(transactionId)) {
                            continue;
                        }

                        String itemCode = itemData[1];
                        int quantity = Integer.parseInt(itemData[2]);

                        for (RentalItem item : catalogue) {
                            if (item.getItemCode().equals(itemCode)) {
                                transaction.addItem(item, quantity);
                                break;
                            }
                        }
                    }
                }
            }

            transaction.calculateTotal();
            transaction.setDiscount(discount);
            transaction.calculateServiceCharge();
            transaction.calculateFinalAmount();

            transactions.add(transaction);
        }

    } catch (IOException | IllegalArgumentException e) {
        System.out.println(
                "Error loading transactions: " + e.getMessage()
        );
    }
}

public static void savePayment(Payment payment) {

    String record;

    if (payment instanceof CashPayment) {
        CashPayment cash = (CashPayment) payment;

        record = payment.getPaymentId() + "|"
                + payment.getAmount() + "|"
                + "CASH" + "|"
                + cash.getReceivedAmount();

    } else if (payment instanceof CardPayment) {
        CardPayment card = (CardPayment) payment;

        record = payment.getPaymentId() + "|"
                + payment.getAmount() + "|"
                + "CARD" + "|"
                + card.getCardType() + "|"
                + card.getLast4Digits();

    } else if (payment instanceof EWalletPayment) {
        EWalletPayment wallet = (EWalletPayment) payment;

        record = payment.getPaymentId() + "|"
                + payment.getAmount() + "|"
                + "EWALLET" + "|"
                + wallet.getWalletType() + "|"
                + wallet.getReferenceNumber();

    } else {
        return;
    }

    appendRecord("payments.txt", record);
}

public static void loadPayments(ArrayList<Payment> payments) {
    File file = new File("data/payments.txt");

    if (!file.exists()) {
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String record;

        while ((record = reader.readLine()) != null) {
            String[] fields = record.split("\\|", -1);

            try {
                if (fields.length < 4) {
                    continue;
                }

                String paymentId = fields[0];
                double amount = Double.parseDouble(fields[1]);
                String paymentMethod = fields[2];

                Payment payment = null;

                switch (paymentMethod) {
                    case "CASH":
                        payment = new CashPayment(
                            paymentId,
                            amount,
                            Double.parseDouble(fields[3])
                        );
                        break;

                    case "CARD":
                        if (fields.length >= 5) {
                            payment = new CardPayment(
                                paymentId,
                                amount,
                                fields[3],
                                fields[4]
                            );
                        }
                        break;

                    case "EWALLET":
                        if (fields.length >= 5) {
                            payment = new EWalletPayment(
                                paymentId,
                                amount,
                                fields[3],
                                fields[4]
                            );
                        }
                        break;
                }

                if (payment != null) {
                    payments.add(payment);
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Skipping invalid payment record: " + record
                );
            }
        }

    } catch (IOException e) {
        System.out.println("Error loading payments: " + e.getMessage());
    }
}

}
