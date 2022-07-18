package GUI;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.*;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    ScreenMode screenMode = ScreenMode.LOGIN;
    final int WIDTH = 800;
    final int HEIGHT = 600;
    CardLayout layout = new CardLayout();
    LoginPanel lgPanel;
    SignUpPanel signUpPanel;
    CustomerPanel customerPanel;

    MainWindow() {
        this.setTitle("Sign In");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.green);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 2));
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
    }

    public void prepareComponents() {
        lgPanel.prepareComponents();
        signUpPanel.prepareComponents();
        customerPanel.prepareComponents();
    }

    public void setFrontScreenAndFocus(ScreenMode s) {
        this.screenMode = s;
        // change screen size
        // if (this.screenMode != ScreenMode.LOGIN) {
        // // resize
        // this.setSize(WIDTH, HEIGHT);
        // } else {
        // // resize
        // this.setSize(WIDTH / 2, HEIGHT / 2);
        // }

        switch (this.screenMode) {
            case LOGIN:
                this.setSize(WIDTH / 2, HEIGHT / 2);
                layout.show(this.getContentPane(), "Sign In");
                lgPanel.requestFocus();
                break;
            case HOME:
                // TODO add home screen
                break;
            case SIGNUP:
                this.setSize(WIDTH / 2, HEIGHT / 2);
                layout.show(this.getContentPane(), "Sign Up");
                signUpPanel.requestFocus();
                break;
            case CUSTOMER:
                this.setSize(WIDTH, HEIGHT);
                layout.show(this.getContentPane(), "Customer");
                customerPanel.requestFocus();
                break;
            case STAFF:
                // TODO add staff screen
                break;
        }
    }
}
