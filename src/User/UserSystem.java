package User;

import java.util.*;

public class UserSystem {
    ArrayList<User> userList = new ArrayList<User>();

    // Add a new user to the system
    public int addCustomer(String name, String id, String password) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                System.out.println("Same ID already exists");
                return -1;
            }
        }
        Customer customer = new Customer(name, id, password);
        userList.add(customer);
        return 0;
    }

    public void addStaff(String name, String id, String password) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                System.out.println("Same ID already exists");
                return;
            }
        }
        Staff staff = new Staff(name, id, password);
        userList.add(staff);
    }

    // Remove a user from the system
    public int removeUser(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                userList.remove(user);
                return 0;
            }
        }
        System.out.println("No such user");
        return -1;
    }

    // Find a user by ID
    public User findUser(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        System.out.println("No such user");
        return null;
    }

}