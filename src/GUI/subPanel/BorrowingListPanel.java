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

// For right side of Customer Panel
// Contains: Search Panel(field & button), Product List Panel(Scroll), Borrow Panel(field & button)
public class BorrowingListPanel extends JPanel {
    private JLabel label;
    private static DefaultListModel<String> listModel;
    private static ArrayList<Integer> productIdList;
    private JList<String> list;
    private JTextField returnField;

    ProductModel pModel = Main.pModel;
    UserModel uModel = Main.uModel;
    String uID = Main.uID;
    Customer c = uModel.getCustomer(uID);

    public void setBorrowingList() {
        listModel = new DefaultListModel<>();
        productIdList = new ArrayList<Integer>();

        String[] names = c.getBorrowingItemName();
        for (String name : names) {
            listModel.addElement(name + " " + c.getBorrowingNumber(name));
            productIdList.add(pModel.getIndex(name));
        }
    }


    class ReturnBtnAction implements ActionListener {
        CustomerController CC = new CustomerController();

        @Override
        public void actionPerformed(ActionEvent e) {
            int rNum = Integer.parseInt(returnField.getText());

            int index = list.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Please select a product to return.");
                return;
            }
            int productId = productIdList.get(index);
            Product p = pModel.getProduct(productId);
            Customer c = uModel.getCustomer(uID);
            switch(CC.returnProduct(p, c, rNum)) {

            }
        }

    }
    class BorrowBtnAction implements ActionListener {
        CustomerController CC = new CustomerController();

        @Override
        public void actionPerformed(ActionEvent e) {
            int bNum = Integer.parseInt(borrowField.getText());

            int index = list.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Please select a product");
                return;
            }
            int pId = productIdList.get(index);
            Product p = pModel.getProduct(pId);
            Customer c = uModel.getCustomer(uID);
            switch (CC.borrowProduct(p, c, bNum)) {
                case -1:
                    JOptionPane.showMessageDialog(null, "Input positive number.");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Sorry, not enough stock.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "You borrowed " + bNum + " " + p.getName() + " successfully.");
                    break;
            }
            pModel.updateProduct(p);
            // System.out.println("Av:" + p.getNumAvailable());
            // System.out.println("To:" + p.getNumTotal());
            // System.out.println(pModel.getProduct(productIdList.get(index)).getNumAvailable());
            // System.out.println(pModel.getProduct(productIdList.get(index)).getNumTotal());
            setProductList();

            // update list
            list.setModel(listModel);
        }
    }

    public ProductListPanel() {
        setProductList();

        label = new JLabel("Product List");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setHorizontalAlignment(JLabel.CENTER);

        list = new JList<String>(listModel);
        list.setVisibleRowCount(10);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        list.setBackground(Color.WHITE);
        list.setForeground(Color.BLACK);
        list.setFont(new Font("Arial", Font.PLAIN, 40));
        list.setSelectedIndex(0);
        list.setSelectionBackground(Color.BLACK);
        list.setSelectionForeground(Color.WHITE);
        list.setVisible(true);

        JScrollPane scrollPanel = new JScrollPane(list);
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