public class Staff {

    // Encapsulation
    private String name;
    private String staffID;

    // Constructor
    public Staff(String name, String staffID) {
        this.name = name;
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public String getStaffID() {
        return staffID;
    }

    // Display information
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + staffID);
    }
}


// Manager inherits Staff
class Manager extends Staff {

    private double allowance = 800;

    // Constructor
    public Manager(String name, String staffID) {
        super(name, staffID);
    }

    @Override
    public void displayInfo() {
        System.out.println("Manager Information");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getStaffID());
        System.out.println("Salary: RM" + (3000 + allowance));
    }
}


// Clerk inherits Staff
class Clerk extends Staff {

    private int overtimeHours;
    private double overtimeRate = 20;

    // Constructor
    public Clerk(String name, String staffID, int overtimeHours) {
        super(name, staffID);
        this.overtimeHours = overtimeHours;
    }

    @Override
    public void displayInfo() {
        double salary = 2000 + (overtimeHours * overtimeRate);

        System.out.println("Clerk Information");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getStaffID());
        System.out.println("Salary: RM" + salary);
    }
}


// Main class
class CompanyStaff {
    public static void main(String[] args) {

        Manager manager = new Manager("Ali", "M101");

        Clerk clerk = new Clerk("Siti", "C201", 20);

        manager.displayInfo();

        System.out.println();

        clerk.displayInfo();
    }
}