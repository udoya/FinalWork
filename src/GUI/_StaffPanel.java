package GUI;

import Product.*;
import User.*;
import GUI.subPanel.*;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.*;

public class _StaffPanel extends JFrame implements ActionListener {

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

    _StaffPanel() {
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
        
        

        /*
        String[] startTime = { "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00" };

        String[] endTime = { "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00" };

        startCombo = new JComboBox(startTime);
        startCombo.setPreferredSize(new Dimension(80, 30));

        startCombo.addActionListener(this);

        endCombo = new JComboBox(endTime);
        endCombo.setPreferredSize(new Dimension(80, 30));

        endCombo.addActionListener(this);

        JPanel p = new JPanel();
        p.add(new JLabel("start:"));
        p.add(startCombo);
        p.add(new JLabel("  end:"));
        p.add(endCombo);

        label = new JLabel();
        JPanel labelPanel = new JPanel();
        labelPanel.add(label);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(labelPanel, BorderLayout.PAGE_END);
        */
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