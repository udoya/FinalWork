package Product;

import java.util.TreeMap;

/*
 * Product class
 * - name: name of the product
 * - numTotal: number of the products that are in the system
 * - numAvailable: number of the products that are available
 * - numLendingTreeMap: Tree of the information that *who* is borrowing *how many* products
//  * - price: price of the product
 */
class Product {
    String name;
    int numTotal;
    int numAvailable;
    TreeMap<Integer, Integer> numLendingTreeMap = new TreeMap<Integer, Integer>();
    /* NOTE: the key is ID who borrows, the value is number of borrowing */

    /* Constructor */
    /* TreeMap は最初何ももってないからこれでいい？ */
    /**
     * Constructor
     * 
     * @param name     the name of the product
     * @param numTotal the number of the products
     */
    Product(String name, int numTotal) {
        this.name = name;
        this.numTotal = numTotal;
        this.numAvailable = numTotal;
        // TODO: Print on the console the following message:
        System.out.println("Product: " + name + " has been added as " + numAvailable + ".");
    }

    /* For Staff */
    /**
     * 製品名変更
     * 
     * @param name: the name of the product
     */
    // TODO: What if the name is already in the list? Maybe it should be in
    // ProductSystem?
    void changeName(String name) {
        String tmp = this.name;
        this.name = name;
        // TODO: Print on the console the following message:
        System.out.println("Product name changed to " + name + " from " + tmp + ".");
    }

    /* For Staff */
    /**
     * 在庫追加・削除
     * 
     * @param difNum: a difference of the number of the products that are available
     */
    void changenumAvailable(int difNum) {
        if (this.numAvailable + difNum >= 0) {
            this.numAvailable += difNum;
        } else {
            // TODO: error;
        }
    }

    /* For Customer */
    /**
     * Use when a customer borrows
     * 
     * @param id:  the ID of the customer
     * @param num: the number of the products that are being borrowed
     */
    void borrowThis(int id, int num) {
        if (this.numAvailable >= num) {
            this.numAvailable -= num;
            if (this.numLendingTreeMap.containsKey(id)) {
                this.numLendingTreeMap.put(id, this.numLendingTreeMap.get(id) + num);
            } else {
                this.numLendingTreeMap.put(id, num);
            }
        }
    }

    void returnThis(int id, int num) {
        if (this.numLendingTreeMap.containsKey(id)) {
            if (this.numLendingTreeMap.get(id) >= num) {
                this.numLendingTreeMap.put(id, this.numLendingTreeMap.get(id) - num);
                this.numAvailable += num;
            } else {

            }
        }
    }

    /**
     * Use when a new customer borrows this product.
     * 
     * @param id:  ID of the customer
     * @param num: the number of the products that the customer borrows
     */
    void setLendTreeMap(int id, int num) {
        numLendingTreeMap.put(id, num);
    }

    /**
     * Change the number of lending.
     ** Use when a customer returns or borrows additionally.
     * 
     * @param id:        ID of the customer
     * @param borrowNum: the number of borrowing (positive) or returning (negative)
     */
    void changenumAvailable(int id, int borrowNum) {
        final int currentNum;

        // check whether the AvailableNum - borrowNum is negative or not.
        if (this.numAvailable < borrowNum) {
            // TODO: error
            System.out.println("Error: The number of available products is not enough.");
            return;
        }

        // check whether the lendingTreeMap contains the ID or not.
        if (numLendingTreeMap.containsKey(id)) {
            currentNum = numLendingTreeMap.get(id);
        } else {
            currentNum = 0;
        }

        // change the number of lending.
        int afterNum = currentNum + borrowNum;

        if (afterNum < 0) {
            // TODO: error setting
            System.out.println("Error: you can't borrow negative number of products.");
            return;
        }

        // change the number of lending.
        numLendingTreeMap.put(id, afterNum);
        // change the number of available products.
        this.numAvailable -= borrowNum;

        System.out.println("Product: " + this.name + " has been added as " + numAvailable + ".");
        System.out.println("Now you have " + afterNum + " of " + this.name + ".");
    }
}
