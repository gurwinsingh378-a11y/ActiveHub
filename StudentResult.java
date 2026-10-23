import java.util.Scanner;

public class StudentResult {

    // Method to determine grade
    static String getGrade(int mark) {
        if (mark >= 80) {
            return "A";
        } else if (mark >= 70) {
            return "B";
        } else if (mark >= 60) {
            return "C";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int[] marks = new int[5];
        int total = 0;
        int highest = 0;
        int passed = 0;

        // Input marks
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter mark for student " + (i + 1) + ": ");
            marks[i] = input.nextInt();
        }

        // Calculate total, highest and passed students
        for (int i = 0; i < 5; i++) {

            total = total + marks[i];

            if (marks[i] > highest) {
                highest = marks[i];
            }

            if (marks[i] >= 50) {
                passed++;
            }
        }

        double average = (double) total / 5;

        // Display marks
        System.out.print("\nMarks: ");

        for (int i = 0; i < 5; i++) {
            System.out.print(marks[i] + " ");
        }

        System.out.printf("\nAverage: %.1f", average);
        System.out.println("\nHighest: " + highest);
        System.out.println("Passed Students: " + passed);

        input.close();
    }
}