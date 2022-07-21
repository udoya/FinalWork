package GUI;

import Product.*;
import User.*;

public class Main {
    public static MainWindow mainWindow;
    public static final int WIDTH = MainWindow.WIDTH;
    public static final int HEIGHT = MainWindow.HEIGHT;
    public static ProductModel pModel;
    public static UserModel uModel;
    public static String uID;

    public static void main(String[] args) {
        pModel = new ProductModel();
        uModel = new UserModel();

        try {
            pModel.addProduct(new Product("Wooden Chair", 10));
            pModel.addProduct(new Product("Wooden Table", 15));
            pModel.addProduct(new Product("White Wooden Chair", 20));
            pModel.addProduct(new Product("White Wooden Table", 25));
            pModel.addProduct(new Product("Black Wooden Chair", 10));
            pModel.addProduct(new Product("Black Wooden Table", 35));
            pModel.addProduct(new Product("Metal Table", 6));
            uModel.addUser(new Customer("Yamada", "c2", "c2"));
            uModel.addUser(new Customer("Taro Tanaka", "customer1", "123456"));
            uModel.addUser(new Staff("Udoya", "admin", "admin"));
            uID = "customer1";

            Customer c = (Customer) uModel.getUser(uID);
            Product p = pModel.getProduct("Wooden Chair");
            int bn = 3;
            c.borrowItem(p, bn);
            p.borrowThis(c, bn);

        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mainWindow = new MainWindow();
        mainWindow.preparePanels();
        mainWindow.prepareComponents();
        mainWindow.setFrontScreenAndFocus(ScreenMode.LOGIN);

        mainWindow.setVisible(true);
    }
}
