public class ShrinkPaddlePowerUp extends PowerUp {

    public ShrinkPaddlePowerUp(double x, double y) {
        super(x, y, 30, 30, "shrink", 20.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.applyPowerUp("shrink");
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
        rd.drawImage("ShrinkPaddlePowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}
