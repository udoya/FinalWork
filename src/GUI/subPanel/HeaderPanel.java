package GUI.subPanel;

import GUI.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HeaderPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    JButton mainBtn;
    JButton infoBtn;
    JButton signOutBtn;

    class BtnAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == mainBtn) {
            }
            if (e.getSource() == infoBtn) {
            }
            if (e.getSource() == signOutBtn) {
                // make option pane for confirm sign out
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to sign out?", "Sign Out",
                        JOptionPane.YES_NO_OPTION);
                // if yes then sign out
                if (confirm == JOptionPane.YES_OPTION) {
                    Main.mainWindow.setFrontScreenAndFocus(ScreenMode.LOGIN);
                }
            }
        }
    }

    public HeaderPanel() {

        // header 1*6
        this.setLayout(new GridLayout(0, 6, 0, 0));

        // init btn
        mainBtn = new JButton("Main");
        infoBtn = new JButton("Info");
        signOutBtn = new JButton("Return");
        signOutBtn.addActionListener(new BtnAction());

        this.add(mainBtn);
        this.add(infoBtn);
        this.add(new JButton("Search"));
        this.add(new JButton("Add"));
        this.add(new JButton("Edit"));
        this.add(signOutBtn);

    }

}