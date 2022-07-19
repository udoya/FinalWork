package GUI.Controller;

import User.*;
import Product.*;
import GUI.*;

public class CustomerController {
    public int borrowProduct(Product product, Customer customer, int bNum) {
        if (bNum < 0) {
            return -1;
        }
        if (product.getNumAvailable() >= bNum) {
            product.borrowThis(customer.getID(), bNum);
            customer.borrowItem(product, bNum);
            System.out.println(product.getName() + " " + customer.getBorrowingNumber(product.getName()));
            return 0;
        } else {
            return 1;
        }
    }
}
