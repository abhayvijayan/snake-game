import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNIT = (int)(SCREEN_HEIGHT*SCREEN_WIDTH)/UNIT_SIZE;
    int appleX;
    int appleY;
    Random random;
    static final int bodyParts = 0;
    char direction = 'R';
    final int[] x = new int[GAME_UNIT];
    final int[] y = new int[GAME_UNIT];

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        startGame();
    }

    void startGame() {
        generateApple();
        snakeMovement();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (int i = 0; i <= UNIT_SIZE; i++) {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        }

        g.setColor(Color.red);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
    }

    public void generateApple() {
        appleX = (random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = (random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    public void snakeMovement() {

    }
}