package User;

import java.util.HashMap;
import Product.*;

public class Customer extends User {
    HashMap<String, Integer> borrowingList; // key: productName, value: quantity

    /**
     * Constructor
     */
    public Customer(String name, String id, String password) {
        super(name, id, password);
        isMaster = false;
        borrowingList = new HashMap<String, Integer>();
    }

    /* Getter */
    public HashMap<String, Integer> getBorrowingList() {
        return borrowingList;
    }

    public int getBorrowingNumber(String name) {
        return borrowingList.get(name);
    }

    /* Setter */

    /**
     * Add a product to the borrowing list
     * 
     * @return -1 if borrowing much more than available, 0 if success
     */
    public int borrowItem(Product product, int borrowNum) {
        final int existNum;
        String name = product.getName();

        if (borrowingList.containsKey(name)) {
            existNum = borrowingList.get(name);
        } else {
            existNum = 0;
        }

        // check if enough quantity
        if (existNum + borrowNum > product.getNumAvailable()) {
            return -1;
        } else {
            borrowingList.put(name, existNum + borrowNum);
            return 0;
        }
    }

    /**
     * Return a product
     * 
     * @return -1 if returning much more than borrowing, 0 if success
     */
    public int returnItem(Product product, int returnNum) {
        final int existNum;
        String name = product.getName();
        if (borrowingList.containsKey(name)) {
            existNum = borrowingList.get(name);
        } else {
            existNum = 0;
        }

        // check if enough quantity
        if (existNum - returnNum < 0) {
            return -1;
        } else {
            borrowingList.put(name, existNum - returnNum);
            // if no more borrowing, remove it from the list
            if (existNum - returnNum == 0) {
                borrowingList.remove(name);
            }
            return 0;
        }
    }
}
