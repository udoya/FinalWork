package GUI.Controller;

import User.*;
import Product.*;
import GUI.*;

public class CustomerController {
    /**
     * Borrow a product
     * * After this, you must update product and customer
     * 
     * @param product
     * @param customer
     * @param bNum
     * @return -1: Invalid bNum, 0: Successful borrow, 1: Borrow much more than available
     */
    public int borrowProduct(Product p, Customer c, int num) {
        if (num <= 0) {
            return -1;
        }
        if (p.getNumAvailable() >= num) {
            try {
                c.borrowItem(p, num);
                p.borrowThis(c, num);
            } catch (Exception e) {
                return 2;
            }

            System.out.println(c.getName() + " just borrowed " + p.getName() + " " + num);
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Return a product
     * * After this, you must update product and customer
     * 
     * @param product
     * @param customer
     * @param rNum
     * @return -1: Invalid rNum, 0: Successful return, 1: Return much more than borrowed
     */
    public int returnProduct(Product p, Customer c, int num) {
        if (num <= 0) {
            return -1;
        }
        if (c.getBorrowingNumber(p) >= num) {
            try {
                p.returnThis(c, num);
                c.returnItem(p, num);
            } catch (Exception e) {
                return 2;
            }
            System.out.println(c.getName() + " just returned " + p.getName() + " " + num);
            return 0;
        } else {
            return 1;
        }
    }
}
