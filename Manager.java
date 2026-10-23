public class Manager extends Staff{
    private double allowance;
    private static final double BASIC_SALARY = 3000;

    public Manager(String name, String staffId, double allowance) {
        super(name, staffId);
        setAllowance(allowance);
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    @Override
    public double calculateSalary() {
        return BASIC_SALARY + allowance;
    }

    @Override
    public void displayInfo() {
        System.out.println("Manager Information");
        super.displayInfo();
        System.out.printf("Salary: RM%.2f%n", calculateSalary());
    }
}
