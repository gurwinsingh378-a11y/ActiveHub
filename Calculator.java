public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {

        if (isZero(b)) {
            System.out.println("Cannot divide by zero.");
            return 0;
        }

        return a / b;
    }

    private boolean isZero(double b) {
        return b == 0;
    }
}