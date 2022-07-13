package Product;

import java.util.TreeMap;

public class Product {
    protected String name;
    protected int numPossessions;
    protected TreeMap<Integer, Integer> numLendingTreeMap = new TreeMap<Integer, Integer>();
    /* NOTE: the key is ID who borrows, the value is number of borrowing */

    public Product(String name2, int numPossessions2) {
        // it is called when a new product is created
        // TODO: make this constructor
    }

    void setName(String name) {
        this.name = name;
    }

    void setNumPossessions(int number) {
        this.numPossessions = number;
    }

    void setLendTreeMap(int id, int number) {
        numLendingTreeMap.put(id, number);
    }

    void changeNumPossessions(int difNum) {
        if (this.numPossessions + difNum >= 0) {
            this.numPossessions += difNum;
        } else {
            // TODO: error;
        }
    }

    void changeLendTreeMap(int id, int difNum) {
        final int existNum;

        if (numLendingTreeMap.containsKey(id)) {
            existNum = numLendingTreeMap.get(id);
        } else {
            existNum = 0;
        }

        if (existNum + difNum >= 0) {
            numLendingTreeMap.put(id, existNum + difNum);
        } else {
            // TODO: error setting

        }
    }
}
