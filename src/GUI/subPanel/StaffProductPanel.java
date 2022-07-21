package GUI.subPanel;

import Product.*;
import User.*;
import GUI.*;

import javax.swing.*;
// import javax.swing.text.FlowView;

import java.awt.*;
import java.awt.event.*;
// import java.lang.reflect.GenericDeclaration;
// import java.util.*;

public class StaffProductPanel extends JPanel {
    InfoPanel infoPanel;
    ComboPanel comboPanel;
    ListPanel listPanel;
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    JLabel label;
    JComboBox<String> combo;
    DefaultComboBoxModel<String> listModel;

    ProductModel pModel = Main.pModel;
    UserModel uModel = Main.uModel;
    HeaderPanel headerPanel = new HeaderPanel(0);

    public void changeUserLabelInHeaderPanel(String uID) {
        headerPanel.changeUserLabel(uID);
    }

    // make list to display all product list
    public void setProductList() {
        listModel = new DefaultComboBoxModel<>();
        listModel.addElement("New Product");
        for (int i = 0; i < pModel.getProductListSize(); i++) {
            Product p = pModel.getProductList().get(i);
            listModel.addElement(p.getProductString(i));
        }
    }

    /**
     * ComboPanel of Product
     */
    class ComboPanel extends JPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            rightPanel.removeAll();

            int index = combo.getSelectedIndex();
            // When adding new product
            // Select top or nothing
            if (index == 0 || index == -1) {
                infoPanel = new InfoPanel();
                listPanel = new ListPanel();
            }
            // When editing/removing product
            else {
                Product p = pModel.getProductList().get(index - 1);
                infoPanel = new InfoPanel(p);
                listPanel = new ListPanel(p);
            }

