package GUI.Controller;

import Product.*;
import User.*;
import GUI.*;

public class LoginSystem {
    ProductModel pModel = Main.pModel;

    public int login(String ID, String password) {
        if (pModel.checkID(ID)) {
            if (pModel.checkPassword(ID, password)) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }
}