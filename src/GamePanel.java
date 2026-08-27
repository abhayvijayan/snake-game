import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;

    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (int i = 0; i <= UNIT_SIZE; i++) {
            g.drawLine(0, (int)(SCREEN_HEIGHT/UNIT_SIZE)*i, SCREEN_HEIGHT, (int)(SCREEN_HEIGHT/UNIT_SIZE)*i);
            g.drawLine((int)(SCREEN_WIDTH/UNIT_SIZE)*i, 0, (int)(SCREEN_WIDTH/UNIT_SIZE)*i, SCREEN_WIDTH);
        }
    }
}