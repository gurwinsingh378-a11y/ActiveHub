public class Account {

    private String icno;
    private String cname;
    private double amount;

    // Default constructor
    public Account() {
        icno = null;
        cname = null;
        amount = 0.0;
    }

    // Constructor with two arguments
    public Account(String icNum, String name) {
        icno = icNum;
        cname = name;
    }

    // Deposit money
    public void depositMoney(double savingAmt) {
        amount = amount + savingAmt;
    }

    // Override toString()
    @Override
    public String toString() {
        return "IC No: " + icno
                + "\nName: " + cname
                + "\nAmount: RM" + amount;
    }
}