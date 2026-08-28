import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNIT = (int)(SCREEN_HEIGHT*SCREEN_WIDTH)/UNIT_SIZE;
    static final int DELAY = 50;
    int appleX;
    int appleY;
    Random random;
    Timer timer;
    static final int bodyParts = 6;
    char direction = 'R';
    final int[] x = new int[GAME_UNIT];
    final int[] y = new int[GAME_UNIT];

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(this);
        timer = new Timer(DELAY, null);
        gameLoop();
    }

    public void gameLoop() {
        timer.start();
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
        switch (direction) {
            case 'R' : {

            }
        }
    }

    void checkCollision() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // DO NOTHING
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 'L';
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 'R';
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = 'U';
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 'D';
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // DO NOTHING
    }
}