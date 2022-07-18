import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    JLabel gameLabel;
    JButton endBtn;

    // make endBtn action listener
    class ButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == endBtn) {
                // change to title panel
                Main.mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE);
            }
        }
    }

    public void TitlePanel() {
        this.setLayout(null);
        this.setBackground(Color.yellow);
    }

    public void prepareComponents() {
        gameLabel = new JLabel("game");
        gameLabel.setBounds(100, 200, 100, 30);
        this.add(gameLabel);

        // init endButton
        endBtn = new JButton("end");
        endBtn.setBounds(440, 440, 100, 50);
        this.add(endBtn);
        endBtn.addActionListener(new ButtonAction());
    }
}
