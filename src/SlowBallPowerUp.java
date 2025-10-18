<<<<<<< HEAD
=======
<<<<<<< HEAD
public class SlowBallPowerUp extends PowerUp {
    private static final double speedDivider = 0.5;

    public SlowBallPowerUp(double x, double y) {
        super(x, y, 20, 20, "slowball", 5);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            ball.setSpeed(ball.getSpeed() * speedDivider);
        }
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            ball.setSpeed(ball.getSpeed() / speedDivider);
        }
    }

    @Override
    public void render(Renderer rd) {
        rd.setColor(0, 255, 255); // Xanh ngọc
        rd.fillCircle(getX(), getY(), getWidth() / 2);
    }
}
=======
>>>>>>> backup-changes
public class SlowBallPowerUp extends PowerUp {
    private static final double speedDivider = 0.5;

    public SlowBallPowerUp(double x, double y) {
        super(x, y, 30, 30, "slowball", 20.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            ball.setSpeed(ball.getSpeed() * speedDivider);
        }
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            ball.setSpeed(ball.getSpeed() / speedDivider);
        }
    }

    @Override
    public void render(Renderer rd) {
        rd.drawImage("SlowBallPowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}
<<<<<<< HEAD
=======
>>>>>>> cc4cdf6 (Initial project files: add source and README)
>>>>>>> backup-changes
