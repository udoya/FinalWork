import Product.*;

public class Main {

    // NOTE: Create Sample Data (to make another Class?)

    public static void main(String[] args) {
        // WorkFlow

        ProductSystem pList = new ProductSystem();

        try {
            pList.addProduct(new Product("Wooden Chair", 10));
            pList.addProduct(new Product("Wooden Table", 15));
            pList.addProduct(new Product("White Wooden Chair", 20));
            pList.addProduct(new Product("White Wooden Table", 25));
            pList.addProduct(new Product("Black Wooden Chair", 10));
            pList.addProduct(new Product("Black Wooden Table", 35));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
