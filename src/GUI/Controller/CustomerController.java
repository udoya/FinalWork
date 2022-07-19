package GUI.Controller;

import User.*;
import Product.*;
import GUI.*;

public class CustomerController {
    /**
     * Borrow a product
     * @param product
     * @param customer
     * @param bNum
     * @return -1: Invalid bNum, 0: Successful borrow, 1: Borrow much more than available
     */
    public int borrowProduct(Product product, Customer customer, int bNum) {
        if (bNum < 0) {
            return -1;
        }
        if (product.getNumAvailable() >= bNum) {
            product.borrowThis(customer, bNum);
            customer.borrowItem(product, bNum);
            System.out.println(customer.getName() + " just borrowed " + product.getName() + " " + customer.getBorrowingNumber(product));
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Return a product
     * @param product
     * @param customer
     * @param rNum
     * @return -1: Invalid rNum, 0: Successful return, 1: Return much more than borrowed
     */
    public int returnProduct(Product product, Customer customer, int rNum) {
        if (rNum < 0) {
            return -1;
        }
        if (customer.getBorrowingNumber(product) >= rNum) {
            product.returnThis(customer, rNum);
            customer.returnItem(product, rNum);
            System.out.println(customer.getName() + " just returned " + product.getName() + " " + customer.getBorrowingNumber(product));
            return 0;
        } else {
            return 1;
        }
    }
}
