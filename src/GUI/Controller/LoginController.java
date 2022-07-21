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
        // test print
        System.out.println("Try Login: " + ID);
        loginCount++;
        User user = uModel.getUser(ID);
        // check existance
        if (user != null) {
            // check password
            if (user.getPassword().equals(password)) {
                if (user instanceof Staff) {
                    return 0;
                } else if (user instanceof Customer) {
                    return 1;
                }
            }
        }
        // too many login attempts
        if (loginCount >= 5) {
            return 2;
        }
        return 3;
    }

    /**
     * Get name from ID when successfully logged in
     */
    public String getName(String ID) {
        return uModel.getUser(ID).getName();
    }
}