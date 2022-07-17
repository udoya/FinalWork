package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login {

    private JTextField IDField = new JTextField(10);
    private JTextField passwordField = new JTextField(10);

    private JButton RgsBtn = new JButton("Register!");

    class ButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == RgsBtn) {
                // if input cannot be change to int type, do nothing

                // input from JtextField
                String ID = IDField.getText();
                String password = passwordField.getText();
                // pop up a window to show the input
                JOptionPane.showMessageDialog(null, "ID: " + ID + "\nPassword: " + password);
            }

            // 下みたいにするにはどうすれば... pListはどうアクセスすれば？
            /*
             * 
             * ProductSystem pList = ProductSystem.getProductList();
             * // List表示したい
             * for (int i = 0; i < pList.getProductList().size(); i++) {
             * System.out.println(pList.getProductList().get(i).getName());
             * }
             * 
             * // 商品を追加する
             * pList.addProduct(new Product());
             * 
             */
        }
    }

    public Component createComponents() {

        // init addButton add clear
        ButtonAction buttonListener = new ButtonAction();
        RgsBtn.addActionListener(buttonListener);

        // make panel
        JPanel panel = new JPanel();
        JPanel subP1 = new JPanel();
        JPanel subP2 = new JPanel();

        panel.setLayout(new GridLayout(2, 1));

        // subP1 is 2*2
        subP1.setLayout(new GridLayout(2, 2));
        subP1.add(new JLabel("Username:", SwingConstants.LEFT));
        subP1.add(IDField);
        subP1.add(new JLabel("Password:", SwingConstants.LEFT));
        subP1.add(passwordField);

        // change the Rgs button's background color and size
        subP2.setLayout(new FlowLayout());
        RgsBtn.setBackground(Color.ORANGE);
        RgsBtn.setPreferredSize(new Dimension(100, 30));
        subP2.add(RgsBtn, SwingConstants.CENTER);

        panel.add(subP1);
        panel.add(subP2);

        return panel;

    }

    public Component createFooter() {
        // footer
        JPanel mainFooter = new JPanel();
        mainFooter.setLayout(new GridLayout(0, 2, 0, 0));

        // sub footer
        JPanel sub1Footer = new JPanel();
        sub1Footer.setLayout(new GridLayout(2, 0, 0, 0));

        JPanel sub2Footer = new JPanel();
        sub2Footer.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel footerLabel = new JLabel("Copyright@2020");
        JLabel footerLabel2 = new JLabel("All rights reserved.");
        sub1Footer.add(footerLabel);
        sub1Footer.add(footerLabel2);

        JButton footerButton = new JButton("OK");
        // size
        footerButton.setPreferredSize(new Dimension(100, 40));
        sub2Footer.add(footerButton);

        mainFooter.add(sub1Footer);
        mainFooter.add(sub2Footer);

        return mainFooter;
    }

    public void init() {
        JFrame frame = new JFrame("Login");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLocationRelativeTo(null);
        // frame.setLayout(new BorderLayout());
        // frame.add(createComponents(), BorderLayout.CENTER);
        // frame.add(createFooter(), BorderLayout.SOUTH);
        // frame.setResizable(false);

        Login app = new Login();
        Component contents = app.createComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* to change the size of the frame, use setSize() */
        // frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Login app = new Login();
        Component contents = app.createComponents();

        JFrame frame = new JFrame("Prob103");

        frame.getContentPane().add(contents, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* to change the size of the frame, use setSize() */
        // frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);
    }
}
