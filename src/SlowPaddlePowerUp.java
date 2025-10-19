public class SlowPaddlePowerUp extends PowerUp {
    private static final double SLOW_FACTOR = 0.75;

    public SlowPaddlePowerUp(double x, double y) {
        super(x, y, 30, 30, "slow", 20.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) paddle.setSpeed(paddle.getSpeed() * SLOW_FACTOR);
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (paddle != null) paddle.setSpeed(paddle.getSpeed() / SLOW_FACTOR);
    }

    @Override
    public void render(Renderer rd) {
        rd.drawImage("SlowPaddlePowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}
