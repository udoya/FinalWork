package GUI.subPanel;

import GUI.Controller.*;
import GUI.subPanel.HeaderPanel;

import GUI.subPanel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductListPanel {
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