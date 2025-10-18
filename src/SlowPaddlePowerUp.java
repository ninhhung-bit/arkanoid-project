<<<<<<< HEAD
public class SlowPaddlePowerUp extends PowerUp {

    private double speedMultiplier = 0.75;

    public SlowPaddlePowerUp(double x, double y) {
        super(x, y, 20, 20, "slow", 5.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.setSpeed(paddle.getSpeed() * speedMultiplier);
        }
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.resetPaddle();
        }
    }

    @Override
    public void render(Renderer rd) {
        rd.setColor(255, 255, 0); // Vàng
        rd.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
=======
public class SlowPaddlePowerUp extends PowerUp {

    private double speedMultiplier = 0.75;

    public SlowPaddlePowerUp(double x, double y) {
        super(x, y, 30, 30, "slow", 20.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.applyPowerUp("slow");
        }
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.resetPaddle();
        }
    }

    @Override
    public void render(Renderer rd) {
        rd.drawImage("SlowPaddlePowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}
>>>>>>> cc4cdf6 (Initial project files: add source and README)
