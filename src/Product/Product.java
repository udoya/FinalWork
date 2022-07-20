package Product;

import java.util.TreeMap;
import java.io.File;
import java.io.IOException;

import User.*;

/*
 * Product class
 * - name: name of the product
 * - numTotal: number of the products that are in the system
 * - numAvailable: number of the products that are available
 * - lendingList: List of the information that *who(id)* is borrowing *how many* products
//  * - price: price of the product
 */
public class Product {
    private String name;
    private int numTotal;
    private int numAvailable;
    private TreeMap<String, Integer> lendingList; // key: userID, value: quantity
    private File imageFile = new File("no_image.png");

    /**
     * Constructor
     * 
     * @param name     the name of the product
     * @param numTotal the number of the products
     */
    public Product(String name, int numTotal) throws IllegalArgumentException {
        if (name == null || numTotal < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.numTotal = numTotal;
        this.numAvailable = numTotal;
        this.lendingList = new TreeMap<String, Integer>();
    }

    /* Getter */
    public String getName() {
        return name;
    }

    public int getNumTotal() {
        return numTotal;
    }

    public int getNumAvailable() {
        return numAvailable;
    }

    public int getNumLending() {
        return numTotal - numAvailable;
    }

    public TreeMap<String, Integer> getLendingList() {
        return lendingList;
    }

    public int getNumLending(String id) {
        if (lendingList.containsKey(id)) {
            return lendingList.get(id);
        } else {
            return -1;
        }
    }

    /**
     * Show product information
     * 
     * @return "ProductName Available/Total"
     */
    public String getProductString(int index) {
        String result = "";
        result += name + " " + numAvailable + "/" + numTotal;
        return result;
    }

    /**
     * Show Lending List
     * 
     * @return List of "UserID: Quantity"
     */
    public String[] getLendingListString() {
        String[] result = new String[lendingList.size()];
        int i = 0;
        for (String key : lendingList.keySet()) {
            result[i] = key + ": " + lendingList.get(key);
            i++;
        }
        return result;
    }

    public File getImageFile() {
        return imageFile;
    }

    /* Setter */
    void setName(String name) {
        this.name = name;
    }

    void setNumTotal(int newTotal) throws IllegalArgumentException {
        int dif = newTotal - this.numTotal;
        int newAva = this.numAvailable + dif;
        if (newAva < 0) {
            throw new IllegalArgumentException();
        }
        this.numTotal = newTotal;
        this.numAvailable = newAva;
    }

    // public void setNumAvailable(int numAvailable) {
    // this.numAvailable = numAvailable;
    // }

    void setLendingList(TreeMap<String, Integer> lendingList) {
        this.lendingList = lendingList;
    }

    void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    /*
     ******* CONTROLLER *******
     */
    /**
     * Use when a customer borrows
     * 
     * @param c:   the customer
     * @param num: the number of the products that are being borrowed
     */
    public void borrowThis(Customer c, int num) throws Exception {
        String id = c.getID();
        if (num > numAvailable) {
            throw new Exception("Not enough products");
        }
        this.numAvailable -= num;
        if (this.lendingList.containsKey(id)) {
            this.lendingList.put(id, this.lendingList.get(id) + num);
        } else {
            this.lendingList.put(id, num);
        }
    }

    /**
     * Use when a customer returns
     * 
     * @param id:  the ID of the customer
     * @param num: the number of the products that are being returned
     */
    public void returnThis(Customer c, int num) throws Exception {
        String id = c.getID();
        if (!this.lendingList.containsKey(id)) {
            throw new Exception("Not borrowed");
        }
        if (this.lendingList.get(id) < num) {
            throw new Exception("Not enough products");
        }
        this.numAvailable += num;
        if (this.lendingList.get(id) == num) {
            this.lendingList.remove(id);
        } else {
            this.lendingList.put(id, this.lendingList.get(id) - num);
        }
    }

}
