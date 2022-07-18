package Product;

import java.util.*;
import java.util.HashMap;

public class ProductModel {
    private ArrayList<Product> pList;

    /* Constructor */
    public ProductModel() {
        pList = new ArrayList<Product>();
    }

    /* Getter */
    public ArrayList<Product> getProductList() {
        return pList;
    }

    /**
     * Get product by idx
     * 
     * @param index
     * @return product
     */
    public Product getProduct(int index) {
        return pList.get(index);
    }

    /**
     * Get list of product by name (partially match)
     * 
     * @param name
     * @return found: list of <index of pList, product>,
     *         otherwise: null
     */
    public HashMap<Integer, Product> findProduct(String name) {
        HashMap<Integer, Product> found = new HashMap<Integer, Product>();
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getName().contains(name)) {
                found.put(i, pList.get(i));
            }
        }
        return found;
    }

    /* Setter */
    /**
     * Add a product to the product list
     * 
     * @param p the product to be added
     */
    public void addProduct(Product p) throws Exception {
        for (Product product : pList) {
            if (product.getName().equals(p.getName())) {
                throw new Exception("Product already exists");
            }
        }
        pList.add(p);
        System.out.println("Product:" + p.getName() + "added");
    }

    /**
     * Remove product from pList
     * 
     * @param p the product to be removed
     */
    public void removeProduct(Product p) {
        pList.remove(p);
    }

}
