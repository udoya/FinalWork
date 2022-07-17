package User;

import java.util.*;
import Product.*;

public class Staff extends User {

    public Staff(String name, int id, String password) {
        super(name, id, password);
        isMaster = true;
    }

    HashMap<String, Integer> getBorrowingList(int customerId) {
        HashMap<String, Integer> borrowingList = new HashMap<String, Integer>();

        return borrowingList;
    }

    HashMap<String, Integer> getLendingList() {
        return borrowingList;
    }

    // show all information of all furnitures in the list
    public getProductList() {
        return productList;
    }

    // partial match search
    public ArrayList<Product> getProductList(String name) {
        ArrayList<Product> productList = new ArrayList<Product>();
        for (int i = 0; i < this.productList.size(); i++) {
            if (this.productList.get(i).name.indexOf(name) != -1) {
                productList.add(this.productList.get(i));
            }
        }
        return productList;
    }

}
