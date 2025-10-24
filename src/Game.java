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
    private int currentLevel = 1;
    private int totalBricks = 20;
    private boolean levelCompleted = false;
    private boolean paused = false;             // Trạng thái tạm dừng
    private boolean showingPauseMenu = false;   // Đang hiển thị menu tạm dừng

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
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
                    paddle.setDx(-paddle.getSpeed());
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
                    paddle.setDx(paddle.getSpeed());

                // ESC bật/tắt tạm dừng
                if (key == KeyEvent.VK_ESCAPE)
                    handleEscKey();

                // Xử lý phím khi đang tạm dừng
                if (paused && showingPauseMenu) {
                    if (key == KeyEvent.VK_R) { // Restart
                        paused = false;
                        showingPauseMenu = false;
                        initGameObjects();
                        timer.start();
                        repaint();
                    } else if (key == KeyEvent.VK_M) { // Main Menu
                        paused = false;
                        showingPauseMenu = false;
                        if (onGameOver != null)
                            SwingUtilities.invokeLater(onGameOver);
                    }
                }
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

    public Game(int level, Runnable onGameOver) {
        this.onGameOver = onGameOver;
        this.currentLevel = level;
        this.totalBricks = 20 + (level - 1) * 10;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);

        initGameObjects();

        timer = new Timer(16, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
                    paddle.setDx(-paddle.getSpeed());
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
                    paddle.setDx(paddle.getSpeed());

                // ESC bật/tắt tạm dừng
                if (key == KeyEvent.VK_ESCAPE)
                    handleEscKey();

                // Xử lý phím khi đang tạm dừng
                if (paused && showingPauseMenu) {
                    if (key == KeyEvent.VK_R) { // Restart
                        paused = false;
                        showingPauseMenu = false;
                        initGameObjects();
                        timer.start();
                        repaint();
                    } else if (key == KeyEvent.VK_M) { // Main Menu
                        paused = false;
                        showingPauseMenu = false;
                        if (onGameOver != null)
                            SwingUtilities.invokeLater(onGameOver);
                    }
                }
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

    // Xử lý phím ESC bật/tắt menu tạm dừng
    private void handleEscKey() {
        if (!paused) {
            // ESC lần đầu → bật menu tạm dừng
            paused = true;
            showingPauseMenu = true;
            timer.stop();
            repaint();
        } else {
            // ESC lần 2 → tiếp tục game
            paused = false;
            showingPauseMenu = false;
            timer.start();
            repaint();
        }
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

        int cols = 5;
        int rows = (int) Math.ceil(totalBricks / (double) cols);
        double brickW = WIDTH / (double) cols;
        double brickH = 20;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (bricks.size() >= totalBricks) break;
                bricks.add(new Brick(c * brickW, 50 + r * (brickH + 5), brickW - 4, brickH, 1, "normal"));
            }
        }

        levelCompleted = false;
        /**
         * am thanh nen.
         */
        SoundManager.playBackground("sounds/background.wav");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (paused) return; // Không cập nhật khi game đang tạm dừng

        double dt = 0.016; // fixed timestep

        paddle.move(dt);
        paddle.update(dt);
        ball.update(dt);

        // Ball - Paddle collision
        if (ball.checkCollision(paddle)) {
            ball.bounceOff(paddle);

            /**
             * am thanh dap vao paddle.
             */
            SoundManager.playSound("sounds/hit.wav");
        }

        // Ball - Brick collisions
        for (Brick b : new ArrayList<>(bricks)) {
            if (!b.isDestroyed() && ball.checkCollision(b)) {
                b.setDestroyed(true);

                if (!ball.isPiercing()) {
                    ball.bounceOff(b);
                }

                /**
                 * amthanh dap vao gach.
                 */
                SoundManager.playSound("sounds/hit.wav");

                // 10% drop chance for power-up
                if (Math.random() < 0.1) {
                    PowerUp powerUp = null;
                    double brickX = b.getX() + b.getWidth() / 2 - 10;
                    double brickY = b.getY() + b.getHeight() / 2 - 10;

                    int rand = (int) (Math.random() * 8);
                    switch (rand) {
                        case 0: powerUp = new ExpandPaddlePowerUp(brickX, brickY); break;
                        case 1: powerUp = new ShrinkPaddlePowerUp(brickX, brickY); break;
                        case 2: powerUp = new SpeedUpPaddlePowerUp(brickX, brickY); break;
                        case 3: powerUp = new SlowPaddlePowerUp(brickX, brickY); break;
                        case 4: powerUp = new FastBallPowerUp(brickX, brickY); break;
                        case 5: powerUp = new SlowBallPowerUp(brickX, brickY); break;
                        case 6: powerUp = new ReverseControlPowerUp(brickX, brickY); break;
                        case 7: powerUp = new PiercingPowerUp(brickX, brickY); break;
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
            SoundManager.stopBackground();

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

        // Kiểm tra xem còn viên gạch nào không
        boolean allDestroyed = true;
        for (Brick b : bricks) {
            if (!b.isDestroyed()) {
                allDestroyed = false;
                break;
            }
        }

        if (allDestroyed && !levelCompleted) {
            levelCompleted = true;
            timer.stop();

            // Nếu còn level tiếp theo → chuyển level mới
            if (currentLevel < 3) {
                int nextLevel = currentLevel + 1;
                int choice = JOptionPane.showConfirmDialog(this,
                        "Bạn đã hoàn thành Level " + currentLevel + "!\nChuyển sang Level " + nextLevel + "?",
                        "Hoàn thành Level",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    currentLevel = nextLevel;
                    totalBricks = 20 + (currentLevel - 1) * 10;
                    initGameObjects();
                    timer.start();
                } else if (onGameOver != null) {
                    SwingUtilities.invokeLater(onGameOver);
                }
            } else {
                // Hoàn tất hết tất cả level
                JOptionPane.showMessageDialog(this,
                        "Chúc mừng! Bạn đã hoàn thành tất cả 3 level!",
                        "Chiến thắng",
                        JOptionPane.INFORMATION_MESSAGE);
                if (onGameOver != null) SwingUtilities.invokeLater(onGameOver);
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

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Level " + currentLevel, 20, 30);

        //  Hiển thị menu tạm dừng overlay
        if (paused && showingPauseMenu) {
            Graphics2D g2 = (Graphics2D) g.create();

            // Làm tối nền
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(0, 0, WIDTH, HEIGHT);

            // Tiêu đề
            g2.setFont(new Font("Arial", Font.BOLD, 42));
            g2.setColor(Color.YELLOW);
            String title = "TẠM DỪNG";
            int titleWidth = g2.getFontMetrics().stringWidth(title);
            g2.drawString(title, (WIDTH - titleWidth) / 2, HEIGHT / 2 - 100);

            // Các lựa chọn menu
            g2.setFont(new Font("Arial", Font.BOLD, 28));
            g2.setColor(Color.WHITE);
            String[] options = {"Tiếp tục (ESC)", "Chơi lại (R)", "Về menu chính (M)"};

            int baseY = HEIGHT / 2 - 20;
            for (int i = 0; i < options.length; i++) {
                String text = options[i];
                int textWidth = g2.getFontMetrics().stringWidth(text);
                g2.drawString(text, (WIDTH - textWidth) / 2, baseY + i * 50);
            }

            g2.dispose();
        }
    }
}
