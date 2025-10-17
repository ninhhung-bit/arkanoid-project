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
    // active ball-affecting powerups (e.g., fastball/slowball) being timed
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
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) paddle.setDx(-paddle.getSpeed());
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) paddle.setDx(paddle.getSpeed());
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

    // Original map
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

        // ball-paddle collision
        if (ball.checkCollision(paddle)) {
            ball.bounceOff(paddle);
        }

        // ball-brick collisions
        for (Brick b : new ArrayList<>(bricks)) {
            if (!b.isDestroyed() && ball.checkCollision(b)) {
                b.setDestroyed(true);
                ball.bounceOff(b);

                // 30% tỉ lệ rơi power-up
                if (Math.random() < 0.3) {
                    PowerUp powerUp = null;
                    double brickX = b.getX() + b.getWidth() / 2 - 15;
                    double brickY = b.getY() + b.getHeight() / 2 - 15;

                    int rand = (int)(Math.random() * 7); // 7 loại power-up
                    switch (rand) {
                        case 0:
                            powerUp = new ExpandPaddlePowerUp(brickX, brickY);
                            break;
                        case 1:
                            powerUp = new ShrinkPaddlePowerUp(brickX, brickY);
                            break;
                        case 2:
                            powerUp = new SpeedUpPaddlePowerUp(brickX, brickY);
                            break;
                        case 3:
                            powerUp = new SlowPaddlePowerUp(brickX, brickY);
                            break;
                        case 4:
                            powerUp = new FastBallPowerUp(brickX, brickY);
                            break;
                        case 5:
                            powerUp = new SlowBallPowerUp(brickX, brickY);
                            break;
//                        case 6:
//                            powerUp = new MultiBallPowerUp(brickX, brickY);
//                            break;
                        default:
                            powerUp = null; // dự phòng
                            break;
                    }

                    if (powerUp != null) {
                        powerUps.add(powerUp);
                    }
                }

            }
        }

        // update power-ups
        for (PowerUp p : new ArrayList<>(powerUps)) {
            p.update(dt);

            if (p.checkCollision(paddle)) {
                // when collected: if same type active -> refresh timer, if different -> remove previous and apply new
                String type = p.getType().toLowerCase();
                if (paddle.activePowerUpType != null && paddle.activePowerUpType.equals(type)) {
                    // refresh
                    paddle.activePowerUpRemaining = p.getDuration();
                } else {
                    // applying will clear previous if needed inside paddle.applyPowerUp
                    p.applyEffect(paddle, ball);
                    // if power-up affects ball (fastball/slowball) we need to schedule removal after duration
                    if (type.equals("fastball") || type.equals("slowball")) {
                        // attach a simple timer object: reuse PowerUp instance to remove effect after duration
                        // store the remaining time in the PowerUp instance
                        p.setDuration(p.getDuration());
                        activeBallPowerUps.add(p);
                    }
                }

                powerUps.remove(p);
                continue;
            }

            if (p.isOutOfScreen()) {
                powerUps.remove(p);
            }
        }

        // update active ball powerups (timers)
        for (PowerUp p : new ArrayList<>(activeBallPowerUps)) {
            double newDur = p.getDuration() - dt;
            p.setDuration(newDur);
            if (newDur <= 0) {
                p.removeEffect(paddle, ball);
                activeBallPowerUps.remove(p);
            }
        }

        // check if ball fell out of screen -> game over
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

        rd.setColor(Color.CYAN);
        paddle.render(rd);

        rd.setColor(Color.WHITE);
        ball.render(rd);

        for (Brick b : bricks) b.render(rd);
        for (PowerUp p : powerUps) p.render(rd);
    }
}
