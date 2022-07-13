package Product;

import java.util.ArrayList;

public class ProductList {
    ArrayList<Product> productList = new ArrayList<Product>();

    // Add a product to the list
    void addProductList(String name, int numPossessions) {
        Product product = new Product(name, numPossessions);
        productList.add(product);
    }
}
