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
    Customer c = (Customer) uModel.getUser(uID);

    // make list to display all product list
    public void setBorrowingList() {
        listModel = new DefaultListModel<>();
        productIdList = new ArrayList<Integer>(); // make list to store index (of borrowing product) in pModel

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

            switch(CC.returnProduct(p, c, rNum)) {
                case -1:
                    JOptionPane.showMessageDialog(null, "Please enter a positive number.");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Please enter a number less than or equal to " + c.getBorrowingNumber(p));
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "You returned " + rNum + " " + p.getName() + " successfully.");
                    break;

            }
            pModel.updateProduct(p);
            uModel.updateUser(c);
            setBorrowingList();
            list.setModel(listModel);
        }

    }

    public BorrowingListPanel() {
        setBorrowingList();

        label = new JLabel("Your Current Borrowing");
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

        JButton returnBtn = new JButton("Return");
        ReturnBtnAction returnBtnListener = new ReturnBtnAction();
        returnBtn.addActionListener(returnBtnListener);


        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        returnField = new JTextField("0");
        // resize the text field and btn
        returnField.setPreferredSize(new Dimension(250, 100));
        returnField.setFont(new Font("Segoe UI", Font.PLAIN, 40));

        returnBtn.setPreferredSize(new Dimension(120, 60));
        returnBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        // btn color
        returnBtn.setBackground(Color.ORANGE);
        returnBtn.setForeground(Color.DARK_GRAY);

        returnPanel.add(returnField);
        returnPanel.add(returnBtn);

        returnPanel.setBackground(Color.PINK);
        this.setBackground(Color.PINK);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(scrollPanel);
        this.add(returnPanel);

    }
}