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
        rd.setColor(150, 150, 255); // Tím nhạt
        rd.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
