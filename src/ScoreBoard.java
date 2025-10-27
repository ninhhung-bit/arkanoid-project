import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    public static void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", 20));
        gc.fillText("Score: " + score, 10, 25);
    }
}