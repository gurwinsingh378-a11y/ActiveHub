package ActiveHub;

public class Customer {
    private String customerId;
    private String name;
    private String contactNumber;
    private String customerType;
    
    public Customer(String customerId, String name,
                    String contactNumber, String customerType) {

        this.customerId = customerId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.customerType = customerType;
    }
    
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void updateContactNumber(String contactNumber) {

        this.contactNumber = contactNumber;
    }

    public String getDetails() {

        return customerId + " | "
                + name + " | "
                + contactNumber + " | "
                + customerType;
    }
}
