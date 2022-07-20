package GUI.subPanel;

import GUI.*;
import GUI.Controller.*;
import GUI.subPanel.HeaderPanel;
import Product.*;
import User.*;

import GUI.subPanel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//BUG cannot borrow more than harf. (if borrow more than half, it will be bug)
//BUG cannot borrow things that already borrowed. (if borrow things that already borrowed, it will be bug)

// For left side of Customer Panel
// Contains: Search Panel(field & button), Product List Panel(Scroll), Borrow Panel(field & button)
public class ProductListPanel extends JPanel {
    private JLabel label;
    private JTextField searchField;
    private static DefaultListModel<String> plistModel;
    private static ArrayList<Integer> productIdList;
    private static JList<String> plist;
    private JTextField borrowField;

    static ProductModel pModel = Main.pModel;
    UserModel uModel = Main.uModel;
    String uID = Main.uID;

    // make list to display all product list
    public void setProductList() {
        plistModel = new DefaultListModel<>();
        productIdList = new ArrayList<Integer>();

        // "ProductName Available/Total"
        for (int i = 0; i < pModel.getProductListSize(); i++) {
            Product p = pModel.getProduct(i);
            plistModel.addElement(p.getProductString(i));
            productIdList.add(i);
        }
    }

    /*
     * After a user write partially name of what to search, this button will be
     * clicked.
     */
    class SearchBtnAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            plistModel = new DefaultListModel<>();
            productIdList = new ArrayList<Integer>();

            // make list to display search result
            String tempText = searchField.getText();
            for (int i = 0; i < pModel.getProductListSize(); i++) {
                Product p = pModel.getProduct(i);
                // TODO: now, search with distinct product name(upper and lower case)
                if (p.getName().contains(tempText)) {
                    plistModel.addElement(p.getProductString(i));
                    productIdList.add(i);
                }
            }
            // update list
            pSetModel();
            // CustomerPanel.borrowingListPanel.bSetModel();
            // CustomerPanel.borrowingListPanel.bSetModel();
            // CustomerPanel.productListPanel.pSetModel();

            // BorrowingListPanel.blist.setModel(BorrowingListPanel.blistModel);
            // repaint
            // list.repaint();
        }
    }

    public void pSetModel() {
        plist.setModel(plistModel);
    }

    /**
     * After a user selects a product and write how many to borrow, this button will
     * be clicked.
     * 
     * @see GUI.Controller.CustomerController#borrowProduct(Product, Customer, int)
     */
    class BorrowBtnAction implements ActionListener {
        CustomerController CC = new CustomerController();

        @Override
        public void actionPerformed(ActionEvent e) {
            int bNum;
            try {
                bNum = Integer.parseInt(borrowField.getText());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Please enter a positive number.");
                return;
            }
            int index = plist.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Please select a product");
                return;
            }
            int pId = productIdList.get(index);
            Product p = pModel.getProduct(pId);
            Customer c = (Customer) uModel.getUser(uID);
            switch (CC.borrowProduct(p, c, bNum)) {
                case -1:
                    JOptionPane.showMessageDialog(null, "Please enter a positive number.");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Sorry, not enough stock.");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Sorry, something happened.");
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null,
                            "You borrowed " + bNum + " " + p.getName() + " successfully.");
                    break;
            }
            pModel.updateProduct(p);
            System.out.println(c.getBorrowingItemName());
            uModel.updateUser(c);
            // System.out.println("Av:" + p.getNumAvailable());
            // System.out.println("To:" + p.getNumTotal());
            // System.out.println(pModel.getProduct(productIdList.get(index)).getNumAvailable());
            // System.out.println(pModel.getProduct(productIdList.get(index)).getNumTotal());
            setProductList();
            CustomerPanel.borrowingListPanel.setBorrowingList();

            // update list
            // pSetModel();
            CustomerPanel.borrowingListPanel.bSetModel();
            CustomerPanel.productListPanel.pSetModel();

            // repaint CustomerPanel
            CustomerPanel.productListPanel.repaint();
            CustomerPanel.borrowingListPanel.repaint();

            // // repaint
            // plist.repaint();
        }
    }

    public ProductListPanel() {
        setProductList();

        label = new JLabel("Product List");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setHorizontalAlignment(JLabel.CENTER);

        plist = new JList<String>(plistModel);
        plist.setVisibleRowCount(10);
        plist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        plist.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        plist.setBackground(Color.WHITE);
        plist.setForeground(Color.BLACK);
        plist.setFont(new Font("Arial", Font.PLAIN, 40));
        plist.setSelectedIndex(0);
        plist.setSelectionBackground(Color.BLACK);
        plist.setSelectionForeground(Color.WHITE);
        plist.setVisible(true);

        JScrollPane scrollPanel = new JScrollPane(plist);
        scrollPanel.createVerticalScrollBar();
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton searchBtn = new JButton("Search");
        SearchBtnAction searchBtnListener = new SearchBtnAction();
        searchBtn.addActionListener(searchBtnListener);
        // searchBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        searchField = new JTextField("");
        // resize the text field and btn
        searchField.setPreferredSize(new Dimension(500, 60));
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        searchBtn.setPreferredSize(new Dimension(120, 60));
        // fontsize
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        searchBtn.setBackground(Color.ORANGE);
        searchBtn.setForeground(Color.DARK_GRAY);

        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        JButton borrowBtn = new JButton("Borrow");
        BorrowBtnAction borrowBtnListener = new BorrowBtnAction();
        borrowBtn.addActionListener(borrowBtnListener);
        // searchBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel borrowPanel = new JPanel();
        borrowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        borrowField = new JTextField("0");
        // resize the text field and btn
        borrowField.setPreferredSize(new Dimension(250, 100));
        borrowField.setFont(new Font("Segoe UI", Font.PLAIN, 40));

        borrowBtn.setPreferredSize(new Dimension(120, 60));
        borrowBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        // btn color
        borrowBtn.setBackground(Color.ORANGE);
        borrowBtn.setForeground(Color.DARK_GRAY);

        borrowPanel.add(borrowField);
        borrowPanel.add(borrowBtn);

        borrowPanel.setBackground(Color.PINK);
        this.setBackground(Color.PINK);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(searchPanel);
        this.add(scrollPanel);
        this.add(borrowPanel);

    }
}