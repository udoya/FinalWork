//this is sample Panel
package GUI;

import GUI.subPanel.FooterPanel;
import GUI.*;
import User.*;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    JTextField nameField;
    JTextField idField;
    JTextField pwdField;
    JButton rtnBtn;
    JButton signUpBtn;

    UserModel uModel = Main.uModel;

    class BtnAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == signUpBtn) {
                // Get name
                // String name = nameField.getText();
                // TODO: Uncomment when nameField is ready

                /* Test */
                String name = "Anonymous";
                /* End of test */

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
                    JOptionPane.showMessageDialog(null, "Sorry, same ID is already in the system");
                    return;
                }

                // Get password
                String pwd = pwdField.getText();
                // care about empty field
                if (pwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "Password is empty");
                    return;
                }

                try {
                    uModel.addUser(new Customer(name, ID, pwd));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Welcome " + name + "!\nYou have successfully signed up.");
                Main.mainWindow.setFrontScreenAndFocus(ScreenMode.LOGIN);
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

    // TODO: add Username field
    public Component createMains() {

        // init Button and Field
        BtnAction btnListener = new BtnAction();
        idField = new JTextField(10);
        pwdField = new JTextField(10);
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
        subP1.add(new JLabel(" UserID :", SwingConstants.LEFT));
        subP1.add(idField);
        subP1.add(new JLabel(" Password :", SwingConstants.LEFT));
        subP1.add(pwdField);

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
