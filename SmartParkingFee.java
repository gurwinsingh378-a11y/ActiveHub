import java.util.Scanner;

public class SmartParkingFee {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = input.nextLine();

        System.out.print("Enter Hours: ");
        int hours = input.nextInt();

        System.out.print("VIP Customer (Y/N): ");
        char vip = input.next().charAt(0);

        double originalFee;
        double discount;
        double finalFee;

        // Calculate parking fee
        if (hours <= 2) {
            originalFee = 3;
        } else {
            originalFee = 3 + (hours - 2) * 2;
        }

        // Maximum charge
        if (originalFee > 15) {
            originalFee = 15;
        }

        // VIP discount
        if (vip == 'Y' || vip == 'y') {
            discount = originalFee * 0.20;
        } else {
            discount = 0;
        }

        finalFee = originalFee - discount;

        // Display receipt
        System.out.println("\nParking Receipt");
        System.out.println("Customer: " + name);
        System.out.println("Hours: " + hours);
        System.out.printf("Original Fee: RM%.2f%n", originalFee);
        System.out.printf("Discount: RM%.2f%n", discount);
        System.out.printf("Final Fee: RM%.2f%n", finalFee);

        input.close();
    }
}
