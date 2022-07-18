import java.awt.Color;

import javax.swing.*;
import java.awt.event.*;

public class TitlePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    JLabel titleLabel;
    JButton startButton;

    // action listener for start button
    class ButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                // change to game panel
                Main.mainWindow.setFrontScreenAndFocus(ScreenMode.GAME);
            }
        }
    }

    TitlePanel() {
        this.setLayout(null);
        this.setBackground(Color.cyan);

    }

    public void prepareComponents() {
        titleLabel = new JLabel("Title");
        titleLabel.setBounds(100, 0, 100, 30);
        this.add(titleLabel);

        // init startButton
        startButton = new JButton("start");
        startButton.setBounds(100, 200, 100, 30);
        this.add(startButton);
        startButton.addActionListener(new ButtonAction());
    }
}
