package GUI.subPanel;

import GUI.*;
import User.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StaffHeaderPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    JButton productBtn;
    JButton userBtn;
    JButton signOutBtn;
    private JLabel userNameLabel;

    public void changeUserLabel(String uID) {
        User user = Main.uModel.getUser(uID);
        userNameLabel.setText("Staff: " + user.getName());
    }

    class BtnAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == productBtn) {
                Main.mainWindow.setFrontScreenAndFocus(ScreenMode.STAFF);
            }
            if (e.getSource() == userBtn) {
                Main.mainWindow.setFrontScreenAndFocus(ScreenMode.STAFF_USER);
            }
            if (e.getSource() == signOutBtn) {
                // make option pane for confirm sign out
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to sign out?", "Sign Out",
                        JOptionPane.YES_NO_OPTION);
                // if yes then sign out
                if (confirm == JOptionPane.YES_OPTION) {
                    Main.mainWindow.setFrontScreenAndFocus(ScreenMode.LOGIN);
                }
            }
        }
    }

    public StaffHeaderPanel(int BtnIndex) {
        // header 1*6
        this.setLayout(new GridLayout(0, 4, 0, 0));

        // init btn
        productBtn = new JButton("Product");
        userBtn = new JButton("User");
        signOutBtn = new JButton("Sign Out");

        // set action
        productBtn.addActionListener(new BtnAction());
        userBtn.addActionListener(new BtnAction());
        signOutBtn.addActionListener(new BtnAction());

        // show user name and role
        User user = Main.uModel.getUser(Main.uID);
        // print uid
        userNameLabel = new JLabel("Staff:  " + user.getName());

        // Label setting fonts
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userNameLabel.setHorizontalAlignment(JLabel.CENTER);

        // for all btn, change font size
        productBtn.setFont(new Font("Arial", Font.BOLD, 20));
        userBtn.setFont(new Font("Arial", Font.BOLD, 20));
        signOutBtn.setFont(new Font("Arial", Font.BOLD, 20));

        // set btn color depend on BtnIndex
        switch (BtnIndex) {
            case 0:
                productBtn.setBackground(Color.cyan);
                break;
            case 1:
                // change info
                userBtn.setBackground(Color.cyan);
                break;
        }

        this.add(userNameLabel);
        this.add(productBtn);
        this.add(userBtn);
        this.add(signOutBtn);
    }
}