package GUI.subPanel;

import Product.*;
import User.*;
import GUI.*;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;

public class StaffUserSubPanel extends JPanel {
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
    CustomerHeaderPanel headerPanel = new CustomerHeaderPanel(0);

    public void changeUserLabelInHeaderPanel(String uID) {
        headerPanel.changeUserLabel(uID);
    }

    // make list to display all product list
    public void setUserList() {
        listModel = new DefaultComboBoxModel<>();
        listModel.addElement("New User");
        for (int i = 0; i < uModel.getUserListSize(); i++) {
            User u = uModel.getUserList().get(i);
            String role;
            if (u.isMaster()) {
                role = "Staff";
            } else {
                role = "Customer";
            }
            listModel.addElement(u.getName() + "@" + role);
        }
    }

    /**
     * ComboPanel of User
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
                User u = uModel.getUserList().get(index - 1);
                if (u.isMaster()) {
                    infoPanel = new InfoPanel(u);
                    listPanel = new ListPanel();
                } else {
                    Customer c = (Customer) u;
                    infoPanel = new InfoPanel(c);
                    listPanel = new ListPanel(c);
                }
            }

            rightPanel.add(infoPanel);
            rightPanel.add(listPanel);
            // repaint the right panel
            rightPanel.revalidate();
            rightPanel.repaint();

        }

        ComboPanel() {
            this.setLayout(new FlowLayout());

            setUserList();

            label = new JLabel("User");
            combo = new JComboBox<String>(listModel);
            combo.addActionListener(this);
            // font
            label.setFont(new Font("Segoe UI", Font.BOLD, 74));
            label.setHorizontalAlignment(JLabel.CENTER);
            combo.setFont(new Font("Segoe UI", Font.BOLD, 30));

            // resize Label and ComboBox
            label.setPreferredSize(new Dimension(600, 100));
            combo.setPreferredSize(new Dimension(600, 80));

            this.add(label);
            this.add(combo);

            this.setPreferredSize(new Dimension(800, 600));
        }
    }

    /**
     * Information of selected User
     * 
     * @member Name(field), ID(field), password(field)
     * @button Add, Remove, Edit
     */
    class InfoPanel extends JPanel {
        JTextField nameField;
        JTextField idField;
        JTextField pwdField;
        JCheckBox staffCheckBox;

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

                // Get ID
                String ID = idField.getText();
                // care about empty field
                if (idField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "ID is empty");
                    return;
                }
                // care about duplicate ID
                if (uModel.getUser(ID) != null) {
                    JOptionPane.showMessageDialog(null, "Same ID already exists");
                    return;
                }

