package Product;

import java.util.TreeMap;

/*
 * Product class
 * - name: name of the product
 * - numPossessions: number of the products that are available
 * - numLending: number of the products that are currently being borrowed
 * - numLendingTreeMap: Tree of the information that *who* is borrowing *how many* products
//  * - price: price of the product
 */
public class Product {
    protected String name;
    protected int numPossessions;
    protected int numLending;
    protected TreeMap<Integer, Integer> numLendingTreeMap = new TreeMap<Integer, Integer>();
    /* NOTE: the key is ID who borrows, the value is number of borrowing */

    /* Constructor */
    /* TreeMap は最初何ももってないからこれでいい？ */
    /** コンストラクタ
     * @param name 商品名
     * @param numPossessions 商品数
     */
    public Product(String name, int numPossessions) {
        this.name = name;
        this.numPossessions = numPossessions;
        this.numLending = 0;
        // TODO: Print on the console the following message:
        System.out.println("Product: " + name + " has been added as " + numPossessions + ".");
    }

    /* For Staff */
    /** 製品名変更
     * @param name: name of the product
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
     * @param difNum: difference of the number of the products that are available
     */
    void changeNumPossessions(int difNum) {
        if (this.numPossessions + difNum >= 0) {
            this.numPossessions += difNum;
        } else {
            // TODO: error;
        }
    }

    /* For Custormer */
    /** Use when a new customer borrows this product.
     * @param id: ID of the customer
     * @param num: number of the products that the customer borrows
     */
    void setLendTreeMap(int id, int num) {
        numLendingTreeMap.put(id, num);
    }

    /** Change the number of lending.
     ** Use when a customer returns or borrows additionally.
     * @param id: ID of user who borrows
     * @param difNum: number of borrowing
     */
    void changeNumPossessions(int id, int difNum) {
        final int currentNum;

        if (numLendingTreeMap.containsKey(id)) {
            currentNum = numLendingTreeMap.get(id);
        } else {
            currentNum = 0;
        }

        if (currentNum + difNum >= 0) {
            numLendingTreeMap.put(id, currentNum + difNum);
        } else {
            // TODO: error setting
            System.out.println("Error: " + difNum + " is negative.");

        }
    }
}
