package Product;

import java.util.*;

public class ProductSystem {
    public ArrayList<Product> p2List = new ArrayList<Product>();
    final int error_code = -1;

    private int getIdx(String name) {
        for (int i = 0; i < p2List.size(); i++) {
            if (p2List.get(i).name.equals(name)) {
                return i;
            }
        }
        // error code :-1
        return error_code;
    }

    /* Public Getter */
    public String getName(int idx) {
        return p2List.get(idx).name;
    }

    public int getNumTotal(String name) {
        int idx = getIdx(name);
        if (idx == error_code) {
            return error_code;
        }
        return p2List.get(idx).numTotal;
    }

    public int getNumAvailable(String name) {
        int idx = getIdx(name);
        if (idx == error_code) {
            return error_code;
        }
        return p2List.get(idx).numAvailable;
    }

    public TreeMap<Integer, Integer> getLendingList(String name) {
        int idx = getIdx(name);
        if (idx == error_code) {
            return null;
        }
        return p2List.get(idx).lendingList;
    }

    // 家具一覧表示用
    // 全て表示
    public ArrayList<Product> getProductList() {
        ArrayList<Product> productList = new ArrayList<Product>();
        for (int i = 0; i < this.p2List.size(); i++) {
            productList.add(this.p2List.get(i));
        }
        return productList;
    }

    // 部分一致表示
    public ArrayList<Product> getNameList(String name) {
        ArrayList<Product> productList = new ArrayList<Product>();
        for (int i = 0; i < this.p2List.size(); i++) {
            if (this.p2List.get(i).name.indexOf(name) != -1) {
                productList.add(this.p2List.get(i));
            }
        }
        return productList;
    }

    // Add a product to the list
    void addProduct(String name, int numTotal) {
        Product product = new Product(name, numTotal);
        p2List.add(product);
    }

    // Remove a product from the lists
    void deleteProduct(String name) {
        for (Iterator<Product> it = p2List.iterator(); it.hasNext();) {
            Product product = it.next();
            if (product.name.equals(name)) {
                it.remove();
                // TODO: Print on the console the following message:
                System.out.println("Product: " + name + " has been deleted.");
                break;
            }
            if (!it.hasNext()) {
                // TODO: Print on the console the following message:
                System.out.println("Product: " + name + " does not exist.");
            }
        }
    }

    // Change the name of a product
    void changeName(String oldName, String newName) {
        int idx = -1;
        for (Iterator<Product> it = p2List.iterator(); it.hasNext();) {
            Product product = it.next();
            if (product.name.equals(newName)) {
                // TODO: Print on the console the following message:
                System.out.println("Product: " + newName + " already exists.");
                break;
            }
            if (product.name.equals(oldName)) {
                idx = p2List.indexOf(product);
            }
            if (!it.hasNext()) {
                if (idx != -1) {
                    p2List.get(idx).name = newName;
                    // TODO: Print on the console the following message:
                    System.out.println("Product name changed to " + newName + " from " + oldName + ".");
                } else {
                    // TODO: Print on the console the following message:
                    System.out.println("Product: " + oldName + " does not exist.");
                }
            }
        }
    }
    // // Change the number of a product
    // void changeNum(String name, int numTotal) {
    // int idx = -1;
    // for (Iterator<Product> it = productList.iterator(); it.hasNext();) {
    // Product product = it.next();
    // if (product.name.equals(name)) {
    // idx = productList.indexOf(product);
    // }
    // if (!it.hasNext()) {
    // if (idx != -1) {
    // productList.get(idx).numTotal = numTotal;
    // }
    // }
    // }
    // }

    // void fixProductList(Product product) {
    // productList.set(productList.indexOf(product), product);
    // }

}
