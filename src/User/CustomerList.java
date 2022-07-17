package User;

import java.util.ArrayList;

public class CustomerList {
    public ArrayList<Customer> customerList = new ArrayList<Customer>();

    void addCustomerList() {
        customerList.add(new Customer("John", 1));
    }

    void deleteCustomerList() {
    }

    void fixCustomerList() {
    }

}
