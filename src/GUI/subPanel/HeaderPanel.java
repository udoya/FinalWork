package GUI.subPanel;

import GUI.*;
import User.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HeaderPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    JButton mainBtn;
    JButton infoBtn;
    JButton signOutBtn;
    private JLabel userNameLabel;
    private JLabel userRoleLabel;

    public void changeUserLabel(String uID) {
        User user = Main.uModel.getUser(uID);
        userNameLabel.setText("User Name: " + user.getName());
        userRoleLabel.setText("Role: " + user.getRole());
    }

    class BtnAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == mainBtn) {
            }
            if (e.getSource() == infoBtn) {
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

    public HeaderPanel(int BtnIndex) {

        // header 1*6
        this.setLayout(new GridLayout(0, 6, 0, 0));

        // init btn
        mainBtn = new JButton("Main");
        infoBtn = new JButton("Info");
        signOutBtn = new JButton("Return");
        signOutBtn.addActionListener(new BtnAction());

        // 3 of the test btn
        JButton testBtn = new JButton("Test");
        JButton testBtn2 = new JButton("Test2");
        JButton testBtn3 = new JButton("Test3");

        // show user name and role
        User user = Main.uModel.getUser(Main.uID);
        // print uid
        System.out.println(Main.uID);
        userNameLabel = new JLabel("User Name: " + user.getName());
        userRoleLabel = new JLabel("Role: " + user.getRole());

        // Label setting fonts
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userRoleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // for all btn, change font size
        mainBtn.setFont(new Font("Arial", Font.BOLD, 20));
        infoBtn.setFont(new Font("Arial", Font.BOLD, 20));
        signOutBtn.setFont(new Font("Arial", Font.BOLD, 20));
        testBtn.setFont(new Font("Arial", Font.BOLD, 20));
        testBtn2.setFont(new Font("Arial", Font.BOLD, 20));
        testBtn3.setFont(new Font("Arial", Font.BOLD, 20));

        // set btn color depend on BtnIndex
        switch (BtnIndex) {
            case 0:
                mainBtn.setBackground(Color.cyan);
                break;
            case 1:
                // change info
                infoBtn.setBackground(Color.cyan);

                break;
            case 2:
                // change sign out
                signOutBtn.setBackground(Color.cyan);
                break;
        }

        this.add(mainBtn);
        this.add(infoBtn);
        this.add(testBtn);
        this.add(userNameLabel);
        this.add(userRoleLabel);
        this.add(signOutBtn);
    }
}