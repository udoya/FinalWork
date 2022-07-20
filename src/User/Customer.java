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

    public int getBorrowingListSize() {
        return borrowingList.size();
    }

    /**
     * get number of borrowing of given product
     * 
     * @param product
     * @return number of borrowing
     */
    public int getBorrowingNumber(Product product) {
        String name = product.getName();
        if (borrowingList.containsKey(name)) {
            return borrowingList.get(name);
        } else {
            return 0;
        }
    }

    public int getBorrowingNumber(String productName) {
        if (borrowingList.containsKey(productName)) {
            return borrowingList.get(productName);
        } else {
            return 0;
        }
    }

    /**
     * Get list of borrowing products
     * 
     * @return list of name
     */
    public String[] getBorrowingItemName() {
        String[] names = new String[borrowingList.size()];
        int i = 0;
        for (String name : borrowingList.keySet()) {
            names[i] = name;
            i++;
        }
        return names;
    }

    /**
     * Show borrowing list
     * 
     * @return List of "ProductName: quantity"
     */
    public String getBorrowingListString() {
        String result = "";
        for (String key : borrowingList.keySet()) {
            result += key + " " + borrowingList.get(key) + "\n";
        }
        return result;
    }

    /**
     * Add a product to the borrowing list
     * 
     * @return -1 if borrowing much more than available, 0 if success
     */
    public void borrowItem(Product product, int borrowNum) throws Exception {
        int existNum;
        String name = product.getName();

        if (borrowNum > product.getNumAvailable()) {
            throw new Exception("Borrowing much more than available");
        }

        if (borrowingList.containsKey(name)) {
            existNum = borrowingList.get(name);
        } else {
            existNum = 0;
        }

        this.borrowingList.put(name, existNum + borrowNum);
        System.out.println("borrowingList: " + name + " " + existNum + borrowNum);
    }

    /**
     * Return a product
     * 
     * @return -1 if returning much more than borrowing, 0 if success
     */
    public void returnItem(Product product, int returnNum) throws Exception {
        int existNum;
        String name = product.getName();

        if (returnNum > getBorrowingNumber(product)) {
            throw new Exception("Returning much more than borrowing");
        }

        if (borrowingList.containsKey(name)) {
            existNum = borrowingList.get(name);
        } else {
            existNum = 0;
        }

        borrowingList.put(name, existNum - returnNum);
        // if no more borrowing, remove it from the list
        if (existNum - returnNum == 0) {
            borrowingList.remove(name);
            System.out.println("BorrowingList: " + name + " removed because no more borrowing");
        }
    }
}
