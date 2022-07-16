package Product;

import java.util.TreeMap;

/*
 * Product class
 * - name: name of the product
 * - numPossessions: number of the products that are available
 * - numTotal: number of the products that are in the system
 * - numLendingTreeMap: Tree of the information that *who* is borrowing *how many* products
//  * - price: price of the product
 */
public class Product {
    protected String name;
    protected int numTotal;
    protected int numPossessions;
    protected TreeMap<Integer, Integer> numLendingTreeMap = new TreeMap<Integer, Integer>();
    /* NOTE: the key is ID who borrows, the value is number of borrowing */

    /* Constructor */
    /* TreeMap は最初何ももってないからこれでいい？ */
    /** コンストラクタ
     * @param name the name of the product
     * @param numPossessions the number of the products
     */
    public Product(String name, int numTotal) {
        this.name = name;
        this.numTotal = numTotal;
        this.numPossessions = numTotal;
        // TODO: Print on the console the following message:
        System.out.println("Product: " + name + " has been added as " + numPossessions + ".");
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
     * @param difNum: a difference of the number of the products that are available
     */
    void changeNumPossessions(int difNum) {
        if (this.numPossessions + difNum >= 0) {
            this.numPossessions += difNum;
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
        if (this.numPossessions >= num) {
            this.numPossessions -= num;
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
                this.numPossessions += num;
            } else {
                
            }
        }
    }
}
