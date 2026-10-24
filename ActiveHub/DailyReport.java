package ActiveHub;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DailyReport {

    private LocalDate reportDate;
    private int totalBookings;
    private int totalTransactions;
    private double totalRevenue;
    private String mostFrequentItem;

    public DailyReport(LocalDate reportDate) {
        this.reportDate = reportDate;
        this.totalBookings = 0;
        this.totalTransactions = 0;
        this.totalRevenue = 0.0;
        this.mostFrequentItem = "None";
    }

    public LocalDate getReportDate() {
        return reportDate;
    }
    
    public int getTotalBookings() {
        return totalBookings;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public String getMostFrequentItem() {
        return mostFrequentItem;
    }

    public void generateReport(
            ArrayList<Booking> bookings,
            ArrayList<RentalTransaction> transactions) {
        
        totalBookings = 0;
        totalTransactions = 0;
        totalRevenue = 0.0;
        mostFrequentItem = "None";
        
        for (Booking booking : bookings) {

            if (booking.getBookingDate().equals(reportDate)
                    && booking.getStatus().equals("CONFIRMED")) {

                totalBookings++;
            }
        }

        Map<String, Integer> itemQuantities = new HashMap<>();

        for (RentalTransaction transaction : transactions) {

            if (transaction.getTransactionDate()
                    .equals(reportDate)) {

                totalTransactions++;

                totalRevenue += transaction.getFinalAmount();

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

        int highestQuantity = 0;

        for (Map.Entry<String, Integer> entry :
                itemQuantities.entrySet()) {

            if (entry.getValue() > highestQuantity) {

                highestQuantity = entry.getValue();

                mostFrequentItem = entry.getKey();
            }
        }
    }

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
