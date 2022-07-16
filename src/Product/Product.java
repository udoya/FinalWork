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
public class Product {
    protected String name;
    protected int numTotal;
    protected int numAvailable;
    protected TreeMap<Integer, Integer> numLendingTreeMap = new TreeMap<Integer, Integer>();
    /* NOTE: the key is ID who borrows, the value is number of borrowing */

    /* Constructor */
    /* TreeMap は最初何ももってないからこれでいい？ */
    /** コンストラクタ
     * @param name the name of the product
     * @param numTotal the number of the products
     */
    public Product(String name, int numTotal) {
        this.name = name;
        this.numTotal = numTotal;
        this.numAvailable = numTotal;
        // TODO: Print on the console the following message:
        System.out.println("Product: " + name + " has been added as " + numAvailable + ".");
    }

    /* For Staff */
    /** 製品名変更
     * @param name: the name of the product
     */
    // TODO: What if the name is already in the list? Maybe it should be in ProductSystem?
    void changeName(String name) {
        String tmp = this.name;
        this.name = name;
        // TODO: Print on the console the following message:
        System.out.println("Product name changed to " + name + " from " + tmp + ".");
    }

    /* For Staff */
    /** 在庫追加・削除
     * @param difNum: amount of change
     */
    void changenumAvailable(int difNum) {
        if (this.numAvailable + difNum >= 0) {
            this.numAvailable += difNum;
        } else {
            // TODO: error;
        }
    }

    /* For Custormer */
    /** Use when a customer borrows
     * @param id: the ID of the customer
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
}
