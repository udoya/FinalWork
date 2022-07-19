package GUI;

import Product.*;
import User.*;
import GUI.subPanel.*;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.*;

public class StaffProductPanel extends JPanel implements ActionListener {
    JLabel pLabel;
    JComboBox<String> pCombo;

    ProductModel pModel = Main.pModel;
    UserModel uModel = Main.uModel;
    HeaderPanel headerPanel = new HeaderPanel(0);

    public void changeUserLabelInHeaderPanel(String uID) {
        headerPanel.changeUserLabel(uID);
    }

    public static void main(String[] args) {
        _StaffPanel frame = new _StaffPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 300, 200);
        frame.setTitle("Title");
        frame.setVisible(true);
    }

    /**
     * ComboPanel of Product
     */
    class ComboPanel extends JPanel {

        int size = pModel.getProductListSize();
        String[] pList = new String[size+1];
        pList[0] = "New Product";   // first item is "New Product" for adding new product
        for (int i = 0; i < size; i++) {
            pList[i+1] = pModel.getProductList().get(i).getName();
        }

        pLabel = new JLabel("Product");
        pCombo = new JComboBox<String>(pList);

        ComboPanel comboPanel = new ComboPanel();
        comboPanel.add(pLabel);
        comboPanel.add(pCombo);
    }

    /**
     * Information of selected product
     * @member Name(field), Available, Total(field)
     * @button Add, Remove, Edit
     */
    class InfoPanel extends JPanel {
        JTextField nameField;
        JLabel availableField; // numAvailable is uneditable
        JTextField totalField;

        /**
         * Button
         */
        class AddButtonAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get name
                String name = nameField.getText();
                // care about empty field
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Name is empty");
                    return;
                }

                // Get total
                int total;
                // care about empty field
                if (totalField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Total is empty");
                    return;
                }
                // care about invalid input
                try {
                    total = Integer.parseInt(totalField.getText());
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Input a positive number.");
                    return;
                }
                if (total < 0) {
                    JOptionPane.showMessageDialog(null, "Input a positive number.");
                    return;
                }

                Product product = new Product(name, total);
                try {
                    pModel.addProduct(product);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    return;
                }
            }
        }

        // TODO: Combo should be reloaded when editing product
        class RemoveButtonAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = pCombo.getSelectedIndex();
                Product p = pModel.getProduct(index-1);
                // remove product from product list
                try {
                    pModel.removeProduct(p);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        class EditButtonAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = pCombo.getSelectedIndex();
                Product p = pModel.getProduct(index-1); // index:0 is "New Product"

                // get new name
                String name = nameField.getText();
                // if name is empty, use old name
                if (name.equals("")) {
                    name = p.getName();
                }
    
                // get new total
                int total;
                // if total is empty, use old total
                if (totalField.getText().equals("")) {
                    total = p.getNumTotal();
                } else {
                    total = Integer.parseInt(totalField.getText());
                }
                // care about smaller total than numLending
                if (total < p.getNumLending()) {
                    JOptionPane.showMessageDialog(null, "Total cannot be less than number of lending");
                } else {
                    p.setNumTotal(total);
                }
            }
        }

        // TODO: BUTTON
        // When adding new product
        InfoPanel() {
            JLabel nameLabel = new JLabel("Name");
            JLabel availableLabel = new JLabel("Available");
            JLabel totalLabel = new JLabel("Total");

            nameField = new JTextField();
            availableField = new JLabel("-");
            totalField = new JTextField();

            JButton addButton = new JButton("Add");
            JPanel namePanel = new JPanel();
            JPanel availablePanel = new JPanel();
            JPanel totalPanel = new JPanel();
            JPanel buttonPanel = new JPanel();
            JPanel infoPanel = new JPanel();

            namePanel.add(nameLabel);
            namePanel.add(nameField);
            availablePanel.add(availableLabel);
            availablePanel.add(availableField);
            totalPanel.add(totalLabel);
            totalPanel.add(totalField);
            buttonPanel.add(addButton);
            infoPanel.add(namePanel);
            infoPanel.add(availablePanel);
            infoPanel.add(totalPanel);
            infoPanel.add(buttonPanel);
            add(infoPanel);
        }
        // When editing product
        InfoPanel(Product p) {
            JLabel nameLabel = new JLabel("Name");
            JLabel availableLabel = new JLabel("Available");
            JLabel totalLabel = new JLabel("Total");

            nameField = new JTextField(p.getName());
            availableField = new JLabel(String.valueOf(p.getNumAvailable()));
            totalField = new JTextField(String.valueOf(p.getNumTotal()));

            JButton removeButton = new JButton("Remove");
            JButton editButton = new JButton("Edit");
            JPanel namePanel = new JPanel();
            JPanel availablePanel = new JPanel();
            JPanel totalPanel = new JPanel();
            JPanel buttonPanel = new JPanel();
            JPanel infoPanel = new JPanel();
            namePanel.add(nameLabel);
            namePanel.add(nameField);
            availablePanel.add(availableLabel);
            availablePanel.add(availableField);
            totalPanel.add(totalLabel);
            totalPanel.add(totalField);
            buttonPanel.add(removeButton);
            buttonPanel.add(editButton);
            infoPanel.add(namePanel);
            infoPanel.add(availablePanel);
            infoPanel.add(totalPanel);
            infoPanel.add(buttonPanel);
            add(infoPanel);
        }
    }

    class ListPanel extends JPanel {
        JList<String> list;
        JScrollPane scrollPane;

        // When adding new product
        ListPanel() {
            //TODO : nothing here
        }

        // When looking product
        ListPanel(Product p) {
            list = new JList<String>(p.getLendingListString());
            scrollPane = new JScrollPane(list);
            scrollPane.createVerticalScrollBar();
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }
    }

    public void actionPerformed(ActionEvent e) {
        InfoPanel infoPanel;
        ListPanel listPanel;

        int index = pCombo.getSelectedIndex();
        // When adding new product
        // Select top or nothing
        if (index == 0 || index == -1) {
            infoPanel = new InfoPanel();
            listPanel = new ListPanel();
        }
        // When editing/removing product
        else {
            Product p = pModel.getProductList().get(index-1);
            infoPanel = new InfoPanel(p);
            listPanel = new ListPanel(p);
        }

        // TODO: Reload left panel

        // add(infoPanel);
        // add(listPanel);
        // JPanel pPanel = new JPanel();
        // pPanel.setLayout(new BorderLayout());
    }

    public void prepareComponents() {
        ComboPanel comboPanel = new ComboPanel();
        add(comboPanel);
    }
}