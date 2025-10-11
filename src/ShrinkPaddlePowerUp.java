public class ShrinkPaddlePowerUp extends PowerUp {

    public ShrinkPaddlePowerUp(double x, double y) {
        super(x, y, 20, 20, "shrink", 5.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.setWidth(Math.max(paddle.getWidth() * 0.75, paddle.minWidth));
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
        rd.setColor(255, 100, 100); // Đỏ nhạt
        rd.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
