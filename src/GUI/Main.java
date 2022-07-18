package GUI;

import Product.*;
import User.*;

public class Main {
    public static MainWindow mainWindow;
    public static final int WIDTH = MainWindow.WIDTH;
    public static final int HEIGHT = MainWindow.HEIGHT;
    public static ProductModel pModel;
    public static UserModel uModel;

    public static void main(String[] args) {
        pModel = new ProductModel();
        uModel = new UserModel();

        mainWindow = new MainWindow();
        mainWindow.preparePanels();
        mainWindow.prepareComponents();
        mainWindow.setFrontScreenAndFocus(ScreenMode.LOGIN);

        mainWindow.setVisible(true);
    }
}
