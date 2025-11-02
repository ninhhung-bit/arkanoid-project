import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ScoreBoard {
    private static int score = 0;
    private static final int BRICK_POINTS = 100;
    private static final int POWERUP_POINTS = 50;

    public static void addBrickPoints() {
        score += BRICK_POINTS;
    }

    public static void addPowerUpPoints() {
        score += POWERUP_POINTS;
    }

    public static void resetScore() {
        score = 0;
    }

    public static int getScore() {
        return score;
    }

    public static void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);
    }
}
