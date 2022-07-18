package GUI;

import Product.*;
import User.*;

public class Main {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static MainWindow mainWindow;
    public static ProductModel pModel;
    public static UserModel uModel;

    public static void main(String[] args) {
        pModel = new ProductModel();
        uModel = new UserModel();

        mainWindow = new MainWindow();
        mainWindow.preparePanels();
        mainWindow.prepareComponents();
        mainWindow.setFrontScreenAndFocus(ScreenMode.CUSTOMER);

        mainWindow.setVisible(true);
    }
}