            rightPanel.add(infoPanel);
            rightPanel.add(listPanel);
            // repaint the right panel
            rightPanel.revalidate();
            rightPanel.repaint();

        }

        ComboPanel() {
            this.setLayout(new FlowLayout());

            setProductList();

            label = new JLabel("Product");
            combo = new JComboBox<String>(listModel);
            combo.addActionListener(this);
            // font
            label.setFont(new Font("Segoe UI", Font.BOLD, 74));
            label.setHorizontalAlignment(JLabel.CENTER);
            combo.setFont(new Font("Segoe UI", Font.BOLD, 30));

            // resize Label and ComboBox
            label.setPreferredSize(new Dimension(600, 100));
            combo.setPreferredSize(new Dimension(400, 80));

            // this.add(label);
            // this.add(combo);

            this.add(label);
            this.add(combo);

            this.setPreferredSize(new Dimension(800, 600));
        }
    }

    /**
     * Information of selected product
     * 
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
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                    return;
                }
                // Pop-up message
                JOptionPane.showMessageDialog(null, "Product added");

                // TODO: add product to comboBox and pModel
                setProductList();
                combo.setModel(listModel);
                leftPanel.revalidate();
                leftPanel.repaint();
            }
        }

        // TODO: Combo should be reloaded when editing product
        class RemoveButtonAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = combo.getSelectedIndex();
                Product p = pModel.getProduct(index - 1);
                // remove product from product list
                try {
                    pModel.removeProduct(p);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                    return;
                }
                // Pop-up message
                JOptionPane.showMessageDialog(null, "Product removed");

                setProductList();
                combo.setModel(listModel);
                leftPanel.revalidate();
                leftPanel.repaint();

                // remove ComboBox item
                // combo.removeItemAt(index);
            }
        }

        class EditButtonAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = combo.getSelectedIndex();
                Product p = pModel.getProduct(index - 1); // index:0 is "New Product"

                // get new name
                String name = nameField.getText();
                String oldName = p.getName();
                // care about duplicate name
                if (pModel.getProduct(name) != null && !name.equals(oldName)) {
                    JOptionPane.showMessageDialog(null, "Same name already exists.");
                    return;
                }
                // if name is empty, use old name
                if (name.equals("")) {
                    name = oldName;
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
                    return;
                }

                // update product
                pModel.updateProduct(p, name, total);

                // refresh comboBox
                setProductList();
                combo.setModel(listModel);
                rightPanel.revalidate();
                rightPanel.repaint();
                leftPanel.revalidate();
                leftPanel.repaint();
            }
        }

        // When adding new product
        InfoPanel() {
            this.setLayout(new FlowLayout());
            this.setPreferredSize(new Dimension(Main.WIDTH / 2, Main.HEIGHT / 2));
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nameLabel = new JLabel("Name");
            JLabel availableLabel = new JLabel("Available");
            JLabel totalLabel = new JLabel("Total");

            // JLabel Settings

            nameField = new JTextField();
            // resize field
            nameField.setColumns(10);

            // resize field
            totalField = new JTextField();
            totalField.setColumns(10);

            availableField = new JLabel("-");

            JButton addButton = new JButton("Add");
            JPanel namePanel = new JPanel();
            JPanel availablePanel = new JPanel();
            JPanel totalPanel = new JPanel();
            JPanel buttonPanel = new JPanel();

            // btn action listener
            addButton.addActionListener(new AddButtonAction());

            // resize components
            nameField.setColumns(10);
            totalField.setColumns(10);
            availableField.setPreferredSize(new Dimension(100, 100));
            addButton.setPreferredSize(new Dimension(100, 30));

            // fonts
            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            availableLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            nameField.setFont(new Font("Segoe UI", Font.BOLD, 20));
            availableField.setFont(new Font("Segoe UI", Font.BOLD, 20));

            namePanel.add(nameLabel);
            namePanel.add(nameField);
            availablePanel.add(availableLabel);
            availablePanel.add(availableField);
            totalPanel.add(totalLabel);
            totalPanel.add(totalField);
            buttonPanel.add(addButton);
            add(namePanel);
            add(availablePanel);
            add(totalPanel);
            add(buttonPanel);
        }

        // When editing product
        InfoPanel(Product p) {
            this.setLayout(new FlowLayout());
            this.setPreferredSize(new Dimension(Main.WIDTH / 2, Main.HEIGHT / 2));
            // margin
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nameLabel = new JLabel("Name");
            JLabel availableLabel = new JLabel("Available");
            JLabel totalLabel = new JLabel("Total");

            nameField = new JTextField(p.getName());
            availableField = new JLabel(String.valueOf(p.getNumAvailable()));
            totalField = new JTextField(String.valueOf(p.getNumTotal()));

            JButton removeButton = new JButton("Remove");
            JButton editButton = new JButton("Edit");

            removeButton.addActionListener(new RemoveButtonAction());
            editButton.addActionListener(new EditButtonAction());

            // resize components
            nameField.setColumns(36);
            nameField.setPreferredSize(new Dimension(400, 40));
            totalField.setColumns(6);
            totalField.setPreferredSize(new Dimension(300, 40));

            availableField.setPreferredSize(new Dimension(100, 100));
            removeButton.setPreferredSize(new Dimension(70, 30));
            editButton.setPreferredSize(new Dimension(70, 30));

            // fonts
            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            availableLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            nameField.setFont(new Font("Segoe UI", Font.BOLD, 20));
            availableField.setFont(new Font("Segoe UI", Font.BOLD, 20));
            totalField.setFont(new Font("Segoe UI", Font.BOLD, 20));

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 2));
            buttonPanel.add(removeButton);
            buttonPanel.add(editButton);
            buttonPanel.setPreferredSize(new Dimension(Main.WIDTH / 4, Main.HEIGHT / 8));

            this.add(nameLabel);
            this.add(nameField);
            this.add(availableLabel);
            this.add(availableField);
            this.add(totalLabel);
            this.add(totalField);

            // this.add(removeButton);
            // this.add(editButton);
            // this.add(namePanel);
            // this.add(availablePanel);
            // this.add(totalPanel);
            this.add(buttonPanel);

            this.setLayout(new FlowLayout(FlowLayout.LEFT));
        }
    }

    class ListPanel extends JPanel {
        JList<String> list;
        JScrollPane scrollPane;

        // When adding new product
        ListPanel() {
            // TODO : nothing here
            this.setPreferredSize(new Dimension(Main.WIDTH / 4, Main.HEIGHT / 4));
            this.setLayout(new BorderLayout());
        }

        // When looking product
        ListPanel(Product p) {
            list = new JList<String>(p.getLendingListString());
            scrollPane = new JScrollPane(list);
            scrollPane.createVerticalScrollBar();
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setFont(new Font("Segoe UI", Font.BOLD, 25));
            this.setPreferredSize(new Dimension(Main.WIDTH / 4, Main.HEIGHT / 4));
            this.setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
        }

    }

    public void prepareComponents() {
        ComboPanel comboPanel = new ComboPanel();
        add(comboPanel);
    }

    public StaffProductPanel() {
        comboPanel = new ComboPanel();
        infoPanel = new InfoPanel();
        listPanel = new ListPanel();

        comboPanel.setBackground(Color.PINK);

        // comboPanel margin

        this.setLayout(new GridLayout(1, 2));

        leftPanel.setLayout(new FlowLayout());
        rightPanel.setLayout(new FlowLayout());

        leftPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        leftPanel.add(comboPanel);
        leftPanel.setBackground(Color.DARK_GRAY);

        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.add(infoPanel);
        rightPanel.add(listPanel);

        // change color
        this.setBackground(Color.PINK);
        this.add(leftPanel);
        this.add(rightPanel);

    }
}