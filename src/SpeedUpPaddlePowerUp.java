public class SpeedUpPaddlePowerUp extends PowerUp {
    private static final double SPEED_FACTOR = 1.3;

    public SpeedUpPaddlePowerUp(double x, double y) {
        super(x, y, 30, 30, "speedup", 20.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) paddle.setSpeed(paddle.getSpeed() * SPEED_FACTOR);
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (paddle != null) paddle.setSpeed(paddle.getSpeed() / SPEED_FACTOR);
    }

    @Override
    public void render(Renderer rd) {
        rd.drawImage("SpeedUpPaddlePowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}
