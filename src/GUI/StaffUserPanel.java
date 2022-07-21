package GUI;

import GUI.subPanel.*;
import Product.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StaffUserPanel extends JPanel {

    StaffHeaderPanel headerPanel;
    private static final long serialVersionUID = 1L;
    JComboBox<String> comboBox;

    public ArrayList<String> makeNameList() {

        ArrayList<Product> pList = Main.pModel.getProductList();

        // make ArrayList<String> for product name
        ArrayList<String> pNameList = new ArrayList<String>();
        for (int i = 0; i < pList.size(); i++) {
            pNameList.add(pList.get(i).getName());
        }
        return pNameList;
    }

    // init JComboBox
    public void initComboBox() {
        ArrayList<String> pNameList = makeNameList();
        comboBox = new JComboBox<String>(pNameList.toArray(new String[pNameList.size()]));
        // comboBox.setSelectedIndex(0);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setEditable(true);
        comboBox.setPreferredSize(new Dimension(200, 30));
        comboBox.setMaximumSize(new Dimension(200, 30));

        // add test data
        // comboBox.addItem("test");
        // comboBox.addItem("test2");
        // comboBox.addItem("test1");
    }

    public void changeUserLabel(String uID) {
        headerPanel.changeUserLabel(uID);
    }

    public void prepareComponents() {

        // create JComboBox
        // initComboBox();
        // this.add(comboBox);
        // comboBox.setBounds(100, 200, 200, 20);

        this.setLayout(new BorderLayout());
        headerPanel = new StaffHeaderPanel(1);
        // resize
        headerPanel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT / 12));
        FooterPanel footerPanel = new FooterPanel();

        StaffUserSubPanel productPanel = new StaffUserSubPanel();

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(productPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);

        // color
        this.setBackground(Color.LIGHT_GRAY);
    }
}