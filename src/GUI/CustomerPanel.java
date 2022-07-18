package GUI;

import GUI.Controller.*;
import GUI.subPanel.HeaderPanel;

import GUI.subPanel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    }

    public void prepareComponents() {
        this.setLayout(new BorderLayout());
        HeaderPanel headerPanel = new HeaderPanel();
        FooterPanel footerPanel = new FooterPanel();

        // final int width = 800;
        // final int height = 600;
        // final int x = (Main.mainWindow.getWidth() - width) / 2;
        // final int y = (Main.mainWindow.getHeight() - height) / 2;
        // setBounds(x, y, width, height);

        // resize
        this.add(headerPanel, BorderLayout.NORTH);

        this.add(new JLabel("2"), BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);

        // color
        this.setBackground(Color.DARK_GRAY);

    }
}