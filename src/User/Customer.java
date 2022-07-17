package User;

import java.util.HashMap;

public class Customer extends User {

    HashMap<String, Integer> borrowingList = new HashMap<String, Integer>();
    /* NOTE: key is the name of Products. value is the number of borrowing */

    /**
     * Constructor
     */
    public Customer(String name, int id, String password) {
        super(name, id, password);
        isMaster = false;
    }

    void borrowItem(String product, int borrowNum) {
        final int existNum;
        if (borrowingList.containsKey(product)) {
            existNum = borrowingList.get(product);
        } else {
            existNum = 0;
        }
        borrowingList.put(product, existNum + borrowNum);

        // TODO: error message when numPossession < (existNum + borrowNum)

        // ???: Maybe it should be System class's method?

    }

    void returnItem(String product, int returnNum) {
        final int existNum;

        if (borrowingList.containsKey(product)) {
            existNum = borrowingList.get(product);
        } else {
            existNum = 0;
        }

        if (existNum < returnNum) {
            // TODO: error case

        }

        borrowingList.put(product, existNum - returnNum);
    }
}
