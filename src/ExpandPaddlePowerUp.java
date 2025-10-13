public class ExpandPaddlePowerUp extends PowerUp {

    public ExpandPaddlePowerUp(double x, double y) {
        super(x, y, 20, 20, "expand", 5.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.setWidth(Math.min(paddle.getWidth() * 1.5, paddle.maxWidth));
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
        rd.setColor(0, 150, 255); // xanh dương
        rd.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
