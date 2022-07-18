//this is sample Panel
package GUI;

import GUI.subPanel.FooterPanel;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    JTextField IDField;
    JTextField passwordField;
    JButton rtnBtn;
    JButton signUpBtn;

    class BtnAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == signUpBtn) {
                // input from JtextField
                String ID = IDField.getText();
                String password = passwordField.getText();
                // pop up a window to show the input
                JOptionPane.showMessageDialog(null, "ID: " + ID + "\nPassword: " + password, "Sign In",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            if (e.getSource() == rtnBtn) {
                Main.mainWindow.setFrontScreenAndFocus(ScreenMode.LOGIN);
            }
        }
    }

    // constructor
    SignUpPanel() {
        // this.setLayout(null);
    }

    public Component createMains() {

        // init Button and Field
        BtnAction btnListener = new BtnAction();
        IDField = new JTextField(10);
        passwordField = new JTextField(10);
        rtnBtn = new JButton("return to login");
        signUpBtn = new JButton("Sign Up");

        rtnBtn.addActionListener(btnListener);
        signUpBtn.addActionListener(btnListener);

        // make panel
        JPanel panel = new JPanel();
        JPanel subP1 = new JPanel();
        JPanel subP2 = new JPanel();

        panel.setLayout(new GridLayout(2, 1));

        // subP1 is 2*2
        subP1.setLayout(new GridLayout(2, 2));
        subP1.add(new JLabel(" Username :", SwingConstants.LEFT));
        subP1.add(IDField);
        subP1.add(new JLabel(" Password :", SwingConstants.LEFT));
        subP1.add(passwordField);

        // change the Rgs button's background color and size
        subP2.setLayout(new FlowLayout());
        rtnBtn.setPreferredSize(new Dimension(180, 30));
        subP2.add(rtnBtn, SwingConstants.CENTER);
        signUpBtn.setBackground(Color.ORANGE);
        signUpBtn.setPreferredSize(new Dimension(180, 30));
        subP2.add(signUpBtn, SwingConstants.CENTER);

        panel.add(subP1);
        panel.add(subP2);

        // add upper margin to the panel
        panel.setBorder(BorderFactory.createEmptyBorder(5, 15, 0, 15));

        return panel;

    }

    public Component joinMainsAndFooter() {
        JPanel mainPanel = new JPanel();
        FooterPanel footer = new FooterPanel();
        JLabel headerLabel = new JLabel("Sign Up");
        // header Label settings
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        mainPanel.add(createMains(), BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);
        // color
        mainPanel.setBackground(Color.cyan);
        return mainPanel;
    }

    public void prepareComponents() {
        this.setLayout(null);
        Component main = joinMainsAndFooter();
        final int width = 320;
        final int height = 230;
        final int x = (Main.mainWindow.getWidth() - width) / 2;
        final int y = -30 + (Main.mainWindow.getHeight() - height) / 2;

        main.setBounds(x, y, width, height);

        // resize
        this.add(main, BorderLayout.CENTER);
        // color
        this.setBackground(Color.cyan);

    }
}
