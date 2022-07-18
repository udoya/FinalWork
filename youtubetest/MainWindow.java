import javax.swing.*;
import java.awt.CardLayout;
import java.awt.*;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    ScreenMode screenMode = ScreenMode.TITLE;
    final int WIDTH = 800;
    final int HEIGHT = 600;
    CardLayout layout = new CardLayout();
    TitlePanel titlePanel;
    GamePanel gamePanel;

    MainWindow() {
        this.setTitle("TITLE");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.green);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void preparePanels() {
        titlePanel = new TitlePanel();
        gamePanel = new GamePanel();
        this.add(titlePanel, "title");
        this.add(gamePanel, "game");
        this.pack();

    }

    public void prepareComponents() {
        titlePanel.prepareComponents();
        gamePanel.prepareComponents();
    }

    public void setFrontScreenAndFocus(ScreenMode s) {
        this.screenMode = s;
        switch (this.screenMode) {
            case TITLE:
                layout.show(this.getContentPane(), "title");
                titlePanel.requestFocus();
                break;
            case GAME:
                layout.show(this.getContentPane(), "game");
                gamePanel.requestFocus();
                break;
        }
    }
}
