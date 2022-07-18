package GUI;

import GUI.Controller.*;
import GUI.subPanel.HeaderPanel;

import GUI.subPanel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel1: Show My Profile
 * Panel2: List of products and List of borrowing: borrow, return
 */

public class CustomerPanel extends JPanel {

    JTextField IDField;
    JTextField passwordField;
    JButton signInBtn;
    JButton signUpBtn;
    JButton quitButton;

    /* ActionListener */
    class BtnAction implements ActionListener {
        CustomerController CC = new CustomerController();

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
        }

        class ProductListPanel {
            private JLabel label;
            private JLabel searchLabel;
            private JTextField searchField;
            private DefaultListModel<String> listModel;
            private JList<String> list;

            public Component createComponents() {
                label = new JLabel("Product List");
                searchLabel = new JLabel("Search");
                searchField = new JTextField("");

                listModel = new DefaultListModel<String>();

                list = new JList<String>(listModel);
                list.setVisibleRowCount(10);
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                JScrollPane scrollPanel = new JScrollPane(list);
                scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            }
        }
    }

    public void prepareComponents() {
        this.setLayout(new BorderLayout());
        HeaderPanel headerPanel = new HeaderPanel();
        // resize
        headerPanel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT / 12));
        FooterPanel footerPanel = new FooterPanel();

        this.add(headerPanel, BorderLayout.NORTH);

        this.add(footerPanel, BorderLayout.SOUTH);

        // color
        this.setBackground(Color.LIGHT_GRAY);

    }
}