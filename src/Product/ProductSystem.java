package Product;

import java.util.ArrayList;

public class ProductSystem {
    ArrayList<Product> productList = new ArrayList<Product>();

    void addProductList(Product product) {
        productList.add(product);
    }

    void deleteProductList(Product product) {
        productList.remove(product);
    }

    void fixProductList(Product product) {
        productList.set(productList.indexOf(product), product);
    }

}
