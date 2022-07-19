package GUI;

import javax.swing.*;

import GUI.subPanel.HeaderPanel;

import java.awt.CardLayout;
import java.awt.*;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    ScreenMode screenMode = ScreenMode.LOGIN;
    static final int WIDTH = 1600;
    static final int HEIGHT = 1000;
    CardLayout layout = new CardLayout();
    LoginPanel lgPanel;
    SignUpPanel signUpPanel;
    CustomerPanel customerPanel;
    StaffPanel staffPanel;

    MainWindow() {
        this.setTitle("Store Management System");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.green);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(400, 300));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void preparePanels() {
        // resize the lgPanel to fit the window
        lgPanel = new LoginPanel();
        this.add(lgPanel, "Sign In");
        lgPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        signUpPanel = new SignUpPanel();
        this.add(signUpPanel, "Sign Up");
        this.pack();

        customerPanel = new CustomerPanel();
        this.add(customerPanel, "Customer");
        this.pack();

        // staffPanel = new StaffPanel();
        // this.add(staffPanel, "Staff");
        // this.pack();
        // System.out.println("test2");
    }

    public void prepareComponents() {
        lgPanel.prepareComponents();
        signUpPanel.prepareComponents();
        customerPanel.prepareComponents();
        // staffPanel.prepareComponents();
    }

    public void setFrontScreenAndFocus(ScreenMode s) {
        if (this.screenMode == ScreenMode.LOGIN && s != ScreenMode.LOGIN) {
            customerPanel.changeUserLabel(Main.uID);
        }

        this.screenMode = s;
        // change screen size
        if (this.screenMode == ScreenMode.LOGIN || this.screenMode == ScreenMode.SIGNUP) {
            // resize
            this.setSize(400, 300);
        } else {
            // resize
            this.setSize(WIDTH, HEIGHT);
        }

        this.setLocationRelativeTo(null);

        switch (this.screenMode) {
            case LOGIN:
                layout.show(this.getContentPane(), "Sign In");
                lgPanel.requestFocus();
                break;
            case HOME:
                // TODO add home screen
                break;
            case SIGNUP:
                layout.show(this.getContentPane(), "Sign Up");
                signUpPanel.requestFocus();
                break;
            case CUSTOMER:
                layout.show(this.getContentPane(), "Customer");
                customerPanel.requestFocus();
                break;
            case STAFF:
                layout.show(this.getContentPane(), "Staff");
                staffPanel.requestFocus();
                break;
        }
    }
}
