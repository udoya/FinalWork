package GUI.Controller;

import User.*;
import Product.*;
import GUI.*;

public class CustomerController {
    String uID = LoginPanel.ID;

    public int borrowProduct(Product product, int bNum) {
        if (bNum < 0) {
            return -1;
        }
        if (product.getNumAvailable() >= bNum) {
            product.setNumAvailable(product.getNumAvailable() - bNum);
            return 0;
        } else {
            return 1;
        }
    }
}