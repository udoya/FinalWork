import Product.*;
import User.*;

public class Main {
    static ProductSystem productSystem = new ProductSystem();
    static UserSystem userSystem = new UserSystem();

    // NOTE: Create Sample Data (to make another Class?)

    public static void main(String[] args) {
        // WorkFlow

        try {
            productSystem.addProduct(new Product("Wooden Chair", 10));
            productSystem.addProduct(new Product("Wooden Table", 15));
            productSystem.addProduct(new Product("White Wooden Chair", 20));
            productSystem.addProduct(new Product("White Wooden Table", 25));
            productSystem.addProduct(new Product("Black Wooden Chair", 10));
            productSystem.addProduct(new Product("Black Wooden Table", 35));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
