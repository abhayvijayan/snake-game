import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNIT = (int)(SCREEN_HEIGHT*SCREEN_WIDTH)/UNIT_SIZE;
    static final int DELAY = 80;
    int appleX;
    int appleY;
    boolean running = false;
    Random random;
    Timer timer;
    JButton restartButton;
    int bodyParts = 3;
    int score = 0;
    char direction = 'R';
    final int[] x = new int[GAME_UNIT];
    final int[] y = new int[GAME_UNIT];

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(this);

        restartButton = new JButton("Restart Game");
        restartButton.setVisible(false);
        restartButton.addActionListener(e -> restartGame());
        this.add(restartButton);

        initializeGame();

        timer = new Timer(DELAY, e -> gameLoop());
        running = true;
        timer.start();
    }

    // GAME INIT FUNCTION
    void initializeGame() {
        generateApple();

        for (int i = 0; i < bodyParts; i++) {
            x[i] = (bodyParts - i - 1) * UNIT_SIZE;
            y[i] = 0;
        }
    }

    // MAIN GAME LOOP
    void gameLoop() {
        snakeMovement();
        checkCollision();
        repaint();
    }

    // GAME OVER
    void gameOver() {
        timer.stop();
        running = false;

        restartButton.setVisible(true);
        repaint();
    }

    // RESTART GAME
    void restartGame() {
        bodyParts = 3;
        direction = 'R';
        score = 0;

        initializeGame();

        running = true;
        restartButton.setVisible(false);

        timer.start();

        requestFocusInWindow();
        repaint();
    }

    // COMPONENT PAINTER FOR GRAPHICS
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // DRAW FUNCTION FOR GRAPHICS ELEMENTS
    public void draw(Graphics g) {
        if (running) {
            // GRID
//            for (int i = 0; i <= UNIT_SIZE; i++) {
//                g.drawLine(i * UNIT_SIZE, 0,
//                        i * UNIT_SIZE, SCREEN_HEIGHT);
//
//                g.drawLine(0, i * UNIT_SIZE,
//                        SCREEN_WIDTH, i * UNIT_SIZE);
//            }

            // APPLE
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // SNAKE
            g.setColor(Color.green);
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(9, 219, 135));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            // DISPLAY SCORE
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("Score: " + score, 10, 25);
        } else {
            // GAME OVER SCREEN
            this.setBackground(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.setColor(Color.BLUE);
            g.drawString("GAME OVER!", SCREEN_WIDTH/2 - 60, SCREEN_HEIGHT/2);
        }
    }

    // APPLE COORDINATES GENERATION
    public void generateApple() {
        appleX = (random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = (random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    // SNAKE MOVEMENT
    public void snakeMovement() {

        // MOVE BODY PARTS
        for (int i = bodyParts - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // MOVE HEAD
        switch (direction) {
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;

            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;

            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;

            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
        }
    }

    // COLLISION CHECKER
    void checkCollision() {

        // WALL COLLISION
        if (x[0] < 0 ||
                x[0] >= SCREEN_WIDTH ||
                y[0] < 0 ||
                y[0] >= SCREEN_HEIGHT) {

            gameOver();
        }

        // SELF COLLISION
        for (int i = 1; i < bodyParts; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {
                gameOver();
            }
        }

        // FOOD COLLISION
        if (x[0] == appleX && y[0] == appleY) {

            x[bodyParts] = x[bodyParts - 1];
            y[bodyParts] = y[bodyParts - 1];

            bodyParts++;
            score++;

            generateApple();
        }
    }

    // KEYBOARD INPUT HANDLER
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
    @Override
    public void keyTyped(KeyEvent e) {
        // DO NOTHING
    }
}