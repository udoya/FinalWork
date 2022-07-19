package GUI;

import Product.*;
import User.*;
import GUI.subPanel.*;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.*;

public class StaffUserPanel extends JFrame implements ActionListener {

    JComboBox<String> pCombo;
    JComboBox<String> uCombo;
    JLabel pLabel;
    JLabel uLabel;
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

    StaffUserPanel() {
        String[] pList = new String[pModel.getProductListSize()];
        String[] uList = new String[uModel.getUserListSize()];

        for (int i = 0; i < pModel.getProductListSize(); i++) {
            pList[i] = pModel.getProductList().get(i).getName();
        }
        for (int i = 0; i < uModel.getUserListSize(); i++) {
            uList[i] = uModel.getUserList().get(i).getName() + " @ " + uModel.getUserList().get(i).getID();
        }

        pCombo = new JComboBox<String>(pList);
        uCombo = new JComboBox<String>(uList);

        pLabel = new JLabel("Product");
        uLabel = new JLabel("User");

        JPanel comboPanel = new JPanel();
        comboPanel.add(pLabel);
        comboPanel.add(pCombo);
        comboPanel.add(uLabel);
        comboPanel.add(uCombo);
    }

    public void actionPerformed(ActionEvent e) {
        static int pIndex = pCombo.getSelectedIndex();
        static int uIndex = uCombo.getSelectedIndex();

        static boolean isProduct = true;

        if (isProduct)

        String start;
        String end;

        if (startCombo.getSelectedIndex() == -1) {
            start = "(not select)";
        } else {
            start = (String) startCombo.getSelectedItem();
        }

        if (endCombo.getSelectedIndex() == -1) {
            end = "(not select)";
        } else {
            end = (String) endCombo.getSelectedItem();
        }

        label.setText("START:" + start + ", END:" + end);
    }

    public void prepareComponents() {

    }
}