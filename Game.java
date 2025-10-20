import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Game extends JPanel implements ActionListener {
    private Timer timer;
    private Runnable onGameOver;
    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks = new ArrayList<>();
    private List<PowerUp> powerUps = new ArrayList<>();
    private List<PowerUp> activeBallPowerUps = new ArrayList<>();

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Game() {
        this(null);
    }

    public Game(Runnable onGameOver) {
        this.onGameOver = onGameOver;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);

        initGameObjects();

        timer = new Timer(16, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
                    paddle.setDx(-paddle.getSpeed());
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
                    paddle.setDx(paddle.getSpeed());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT
                        || key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
                    paddle.setDx(0);
                }
            }
        });
    }

    private void initGameObjects() {
        paddle = new Paddle(WIDTH / 2 - 80, HEIGHT - 50, 600);

        double baseSpeed = 150 + GameSettings.getSpeed() * 35;
        ball = new Ball(
                paddle.getX() + paddle.getWidth() / 2 - 10,
                paddle.getY() - 20,
                10,
                baseSpeed,
                1,
                -1
        );

        bricks.clear();
        powerUps.clear();

        int rows = 4;
        int cols = 5;
        double brickW = WIDTH / (double) cols;
        double brickH = 20;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                bricks.add(new Brick(c * brickW, 50 + r * (brickH + 5), brickW - 4, brickH, 1, "normal"));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double dt = 0.016; // fixed timestep

        paddle.move(dt);
        paddle.update(dt);
        ball.update(dt);

        // Ball - Paddle collision
        if (ball.checkCollision(paddle)) {
            ball.bounceOff(paddle);
        }

        // Ball - Brick collisions
        for (Brick b : new ArrayList<>(bricks)) {
            if (!b.isDestroyed() && ball.checkCollision(b)) {
                b.setDestroyed(true);
                ball.bounceOff(b);

                // 10% drop chance for power-up
                if (Math.random() < 0.1) {
                    PowerUp powerUp = null;
                    double brickX = b.getX() + b.getWidth() / 2 - 10;
                    double brickY = b.getY() + b.getHeight() / 2 - 10;

                    int rand = (int) (Math.random() * 7);
                    switch (rand) {
                        case 0: powerUp = new ExpandPaddlePowerUp(brickX, brickY); break;
                        case 1: powerUp = new ShrinkPaddlePowerUp(brickX, brickY); break;
                        case 2: powerUp = new SpeedUpPaddlePowerUp(brickX, brickY); break;
                        case 3: powerUp = new SlowPaddlePowerUp(brickX, brickY); break;
                        case 4: powerUp = new FastBallPowerUp(brickX, brickY); break;
                        case 5: powerUp = new SlowBallPowerUp(brickX, brickY); break;
                        default: break;
                    }

                    if (powerUp != null) powerUps.add(powerUp);
                }
            }
        }

        // Update PowerUps
        for (PowerUp p : new ArrayList<>(powerUps)) {
            p.update(dt);

            if (p.checkCollision(paddle)) {
                p.applyEffect(paddle, ball);
                powerUps.remove(p);
                continue;
            }

            if (p.isOutOfScreen()) {
                powerUps.remove(p);
            }
        }

        // Check Game Over
        if (ball.isOutOfScreen()) {
            timer.stop();

            int choice = JOptionPane.showOptionDialog(this,
                    "Bạn đã thua!",
                    "Thua cuộc",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Thử lại", "Quay về Menu"},
                    "Thử lại");

            if (choice == JOptionPane.YES_OPTION) {
                initGameObjects();
                timer.start();
            } else if (onGameOver != null) {
                SwingUtilities.invokeLater(onGameOver);
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Renderer rd = new Renderer(g);

        rd.drawImage("Background.png", 0, 0, getWidth(), getHeight());

        paddle.render(rd);
        ball.render(rd);

        for (Brick b : bricks) b.render(rd);
        for (PowerUp p : powerUps) p.render(rd);
    }

}