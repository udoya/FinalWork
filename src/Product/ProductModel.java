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

    public int getProductListSize() {
        return pList.size();
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
     * Get product by name
     * 
     * @param name
     * @return product
     */
    public Product getProduct(String name) {
        for (Product p : pList) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Get index of product by name
     * @param s
     * @return index or -1 if not found
     */
    public int getIndex(String s) {
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getName().equals(s)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Get index of given product
     * @param p
     * @return index or -1 if not found
     */
    public int getIndex(Product p) {
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getName().equals(p.getName())) {
                return i;
            }
        }
        return -1;
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
    public void removeProduct(Product p) throws Exception {
        int index = getIndex(p);
        if (pList.get(index).equals(p)) {
            if (p.getNumLending() > 0) {
                throw new Exception("Cannot remove product being lent");
            }
            pList.remove(index);
            System.out.println("Product:" + p.getName() + "removed");
        } else {
            throw new Exception("Product not found");
        }
    }

    public void changeName(Product p, String newName) {
        p.setName(newName);
    }

    /**
     * Simply update product
     * Use only when you don't change ID
     * 
     * @param p
     */
    public void updateProduct(Product p) {
        int index = getIndex(p);
        if (index >= 0) {
            pList.set(index, p);
            System.out.println("Product:" + p.getName() + "is updated");
        }
    }

    /**
     * Update product
     * *This method is necessary because you can't find a product by name if it's changed
     * 
     * @param p the product to be updated
     * @param name new name
     * @param total new total 
     */
    public void updateProduct(Product p, String name, int total) {
        int index = getIndex(p);
        p.setName(name);
        p.setNumTotal(total);
        if (index >= 0) {
            pList.set(index, p);
            System.out.println("Product:" + p.getName() + "is updated");
        }
    }

}
