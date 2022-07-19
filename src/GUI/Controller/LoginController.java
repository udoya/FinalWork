package GUI.Controller;

import User.*;
import GUI.*;

public class LoginController {
    UserModel uModel = Main.uModel;
    static int loginCount = 0;

    /**
     * Login
     * 
     * @param ID
     * @param password
     * @return 0: Invalid ID or password
     *         1: Successful login (staff)
     *         2: Successful login (customer)
     */
    public int login(String ID, String password) {
        loginCount++;
        User user = uModel.getUser(ID);
        if (user == null) {
            return 0;
        } else if (!user.getPassword().equals(password)) {
            return 0;
        } else {
            loginCount = 0;
            Main.uID = ID;
            if (user.isMaster()) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    /**
     * Get name from ID when successfully logged in
     */
    public String getName(String ID) {
        return uModel.getUser(ID).getName();
    }

    /**
     * Check how many times the user has tried to login
     */
    public int getLoginCount() {
        return loginCount;
    }
}