                // Get password
                String pwd = pwdField.getText();
                // care about empty field
                if (pwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "Password is empty");
                    return;
                }

                // Add new Staff / Customer
                try {
                    if (staffCheckBox.isSelected()) {
                        uModel.addUser(new Staff(name, ID, pwd));
                    } else {
                        uModel.addUser(new Customer(name, ID, pwd));
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                    return;
                }
                JOptionPane.showMessageDialog(null, "Added new user successfully");

                // renew combo box
                setUserList();
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
                User u = uModel.getUserList().get(index - 1);

                // remove user
                try {
                    uModel.removeUser(u);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                    return;
                }
                JOptionPane.showMessageDialog(null, "Removed user successfully");

                // renew combo box
                setUserList();
                combo.setModel(listModel);
                leftPanel.revalidate();
                leftPanel.repaint();
            }
        }

        class EditButtonAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = combo.getSelectedIndex();
                User u = uModel.getUserList().get(index - 1);

                // get new name
                String name = nameField.getText();
                // if name is empty, do not change
                if (name.equals("")) {
                    name = u.getName();
                }

                // get new ID
                String ID = idField.getText();
                String oldID = u.getID();
                if (uModel.getUser(ID) != null && !ID.equals(oldID)) {
                    JOptionPane.showMessageDialog(null, "Same ID already exists");
                    return;
                }
                // if ID is empty, do not change
                if (ID.equals("")) {
                    ID = u.getID();
                }

                // get new password
                String pwd = pwdField.getText();
                // if password is empty, do not change
                if (pwd.equals("")) {
                    pwd = u.getPassword();
                }

                // update user
                uModel.updateUser(u, name, ID, pwd);

                // refresh comboBox
                setUserList();
                combo.setModel(listModel);
                rightPanel.revalidate();
                rightPanel.repaint();
                leftPanel.revalidate();
                leftPanel.repaint();
            }
        }

        // When adding new User
        // TODO: JCheckBox seikei please
        InfoPanel() {
            this.setLayout(new FlowLayout());
            this.setPreferredSize(new Dimension(Main.WIDTH / 2, Main.HEIGHT / 2));
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nameLabel = new JLabel("Name");
            JLabel idLabel = new JLabel("ID");
            JLabel pwdLabel = new JLabel("Password");

            // JLabel Settings

            nameField = new JTextField();
            // resize field
            nameField.setColumns(10);

            idField = new JTextField();
            idField.setColumns(10);

            // resize field
            pwdField = new JTextField();
            pwdField.setColumns(10);

            staffCheckBox = new JCheckBox("Staff");
            JButton addButton = new JButton("Add");
            JPanel namePanel = new JPanel();
            JPanel idPanel = new JPanel();
            JPanel pwdPanel = new JPanel();
            JPanel buttonPanel = new JPanel();

            // btn action listener
            addButton.addActionListener(new AddButtonAction());

            // resize components
            nameField.setColumns(36);
            nameField.setPreferredSize(new Dimension(550, 40));
            pwdField.setColumns(10);
            pwdField.setPreferredSize(new Dimension(300, 40));

            idField.setPreferredSize(new Dimension(300, 40));
            idLabel.setPreferredSize(new Dimension(70, 100));
            idLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            // password label margin
            pwdLabel.setPreferredSize(new Dimension(150, 100));
            pwdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            // fonts
            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            idLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            pwdLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            nameField.setFont(new Font("Segoe UI", Font.BOLD, 20));
            idField.setFont(new Font("Segoe UI", Font.BOLD, 20));

            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.setPreferredSize(new Dimension(Main.WIDTH / 2, Main.HEIGHT / 8));
            staffCheckBox.setPreferredSize(new Dimension(100, 40));
            staffCheckBox.setFont(new Font("Segoe UI", Font.BOLD, 20));
            addButton.setPreferredSize(new Dimension(100, 40));
            addButton.setFont(new Font("Segoe UI", Font.BOLD, 16));

            // btn color
            addButton.setBackground(Color.GREEN);

            namePanel.add(nameLabel);
            namePanel.add(nameField);
            idPanel.add(idLabel);
            idPanel.add(idField);
            pwdPanel.add(pwdLabel);
            pwdPanel.add(pwdField);
            buttonPanel.add(staffCheckBox);
            buttonPanel.add(addButton);
            add(namePanel);
            add(idPanel);
            add(pwdPanel);
            add(buttonPanel);
        }

        // When editing product
        InfoPanel(User u) {
            this.setLayout(new FlowLayout());
            this.setPreferredSize(new Dimension(Main.WIDTH / 2, Main.HEIGHT / 2));
            // margin
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nameLabel = new JLabel("Name");
            JLabel idLabel = new JLabel("ID");
            JLabel pwdLabel = new JLabel("Password");

            nameField = new JTextField(u.getName());
            idField = new JTextField(u.getID());
            pwdField = new JTextField(u.getPassword());

            JButton removeButton = new JButton("Remove");
            JButton editButton = new JButton("Edit");

            removeButton.addActionListener(new RemoveButtonAction());
            editButton.addActionListener(new EditButtonAction());

            // resize components
            nameField.setColumns(36);
            nameField.setPreferredSize(new Dimension(550, 40));
            pwdField.setColumns(10);
            pwdField.setPreferredSize(new Dimension(300, 40));

            idField.setPreferredSize(new Dimension(300, 40));
            idLabel.setPreferredSize(new Dimension(70, 100));
            idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            removeButton.setPreferredSize(new Dimension(250, 50));
            editButton.setPreferredSize(new Dimension(200, 50));
            removeButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
            editButton.setFont(new Font("Segoe UI", Font.BOLD, 18));

            // password label margin
            pwdLabel.setPreferredSize(new Dimension(150, 100));
            pwdLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            // fonts
            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            idLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            pwdLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            nameField.setFont(new Font("Segoe UI", Font.BOLD, 20));
            idField.setFont(new Font("Segoe UI", Font.BOLD, 20));
            pwdField.setFont(new Font("Segoe UI", Font.BOLD, 20));

            // btn color
            removeButton.setBackground(Color.RED);
            editButton.setBackground(Color.GREEN);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(removeButton);
            buttonPanel.add(editButton);
            buttonPanel.setPreferredSize(new Dimension(Main.WIDTH / 2, Main.HEIGHT / 7));

            this.add(nameLabel);
            this.add(nameField);
            this.add(idLabel);
            this.add(idField);
            this.add(pwdLabel);
            this.add(pwdField);

            // this.add(removeButton);
            // this.add(editButton);
            // this.add(namePanel);
            // this.add(idPanel);
            // this.add(pwdPanel);
            this.add(buttonPanel);

            this.setLayout(new FlowLayout(FlowLayout.LEFT));
        }
    }

    class ListPanel extends JPanel {
        JList<String> list;
        JScrollPane scrollPane;

        // When adding new User
        ListPanel() {
            // TODO : nothing here
            this.setPreferredSize(new Dimension(Main.WIDTH / 4, Main.HEIGHT / 4));
        }

        // When looking Customer
        ListPanel(Customer c) {
            list = new JList<String>(c.getBorrowingListString());
            scrollPane = new JScrollPane(list);
            scrollPane.createVerticalScrollBar();
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            // change fonts
            list.setFont(new Font("Segoe UI", Font.BOLD, 24));
            this.setPreferredSize(new Dimension(Main.WIDTH / 4, Main.HEIGHT / 4));
            this.setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
        }
    }

    public void prepareComponents() {
        ComboPanel comboPanel = new ComboPanel();
        add(comboPanel);
    }

    public StaffUserSubPanel() {
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