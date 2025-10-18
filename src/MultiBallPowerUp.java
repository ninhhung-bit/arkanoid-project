<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.util.List;

public class MultiBallPowerUp extends PowerUp {
    public MultiBallPowerUp(double x, double y) {
        super(x, y, 20, 20, "multiball", 0);
    }

    public void applyEffect(List<Ball> balls, Paddle paddle) {
        if (balls.size() >= 3) return;

        Ball baseBall = balls.get(0);
        Ball b1 = new Ball(baseBall.getX(), baseBall.getY(), baseBall.getRadius(),
                baseBall.getSpeed(), 1, -1);
        Ball b2 = new Ball(baseBall.getX(), baseBall.getY(), baseBall.getRadius(),
                baseBall.getSpeed(), -1, -1);

        double norm1 = Math.sqrt(b1.getDirectionX() * b1.getDirectionX() + b1.getDirectionY() * b1.getDirectionY());
        double norm2 = Math.sqrt(b2.getDirectionX() * b2.getDirectionX() + b2.getDirectionY() * b2.getDirectionY());
        b1.setDirectionX(b1.getDirectionX() / norm1);
        b1.setDirectionY(b1.getDirectionY() / norm1);
        b2.setDirectionX(b2.getDirectionX() / norm2);
        b2.setDirectionY(b2.getDirectionY() / norm2);

        balls.add(b1);
        balls.add(b2);
    }

    @Override
    public void render(Renderer rd) {
        rd.setColor(255, 165, 0); // Cam sáng
        rd.fillCircle(getX(), getY(), getWidth() / 2);
    }
}
=======
>>>>>>> backup-changes
import java.util.List;

public class MultiBallPowerUp extends PowerUp {
    public MultiBallPowerUp(double x, double y) {
        super(x, y, 20, 20, "multiball", 0);
    }

    public void applyEffect(List<Ball> balls, Paddle paddle) {
        if (balls.size() >= 3) return;

        Ball baseBall = balls.get(0);
        Ball b1 = new Ball(baseBall.getX(), baseBall.getY(), baseBall.getRadius(),
                baseBall.getSpeed(), 1, -1);
        Ball b2 = new Ball(baseBall.getX(), baseBall.getY(), baseBall.getRadius(),
                baseBall.getSpeed(), -1, -1);

        double norm1 = Math.sqrt(b1.getDirectionX() * b1.getDirectionX() + b1.getDirectionY() * b1.getDirectionY());
        double norm2 = Math.sqrt(b2.getDirectionX() * b2.getDirectionX() + b2.getDirectionY() * b2.getDirectionY());
        b1.setDirectionX(b1.getDirectionX() / norm1);
        b1.setDirectionY(b1.getDirectionY() / norm1);
        b2.setDirectionX(b2.getDirectionX() / norm2);
        b2.setDirectionY(b2.getDirectionY() / norm2);

        balls.add(b1);
        balls.add(b2);
    }

    @Override
    public void render(Renderer rd) {
        rd.setColor(255, 165, 0); // Cam sáng
        rd.fillCircle(getX(), getY(), getWidth() / 2);
    }
}
<<<<<<< HEAD
=======
>>>>>>> cc4cdf6 (Initial project files: add source and README)
>>>>>>> backup-changes
