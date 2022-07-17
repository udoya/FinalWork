package Product;

import java.util.*;

public class ProductSystem {
    private ArrayList<Product> pList;

    public ProductSystem() {
        pList = new ArrayList<Product>();
    }

    /* Getter */
    public ArrayList<Product> getProductList() {
        return pList;
    }

    // Product作ってから
    // pListに追加する
    public void addProduct(Product p) throws Exception {
        for (Product product : pList) {
            if (product.getName().equals(p.getName())) {
                throw new Exception("Product already exists");
            }
        }
        pList.add(p);
        System.out.println("Product:" + p.getName() + "added");
    }

    // 消すProduct確認してから消してね
    // pListから消す
    public void removeProduct(Product p) {
        pList.remove(p);
    }

    // 名前で探す
    // partialMatch
    public ArrayList<Product> findProduct(String name) {
        ArrayList<Product> found = new ArrayList<Product>();
        for (Product product : pList) {
            if (product.getName().indexOf(name) != -1) {
                found.add(product);
            }
        }
        return found;
    }

    // /* Constructor */
    // public ProductSystem() {
    // pList = new ArrayList<Product>();
    // }

    // private int getIdx(String name) {
    // for (int i = 0; i < pList.size(); i++) {
    // if (pList.get(i).name.equals(name)) {
    // return i;
    // }
    // }
    // return -1;
    // }

    // /* Public Getter */
    // public String getName(int idx) {
    // return pList.get(idx).name;
    // }

    // public int getNumTotal(String name) {
    // int idx = getIdx(name);
    // if (idx == -1) {
    // return -1;
    // }
    // return pList.get(idx).numTotal;
    // }

    // public int getNumAvailable(String name) {
    // int idx = getIdx(name);
    // if (idx == -1) {
    // return -1;
    // }
    // return pList.get(idx).numAvailable;
    // }

    // public TreeMap<Integer, Integer> getLendingList(String name) {
    // int idx = getIdx(name);
    // if (idx == -1) {
    // return null;
    // }
    // return pList.get(idx).lendingList;
    // }

    // // 家具一覧表示用
    // // 全て表示
    // public ArrayList<Product> getProductList() {
    // ArrayList<Product> productList = new ArrayList<Product>();
    // for (int i = 0; i < this.pList.size(); i++) {
    // productList.add(this.pList.get(i));
    // }
    // return productList;
    // }

    // // 部分一致表示
    // public ArrayList<Product> getProductList(String name) {
    // ArrayList<Product> productList = new ArrayList<Product>();
    // for (int i = 0; i < this.pList.size(); i++) {
    // if (this.pList.get(i).name.indexOf(name) != -1) {
    // productList.add(this.pList.get(i));
    // }
    // }
    // return productList;
    // }

    // // Add a product to the list
    // void addProduct(String name, int numTotal) {

    // Product product = new Product(name, numTotal);
    // pList.add(product);
    // }

    // // Remove a product from the lists
    // void deleteProduct(String name) {
    // for (Iterator<Product> it = pList.iterator(); it.hasNext();) {
    // Product product = it.next();
    // if (product.name.equals(name)) {
    // it.remove();
    // // TODO: Print on the console the following message:
    // System.out.println("Product: " + name + " has been deleted.");
    // break;
    // }
    // if (!it.hasNext()) {
    // // TODO: Print on the console the following message:
    // System.out.println("Product: " + name + " does not exist.");
    // }
    // }
    // }

    // // Change the name of a product
    // void changeName(String oldName, String newName) {
    // int idx = -1;
    // for (Iterator<Product> it = pList.iterator(); it.hasNext();) {
    // Product product = it.next();
    // if (product.name.equals(newName)) {
    // // TODO: Print on the console the following message:
    // System.out.println("Product: " + newName + " already exists.");
    // break;
    // }
    // if (product.name.equals(oldName)) {
    // idx = pList.indexOf(product);
    // }
    // if (!it.hasNext()) {
    // if (idx != -1) {
    // pList.get(idx).name = newName;
    // // TODO: Print on the console the following message:
    // System.out.println("Product name changed to " + newName + " from " + oldName
    // + ".");
    // } else {
    // // TODO: Print on the console the following message:
    // System.out.println("Product: " + oldName + " does not exist.");
    // }
    // }
    // }
    // }
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
