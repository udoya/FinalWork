package GUI;

import GUI.Controller.*;
import GUI.subPanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    JTextField IDField;
    JTextField passwordField;
    JButton signInBtn;
    JButton signUpBtn;
    JButton quitButton;
    public static String ID;

    class BtnAction implements ActionListener {
        LoginController LC = new LoginController();

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == signInBtn) {
                // input from JtextField
                ID = IDField.getText();
                String password = passwordField.getText();

                // Check Input
                if (ID.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please input ID and Password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // try to sign in
                    int login = LC.login(ID, password);
                    String name;
                    switch (login) {
                        case 0:
                            name = LC.getName(ID);
                            JOptionPane.showMessageDialog(null, "Login Success\nStaff: " + name, "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            Main.uID = ID;

                            System.out.println("Login Success: STAFF1");
                            Main.mainWindow.setFrontScreenAndFocus(ScreenMode.STAFF);
                            System.out.println("Login Success: STAFF");
                            break;
                        case 1:
                            name = LC.getName(ID);
                            JOptionPane.showMessageDialog(null, "Login Success\nWelcome " + name, "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            Main.uID = ID;
                            Main.mainWindow.setFrontScreenAndFocus(ScreenMode.CUSTOMER);
                            System.out.println("Login Success: CUSTOMER");
                            break;
                        case 2:
                        JOptionPane.showMessageDialog(null, "Too many wrong attempts.\nPlease try again later.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                        case 3:
                        JOptionPane.showMessageDialog(null, "Invalid ID or Password", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }

            }
            if (e.getSource() == signUpBtn) {
                // TODO: move home screen
                Main.mainWindow.setFrontScreenAndFocus(ScreenMode.SIGNUP);
            }
        }
    }

    // constructor
    LoginPanel() {
        // this.setLayout(null);
    }

    public Component createMains() {

        // init Button and Field
        BtnAction btnListener = new BtnAction();
        IDField = new JTextField(10);
        passwordField = new JTextField(10);
        signInBtn = new JButton("Sign In");
        signUpBtn = new JButton("Sign Up");

        signInBtn.addActionListener(btnListener);
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
        signInBtn.setBackground(Color.ORANGE);
        signInBtn.setPreferredSize(new Dimension(100, 30));
        subP2.add(signInBtn, SwingConstants.CENTER);
        signUpBtn.setBackground(Color.ORANGE);
        signUpBtn.setPreferredSize(new Dimension(100, 30));
        subP2.add(signUpBtn, SwingConstants.CENTER);

        // add margin to subP2
        subP2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panel.add(subP1);
        panel.add(subP2);

        // add upper margin to the panel
        panel.setBorder(BorderFactory.createEmptyBorder(5, 15, 0, 15));

        return panel;

    }

    public Component joinMainsAndFooter() {
        JPanel mainPanel = new JPanel();
        FooterPanel footer = new FooterPanel();
        JLabel headerLabel = new JLabel("Product System Login");
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
        final int width = 300;
        final int height = 200;
        final int x = -5 + (Main.mainWindow.getWidth() - width) / 2;
        final int y = -30 + (Main.mainWindow.getHeight() - height) / 2;

        main.setBounds(x, y, width, height);

        // resize
        this.add(main, BorderLayout.CENTER);
        // color
        this.setBackground(Color.cyan);

    }

}
