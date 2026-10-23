package ActiveHub;
// This class represents a customer who uses
// the ActiveHub Sports Centre.
public class Customer {

    // Unique ID for the customer
    private String customerId;

    // Customer's name
    private String name;

    // Customer's contact number
    private String contactNumber;

    // Customer type, for example:
    // Student, Staff, or Community Member
    private String customerType;


    // Constructor
    // Used to create a Customer object with its details.
    public Customer(String customerId, String name,
                    String contactNumber, String customerType) {

        this.customerId = customerId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.customerType = customerType;
    }


    // Returns the customer's ID
    public String getCustomerId() {
        return customerId;
    }


    // Returns the customer's name
    public String getName() {
        return name;
    }


    // Returns the customer's contact number
    public String getContactNumber() {
        return contactNumber;
    }


    // Returns the type of customer
    public String getCustomerType() {
        return customerType;
    }


    // Allows the customer's contact number
    // to be updated.
    public void updateContactNumber(String contactNumber) {

        this.contactNumber = contactNumber;
    }


    // Returns the customer's information
    // as a readable String.
    public String getDetails() {

        return customerId + " | "
                + name + " | "
                + contactNumber + " | "
                + customerType;
    }
}