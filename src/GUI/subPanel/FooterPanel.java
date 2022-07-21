package GUI.subPanel;

import javax.swing.*;

import GUI.Main;

import java.awt.*;
import java.awt.event.*;

public class FooterPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    JLabel footerLabel = new JLabel("Copyright@2020");
    JLabel footerLabel2 = new JLabel("All rights reserved.");

    JPanel sub1Footer = new JPanel();
    JPanel sub2Footer = new JPanel();

    JButton quitButton = new JButton("Quit");

    public FooterPanel() {

        // this Layout is 1*2 grid layout
        this.setLayout(new GridLayout(0, 2));

        sub1Footer.setLayout(new FlowLayout(FlowLayout.LEFT));

        sub2Footer.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel sub1A = new JPanel();
        sub1A.setLayout(new GridLayout(2, 1));
        sub1A.add(footerLabel);

        // add bottom margin to footerLabel2
        footerLabel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        sub1A.add(footerLabel2);
        sub1Footer.add(sub1A);

        // when click quit button, close the window
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // make option pane for confirm quit
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to quit?", "Quit",
                        JOptionPane.YES_NO_OPTION);
                // if yes then sign out
                if (confirm == JOptionPane.YES_OPTION) {
                    Main.mainWindow.dispose();
                }
            }
        });

        // size
        quitButton.setPreferredSize(new Dimension(70, 30));
        sub2Footer.add(quitButton);

        this.add(sub1Footer);
        this.add(sub2Footer);

    }

}