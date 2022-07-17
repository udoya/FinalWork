package GUI;

import Product.*;
import User.*;

public class CustomerUI {

    void borrowItem(String name, int num) {

    }

    void returnThis(int id, int num) {
        if (this.lendingList.containsKey(id)) {
            if (this.lendingList.get(id) >= num) {
                this.lendingList.put(id, this.lendingList.get(id) - num);
                this.numAvailable += num;
            } else {

            }
        }
    }

}