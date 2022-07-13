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

    public Component createHeaderComponents() {

        // ButtonAction buttonListener = new ButtonAction();
        // homeBtn.addActionListener(buttonListener);
        // userBtn.addActionListener(buttonListener);

        // make main panel
        JPanel mainPanel = new JPanel();

        // make header panel (1*6)
        // header panel has a 6 buttons
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 6));
        headerPanel.add(homeBtn);
        headerPanel.add(userBtn);
        headerPanel.add(textField);
        headerPanel.add(new JButton("search"));
        headerPanel.add(new JButton("cart"));
        headerPanel.add(new JButton("logout"));

        // put into main panel
        mainPanel.add(headerPanel);

        return mainPanel;

    }

    public Component createComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(createHeaderComponents(), BorderLayout.NORTH);
        mainPanel.add(new JLabel("Main Screen"), BorderLayout.CENTER);
        return mainPanel;
    }

    public static void main(String[] args) {
        MainScreen app = new MainScreen();
        Component header = app.createComponents();

        JFrame frame = new JFrame("Prob103");

        frame.getContentPane().add(header, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* to change the size of the frame, use setSize() */
        // frame.setSize(300, 300);
        frame.pack();
        frame.setVisible(true);
    }
}
