package User;

import java.util.*;

public class CustomerSystem {
    ArrayList<Customer> customerList = new ArrayList<Customer>();

    /* Getter */
    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    /* Add a customer to the list */
    void addCustomerList(String name, int id, String password) {
        Customer customer = new Customer(name, id, password);
        customerList.add(customer);
    }

    /* Delete a customer from the list */
    void deleteCustomerList(int id) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == id) {
                customerList.remove(i);
            }
        }
    }

    /* Fix a customer from the list */
    void fixCustomerList(int id, String name, String password) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == id) {
                customerList.get(i).setName(name);
                customerList.get(i).setPassword(password);
            }
        }
    }
}
