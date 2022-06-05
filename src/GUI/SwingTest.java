package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SwingTest extends JFrame {

    public static void main(String[] args) {
        new SwingTest();
    }

    public SwingTest() {

        // window Size
        setBounds(200, 100, 400, 400);
        // window Title
        setTitle("JSample");
        // setting Layout
        setLayout(new FlowLayout());
        // setting window close
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setLayout(null);

        JButton b = new JButton("Tap here");
        add(b);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hi!");
            }
        });

        setVisible(true);

    }

}
