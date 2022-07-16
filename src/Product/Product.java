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
    /** コンストラクタ
     * @param name the name of the product
     * @param numTotal the number of the products
     */
    Product(String name, int numTotal) {
        this.name = name;
        this.numTotal = numTotal;
        this.numAvailable = numTotal;
        // TODO: Print on the console the following message:
        System.out.println("Product: " + name + " has been added as " + numAvailable + ".");
    }
}
