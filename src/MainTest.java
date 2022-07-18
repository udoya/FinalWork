import Product.*;
import User.*;

public class MainTest {
    static ProductModel productModel = new ProductModel();
    static UserModel userModel = new UserModel();

    // NOTE: Create Sample Data (to make another Class?)

    public static void main(String[] args) {
        // WorkFlow

        try {
            productModel.addProduct(new Product("Wooden Chair", 10));
            productModel.addProduct(new Product("Wooden Table", 15));
            productModel.addProduct(new Product("White Wooden Chair", 20));
            productModel.addProduct(new Product("White Wooden Table", 25));
            productModel.addProduct(new Product("Black Wooden Chair", 10));
            productModel.addProduct(new Product("Black Wooden Table", 35));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
