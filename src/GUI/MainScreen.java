package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen {

    private JTextField textField = new JTextField(10);
    private JButton homeBtn = new JButton("home");
    private JButton userBtn = new JButton("user");

    // class ButtonAction implements ActionListener {
    // public void actionPerformed(ActionEvent e) {
    // if (e.getSource() == homeBtn) {
    // }

    // if (e.getSource() == userBtn) {
    // }
    // }
    // }

    public Component createHeader() {

        // ButtonAction buttonListener = new ButtonAction();
        // homeBtn.addActionListener(buttonListener);
        // userBtn.addActionListener(buttonListener);

        JPanel header = new JPanel();
        // header 1*6
        header.setLayout(new GridLayout(0, 6, 0, 0));
        header.add(new JButton("Main"));
        header.add(new JButton("Information"));
        header.add(new JButton("Search"));
        header.add(new JButton("Add"));
        header.add(new JButton("Edit"));
        header.add(new JButton("Delete"));

        return header;
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

    public Component createComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new JLabel("Main Screen"), BorderLayout.CENTER);
        return mainPanel;
    }

    public static void main(String[] args) {
        MainScreen app = new MainScreen();
        Component contents = app.createComponents();

        JFrame frame = new JFrame("Prob103");

        Component header = app.createHeader();
        Component footer = app.createFooter();

        header.setPreferredSize(new Dimension(100, 100));
        frame.getContentPane().add(header, BorderLayout.NORTH);
        frame.getContentPane().add(contents, BorderLayout.CENTER);
        frame.getContentPane().add(footer, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* to change the size of the frame, use setSize() */
        frame.setSize(800, 600);
        // frame.pack();
        frame.setVisible(true);
    }
}