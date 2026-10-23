package ActiveHub;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// This class represents the daily report
// for ActiveHub Sports Centre.
public class DailyReport {

    // Date of the report.
    private LocalDate reportDate;

    // Total number of facility bookings.
    private int totalBookings;

    // Total number of completed rental transactions.
    private int totalTransactions;

    // Total revenue collected.
    private double totalRevenue;

    // Most frequently rented item.
    private String mostFrequentItem;

    // Constructor
    public DailyReport(LocalDate reportDate) {
        this.reportDate = reportDate;
        this.totalBookings = 0;
        this.totalTransactions = 0;
        this.totalRevenue = 0.0;
        this.mostFrequentItem = "None";
    }

    // Returns the report date.
    public LocalDate getReportDate() {
        return reportDate;
    }

    // Returns the total number of bookings.
    public int getTotalBookings() {
        return totalBookings;
    }

    // Returns the total number of transactions.
    public int getTotalTransactions() {
        return totalTransactions;
    }

    // Returns the total revenue.
    public double getTotalRevenue() {
        return totalRevenue;
    }

    // Returns the most frequently rented item.
    public String getMostFrequentItem() {
        return mostFrequentItem;
    }

    // Generates the report using the stored bookings
    // and rental transactions.
    public void generateReport(
            ArrayList<Booking> bookings,
            ArrayList<RentalTransaction> transactions) {

        // Reset report values.
        totalBookings = 0;
        totalTransactions = 0;
        totalRevenue = 0.0;
        mostFrequentItem = "None";

        // Count today's confirmed bookings.
        for (Booking booking : bookings) {

            if (booking.getBookingDate().equals(reportDate)
                    && booking.getStatus().equals("CONFIRMED")) {

                totalBookings++;
            }
        }

        // Count today's rental transactions
        // and calculate revenue.
        Map<String, Integer> itemQuantities = new HashMap<>();

        for (RentalTransaction transaction : transactions) {

            if (transaction.getTransactionDate()
                    .equals(reportDate)) {

                totalTransactions++;

                totalRevenue += transaction.getFinalAmount();

                // Count each rented item.
                for (RentalLine line :
                        transaction.getRentalLines()) {

                    String itemName =
                            line.getItem().getItemName();

                    int quantity =
                            line.getQuantity();

                    itemQuantities.put(
                            itemName,
                            itemQuantities.getOrDefault(
                                    itemName, 0
                            ) + quantity
                    );
                }
            }
        }

        // Find the most frequently rented item.
        int highestQuantity = 0;

        for (Map.Entry<String, Integer> entry :
                itemQuantities.entrySet()) {

            if (entry.getValue() > highestQuantity) {

                highestQuantity = entry.getValue();

                mostFrequentItem = entry.getKey();
            }
        }
    }

    // Displays the completed report.
    public void displayReport() {

        System.out.println("\n========================================");
        System.out.println("           DAILY REPORT");
        System.out.println("========================================");

        System.out.println(
                "Report Date              : " + reportDate
        );

        System.out.println(
                "Total Facility Bookings  : " + totalBookings
        );

        System.out.println(
                "Completed Transactions   : " + totalTransactions
        );

        System.out.println(
                "Total Daily Revenue      : RM"
                + String.format("%.2f", totalRevenue)
        );

        System.out.println(
                "Most Frequently Rented   : "
                + mostFrequentItem
        );

        System.out.println("========================================");
    }
}