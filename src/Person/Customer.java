package Person;

import java.util.HashMap;

public class Customer extends Person {

    HashMap<String, Integer> borrowedHashMap = new HashMap<String, Integer>();
    /* NOTE: key is the name of Products. value is the number of borrowing */

    void borrowItem(String product, int borrowNum) {
        final int existNum;
        if (borrowedHashMap.containsKey(product)) {
            existNum = borrowedHashMap.get(product);
        } else {
            existNum = 0;
        }

        borrowedHashMap.put(product, existNum + borrowNum);
        // TODO: plz take error message when numPossession < (existNum + borrowNum)
        // Maybe it should be System class's method?

    }

    void returnItem(String product, int returnNum) {
        final int existNum;

        if (borrowedHashMap.containsKey(product)) {
            existNum = borrowedHashMap.get(product);
        } else {
            existNum = 0;
        }

        if (existNum < returnNum) {
            // TODO: plz take error message
        }

        borrowedHashMap.put(product, existNum - returnNum);
    }
}
