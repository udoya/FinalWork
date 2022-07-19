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
        JLabel pLabel;
        JComboBox<String> pCombo;
        int size = pModel.getProductListSize();
        String[] pList = new String[size+1];
        pList[0] = "Add Product";   // first item is "Product" for adding new product
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
        // When adding new product
        InfoPanel() {
            JLabel nameLabel = new JLabel("Name");
            JLabel availableLabel = new JLabel("Available");
            JLabel totalLabel = new JLabel("Total");
            JTextField nameField = new JTextField();
            JLabel availableField = new JLabel("-");
            JTextField totalField = new JTextField();
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
            JTextField nameField = new JTextField(p.getName());
            JLabel availableField = new JLabel(String.valueOf(p.getNumAvailable()));
            JTextField totalField = new JTextField(String.valueOf(p.getNumTotal()));
            JButton addButton = new JButton("Add");
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
            buttonPanel.add(addButton);
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

        // When editing product
        ListPanel(Product p) {

            // String[] pList = new String[pModel.getProductListSize()];
            // for (int i = 0; i < pModel.getProductListSize(); i++) {
            //     pList[i] = pModel.getProductList().get(i).getName();
            // }
            // list = new JList<String>(pList);
            // scrollPane = new JScrollPane(list);
            // add(scrollPane);
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