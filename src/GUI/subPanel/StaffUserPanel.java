package GUI.subPanel;

import Product.*;
import User.*;
import GUI.*;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.*;

public class StaffUserPanel extends JPanel implements ActionListener {
    JLabel uLabel;
    JComboBox<String> uCombo;

    ProductModel pModel = Main.pModel;
    UserModel uModel = Main.uModel;
    HeaderPanel headerPanel = new HeaderPanel(0);

    public void changeUserLabelInHeaderPanel(String uID) {
        headerPanel.changeUserLabel(uID);
    }

    /**
     * ComboPanel of User
     */
    class ComboPanel extends JPanel {

        ComboPanel() {

            int size = uModel.getUserListSize();
            String[] uList = new String[size + 1];
            uList[0] = "New User"; // first item is "New User" for adding new User
            for (int i = 0; i < size; i++) {
                uList[i + 1] = uModel.getUserList().get(i).getName();
            }

            uLabel = new JLabel("User");
            uCombo = new JComboBox<String>(uList);

            ComboPanel comboPanel = new ComboPanel();
            comboPanel.add(uLabel);
            comboPanel.add(uCombo);
        }
    }

    /**
     * Information of selected User
     * 
     * @member Name(field), ID(field), password(field)
     * @button Add, Remove, Edit
     */
    class InfoPanel extends JPanel {
        // When adding new User
        InfoPanel() {
            JLabel nameLabel = new JLabel("Name");
            JLabel idLabel = new JLabel("ID");
            JLabel pwdLabel = new JLabel("Password");
            JTextField nameField = new JTextField();
            JTextField idField = new JTextField();
            JTextField pwdField = new JTextField();
            JButton addButton = new JButton("Add");
            JPanel namePanel = new JPanel();
            JPanel idPanel = new JPanel();
            JPanel pwdPanel = new JPanel();
            JPanel buttonPanel = new JPanel();
            JPanel infoPanel = new JPanel();
            namePanel.add(nameLabel);
            namePanel.add(nameField);
            idPanel.add(idLabel);
            idPanel.add(idField);
            pwdPanel.add(pwdLabel);
            pwdPanel.add(pwdField);
            buttonPanel.add(addButton);
            infoPanel.add(namePanel);
            infoPanel.add(idPanel);
            infoPanel.add(pwdPanel);
            infoPanel.add(buttonPanel);
            add(infoPanel);
        }

        // When editing User
        InfoPanel(User u) {
            JLabel nameLabel = new JLabel("Name");
            JLabel idLabel = new JLabel("ID");
            JLabel pwdLabel = new JLabel("Password");
            JTextField nameField = new JTextField(u.getName());
            JLabel idField = new JLabel(String.valueOf(u.getID()));
            JTextField pwdField = new JTextField(String.valueOf(u.getPassword()));
            JButton removeButton = new JButton("Remove");
            JButton editButton = new JButton("Edit");
            JPanel namePanel = new JPanel();
            JPanel idPanel = new JPanel();
            JPanel pwdPanel = new JPanel();
            JPanel buttonPanel = new JPanel();
            JPanel infoPanel = new JPanel();
            namePanel.add(nameLabel);
            namePanel.add(nameField);
            idPanel.add(idLabel);
            idPanel.add(idField);
            pwdPanel.add(pwdLabel);
            pwdPanel.add(pwdField);
            buttonPanel.add(removeButton);
            buttonPanel.add(editButton);
            infoPanel.add(namePanel);
            infoPanel.add(idPanel);
            infoPanel.add(pwdPanel);
            infoPanel.add(buttonPanel);
            add(infoPanel);
        }
    }

    class ListPanel extends JPanel {
        JList<String> list;
        JScrollPane scrollPane;

        // When adding new User
        ListPanel() {
            // TODO : nothing here
        }

        // When editing User
        ListPanel(User u) {

            // String[] pList = new String[uModel.getUserListSize()];
            // for (int i = 0; i < uModel.getUserListSize(); i++) {
            // pList[i] = uModel.getUserList().get(i).getName();
            // }
            // list = new JList<String>(pList);
            // scrollPane = new JScrollPane(list);
            // add(scrollPane);
        }
    }

    public void actionPerformed(ActionEvent e) {
        InfoPanel infoPanel;
        ListPanel listPanel;

        int index = uCombo.getSelectedIndex();
        // When adding new User
        // Select top or nothing
        if (index == 0 || index == -1) {
            infoPanel = new InfoPanel();
            listPanel = new ListPanel();
        }
        // When editing/removing User
        else {
            User u = uModel.getUserList().get(index - 1);
            infoPanel = new InfoPanel(u);
            listPanel = new ListPanel(u);
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