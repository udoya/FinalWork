package GUI;

import GUI.Controller.*;
import Product.*;
import User.*;

import GUI.subPanel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Panel1: Show My Profile
 * Panel2: List of products and List of borrowing: borrow, return
 */

public class CustomerPanel extends JPanel {

    JTextField IDField;
    JTextField passwordField;
    JButton signInBtn;
    JButton signUpBtn;
    JButton quitButton;
    HeaderPanel headerPanel;

    public static ProductListPanel productListPanel;
    public static BorrowingListPanel borrowingListPanel;

    /* ActionListener */
    class BtnAction implements ActionListener {
        CustomerController CC = new CustomerController();

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            // if push search / borrow / return button, refresh the list of products
            if (command.equals("Search")) {
                // console
                System.out.println("Search");
                productListPanel.setProductList();
                borrowingListPanel.setBorrowingList();
            } else if (command.equals("Borrow")) {
                productListPanel.setProductList();
                borrowingListPanel.setBorrowingList();
            } else if (command.equals("Return")) {
                productListPanel.setProductList();
                borrowingListPanel.setBorrowingList();
            }
        }
    }

    public void changeUserLabel(String uID) {
        headerPanel.changeUserLabel(uID);
    }

    public void prepareComponents() {
        this.setLayout(new BorderLayout());
        headerPanel = new HeaderPanel(0);
        // resize
        headerPanel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT / 12));

        FooterPanel footerPanel = new FooterPanel();
        productListPanel = new ProductListPanel();
        borrowingListPanel = new BorrowingListPanel();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(productListPanel);
        centerPanel.add(borrowingListPanel);
        // centerPanel color
        centerPanel.setBackground(Color.DARK_GRAY);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);

        // color
        this.setBackground(Color.LIGHT_GRAY);

    }
}