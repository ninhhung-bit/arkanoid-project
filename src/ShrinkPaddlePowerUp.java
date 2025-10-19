public class ShrinkPaddlePowerUp extends PowerUp {
    private static final double SHRINK_FACTOR = 0.75;

    public ShrinkPaddlePowerUp(double x, double y) {
        super(x, y, 30, 30, "shrink", 20.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) paddle.setWidth(paddle.getWidth() * SHRINK_FACTOR);
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (paddle != null) paddle.setWidth(paddle.getWidth() / SHRINK_FACTOR);
    }

    @Override
    public void render(Renderer rd) {
        rd.drawImage("ShrinkPaddlePowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}
