package Product;

import java.util.ArrayList;

public class ProductList {
    public ArrayList<Product> pList = new ArrayList<Product>();

    // Add a product to the list
    void addProductList(String name, int numPossessions) {
        Product product = new Product(name, numPossessions);
        pList.add(product);
    }
}
